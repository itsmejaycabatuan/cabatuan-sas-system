/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scholarcommitte;

import admin.scholarshipTable;
import config.Session;
import config.dbConnect;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import scholarshipgui.LoginForm;

/**
 *
 * @author user
 */
public class sDashBoard extends javax.swing.JFrame {

    /**
     * Creates new form sDashBoard
     */
    public sDashBoard() {
        initComponents();
       loadTotalRecords();
       loadPendingApplications();
       loadApprovedApplications();
       loadRejectedApplications();
       loadRecentApplications();
    }
    public void loadRecentApplications() {
    dbConnect db = new dbConnect();
    try (Connection con = db.getConnection()) {

        // Query to get recent applications ordered by submission date
        String query = "SELECT r_id, user_id, fname, lname, u_email, date_submit, status FROM tbl_records ORDER BY date_submit DESC LIMIT 10";

        PreparedStatement pst = con.prepareStatement(query);
        ResultSet rs = pst.executeQuery();

        // Create a table model to display recent submissions
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Application ID", "User ID", "First Name", "Last Name", "Email", "Submission Date", "Status"});

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getInt("r_id"),
                rs.getInt("user_id"),
                rs.getString("fname"),
                rs.getString("lname"),
                rs.getString("u_email"),
                rs.getDate("date_submit"),
                rs.getString("status")
            });
        }

        // Assuming you have a JTable called recentApplicationsTable
        tbl_recent.setModel(model);

        rs.close();
        pst.close();

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error loading recent applications!", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    public void loadApprovedApplications() {
    dbConnect db = new dbConnect();
    try (Connection con = db.getConnection()) {

        // Query to count the number of approved applications
        String query = "SELECT COUNT(*) AS approved_records FROM tbl_records WHERE status = 'Approved'";

        PreparedStatement pst = con.prepareStatement(query);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            int approvedRecords = rs.getInt("approved_records");
            
            // Assuming you have a label or text field to display the approved records
            approved.setText(""+ approvedRecords);
        }

        rs.close();
        pst.close();

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error loading approved applications!", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

public void loadRejectedApplications() {
    dbConnect db = new dbConnect();
    try (Connection con = db.getConnection()) {

        // Query to count the number of rejected applications
        String query = "SELECT COUNT(*) AS rejected_records FROM tbl_records WHERE status = 'Rejected'";

        PreparedStatement pst = con.prepareStatement(query);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            int rejectedRecords = rs.getInt("rejected_records");
            
            // Assuming you have a label or text field to display the rejected records
           rejected.setText("" + rejectedRecords);
        }

        rs.close();
        pst.close();

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error loading rejected applications!", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

   public void loadPendingApplications() {
    dbConnect db = new dbConnect();
    try (Connection con = db.getConnection()) {

        // Query to count the number of pending applications
        String query = "SELECT COUNT(*) AS pending_records FROM tbl_records WHERE status = 'Pending'";

        PreparedStatement pst = con.prepareStatement(query);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            int pendingRecords = rs.getInt("pending_records");
            
            // Assuming you have a label or text field to display the pending records
            pending.setText(""+pendingRecords);
        }

        rs.close();
        pst.close();

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error loading pending applications!", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
   public void loadTotalRecords() {
    dbConnect db = new dbConnect();
    try (Connection con = db.getConnection()) {

        // Query to count the total number of records (applications)
        String query = "SELECT COUNT(*) AS total_records FROM tbl_records";

        PreparedStatement pst = con.prepareStatement(query);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            int totalRecords = rs.getInt("total_records");
            
            // Assuming you have a label or text field to display the total records
            total.setText(""+totalRecords);
        }

        rs.close();
        pst.close();

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error loading total records!", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    public int getUserId(String firstname) {
    int userId = -1; 

    try {
        dbConnect dc = new dbConnect();
        Connection con = dc.getConnection();

        
        String query = "SELECT u_id FROM tbl_user WHERE LOWER(TRIM(f_name)) = LOWER(TRIM(?))";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, firstname.trim()); // Trim input firstname
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            userId = rs.getInt("u_id");
           
        } else {
           
        }

        rs.close();
        pst.close();
        con.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }

    return userId;
}
 Color hover = new Color (255,255,255);
    Color defaultcolor = new Color  (102,102,102);
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        label = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        logoutnav = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        logout = new javax.swing.JLabel();
        scholarnav1 = new javax.swing.JPanel();
        labelscholar1 = new javax.swing.JLabel();
        acc_name = new javax.swing.JLabel();
        paneldashboard = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        app = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        panelset = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        release = new javax.swing.JPanel();
        labelscholar = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        total = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_recent = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        pending = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        approved = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        rejected = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 102), 3));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label.setText("_____________________________________");
        jPanel2.add(label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 280, 20));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/com.png"))); // NOI18N
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 130, 110));

        logoutnav.setBackground(new java.awt.Color(255, 255, 255));
        logoutnav.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutnavMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logoutnavMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                logoutnavMouseExited(evt);
            }
        });
        logoutnav.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setText("jLabel5");
        logoutnav.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 320, -1, -1));

        logout.setFont(new java.awt.Font("Arial Black", 1, 15)); // NOI18N
        logout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logoutfordashboard.png"))); // NOI18N
        logout.setText("  Logout");
        logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutMouseClicked(evt);
            }
        });
        logoutnav.add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 130, 40));

        jPanel2.add(logoutnav, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, 280, 60));

        scholarnav1.setBackground(new java.awt.Color(255, 255, 255));
        scholarnav1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                scholarnav1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                scholarnav1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                scholarnav1MouseExited(evt);
            }
        });
        scholarnav1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelscholar1.setFont(new java.awt.Font("Arial Black", 1, 15)); // NOI18N
        labelscholar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sdashboard.png"))); // NOI18N
        labelscholar1.setText("  Scholarships");
        labelscholar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelscholar1MouseClicked(evt);
            }
        });
        scholarnav1.add(labelscholar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 190, 30));

        jPanel2.add(scholarnav1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 260, 60));

        acc_name.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        acc_name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        acc_name.setText("Hello, Com/Staff");
        jPanel2.add(acc_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 190, 20));

        paneldashboard.setBackground(new java.awt.Color(255, 255, 255));
        paneldashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                paneldashboardMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                paneldashboardMouseExited(evt);
            }
        });
        paneldashboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Arial Black", 1, 15)); // NOI18N
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dashboard.png"))); // NOI18N
        jLabel11.setText(" Dashboard");
        paneldashboard.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 13, 151, -1));

        jPanel2.add(paneldashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 260, 50));

        app.setBackground(new java.awt.Color(255, 255, 255));
        app.setToolTipText("");
        app.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                appMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                appMouseExited(evt);
            }
        });
        app.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Arial Black", 1, 15)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/editapplication.png"))); // NOI18N
        jLabel6.setText("  Applications");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        app.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 160, 40));

        jPanel2.add(app, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 290, 60));

        panelset.setBackground(new java.awt.Color(255, 255, 255));
        panelset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelsetMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelsetMouseExited(evt);
            }
        });
        panelset.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Arial Black", 1, 15)); // NOI18N
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/settings.png"))); // NOI18N
        jLabel9.setText(" Settings");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        panelset.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 160, 29));

        jPanel2.add(panelset, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, 280, 50));

        release.setBackground(new java.awt.Color(255, 255, 255));
        release.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                releaseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                releaseMouseExited(evt);
            }
        });
        release.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelscholar.setFont(new java.awt.Font("Arial Black", 1, 15)); // NOI18N
        labelscholar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/release.png"))); // NOI18N
        labelscholar.setText(" Release Funds");
        labelscholar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelscholarMouseClicked(evt);
            }
        });
        release.add(labelscholar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 190, 30));

        jPanel2.add(release, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 280, 50));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 310, 680));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 102), 3));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setBackground(new java.awt.Color(153, 153, 153));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setFont(new java.awt.Font("Arial Black", 1, 15)); // NOI18N
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/total.png"))); // NOI18N
        jLabel22.setText(" Total User");
        jPanel8.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 180, 70));

        jLabel23.setFont(new java.awt.Font("Arial Black", 1, 48)); // NOI18N
        jLabel23.setText("0");
        jPanel8.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 50, 80));

        jLabel24.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel24.setText("Registered Users");
        jPanel8.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, 160, 50));

        jPanel6.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 160, 270, 220));

        jLabel28.setFont(new java.awt.Font("Arial Black", 1, 15)); // NOI18N
        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/total.png"))); // NOI18N
        jPanel6.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 110, 70));

        total.setFont(new java.awt.Font("Arial Black", 1, 48)); // NOI18N
        total.setText("0");
        jPanel6.add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 70, 80));

        jLabel26.setFont(new java.awt.Font("Arial Black", 1, 15)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Total Records");
        jPanel6.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 190, 50));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 150, 230, 220));

        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 40)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 204, 102));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("SCHOLARSHIP STAFF DASHBOARD");
        jLabel2.setToolTipText("");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 940, 100));

        tbl_recent.setBackground(new java.awt.Color(153, 153, 153));
        tbl_recent.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        tbl_recent.setForeground(new java.awt.Color(255, 255, 255));
        tbl_recent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tbl_recent);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 430, 950, 270));

        jLabel16.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Recent Applicant Submission");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 380, 250, 50));

        jLabel18.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Overview");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 90, 140, 50));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 204, 102), 3, true));
        jPanel7.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setFont(new java.awt.Font("Arial Black", 1, 15)); // NOI18N
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pendingusers.png"))); // NOI18N
        jPanel7.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 70, 60));

        pending.setFont(new java.awt.Font("Arial Black", 1, 48)); // NOI18N
        pending.setText("0");
        jPanel7.add(pending, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 80, 100));

        jLabel29.setFont(new java.awt.Font("Arial Black", 1, 15)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Pending Applicants");
        jPanel7.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 200, 70));

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 150, 220, 220));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 102), 3));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel30.setFont(new java.awt.Font("Arial Black", 1, 15)); // NOI18N
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/approveduser.png"))); // NOI18N
        jPanel12.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 70, 60));

        approved.setFont(new java.awt.Font("Arial Black", 1, 48)); // NOI18N
        approved.setText("0");
        jPanel12.add(approved, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 70, 70));

        jLabel32.setFont(new java.awt.Font("Arial Black", 1, 15)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("Approved Applicants");
        jPanel12.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 200, 70));

        jPanel1.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 150, 220, 220));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 102), 3));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Arial Black", 1, 15)); // NOI18N
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rejected user.png"))); // NOI18N
        jPanel13.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 70, 60));

        rejected.setFont(new java.awt.Font("Arial Black", 1, 48)); // NOI18N
        rejected.setText("0");
        jPanel13.add(rejected, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 70, 70));

        jLabel21.setFont(new java.awt.Font("Arial Black", 1, 15)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Rejected Applicants");
        jPanel13.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 200, 70));

        jPanel1.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 150, 220, 220));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1341, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 727, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void logoutnavMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutnavMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_logoutnavMouseClicked

    private void logoutnavMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutnavMouseEntered
        logoutnav.setBackground(defaultcolor);
    }//GEN-LAST:event_logoutnavMouseEntered

    private void logoutnavMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutnavMouseExited
        logoutnav.setBackground(hover);
    }//GEN-LAST:event_logoutnavMouseExited

    private void labelscholarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelscholarMouseClicked
        funds fs = new funds();
        fs.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_labelscholarMouseClicked

    private void scholarnav1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_scholarnav1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_scholarnav1MouseClicked

    private void scholarnav1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_scholarnav1MouseEntered
        scholarnav1.setBackground(defaultcolor);
    }//GEN-LAST:event_scholarnav1MouseEntered

    private void scholarnav1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_scholarnav1MouseExited
        scholarnav1.setBackground(hover);
    }//GEN-LAST:event_scholarnav1MouseExited

    private void paneldashboardMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paneldashboardMouseEntered
        paneldashboard.setBackground(defaultcolor);
    }//GEN-LAST:event_paneldashboardMouseEntered

    private void paneldashboardMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paneldashboardMouseExited
        paneldashboard.setBackground(hover);
    }//GEN-LAST:event_paneldashboardMouseExited

    private void panelsetMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelsetMouseEntered
        panelset.setBackground(defaultcolor);
    }//GEN-LAST:event_panelsetMouseEntered

    private void panelsetMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelsetMouseExited
        panelset.setBackground(hover);
    }//GEN-LAST:event_panelsetMouseExited

    private void logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseClicked
        String username = acc_name.getText();


int userId = getUserId(username);

if (userId == -1) { 
    System.out.println("🚨 User not found! Cannot log out.");
    JOptionPane.showMessageDialog(null, "Error: Cannot log action. User not found!", "Error", JOptionPane.ERROR_MESSAGE);
    return; 
}

System.out.println("✅ User found! userId: " + userId);

int choice = JOptionPane.showConfirmDialog(null, "Do you want to log out?", "Logout Confirmation!",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

if (choice == JOptionPane.YES_OPTION) {
    dbConnect dc = new dbConnect();
    dc.insertLog(userId, "Logout");

    LoginForm lf = new LoginForm();
    lf.setVisible(true);
    this.dispose();
}
    }//GEN-LAST:event_logoutMouseClicked

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
       Session sess = Session.getInstance();
     if(sess.getUser_id() == 0){
            JOptionPane.showMessageDialog(null, "No account, Please Login First", "Missing Account", JOptionPane.WARNING_MESSAGE);
            LoginForm lf = new LoginForm();
            lf.setVisible(true);
            this.dispose();
     }
     acc_name.setText(""+sess.getFname());
                                  
    }//GEN-LAST:event_formWindowActivated

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
       comsSettings cs = new comsSettings();
       cs.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jLabel9MouseClicked

    private void appMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_appMouseEntered
      app.setBackground(defaultcolor);
      
    }//GEN-LAST:event_appMouseEntered

    private void appMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_appMouseExited
      app.setBackground(hover);
    }//GEN-LAST:event_appMouseExited

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
      applicants ap = new applicants();
      ap.setVisible(true);
      this.dispose();
    }//GEN-LAST:event_jLabel6MouseClicked

    private void labelscholar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelscholar1MouseClicked
       scholarshipTable sc = new scholarshipTable();
       sc.setVisible(true);
       this.dispose(    );
    }//GEN-LAST:event_labelscholar1MouseClicked

    private void releaseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_releaseMouseEntered
      release.setBackground(defaultcolor);
    }//GEN-LAST:event_releaseMouseEntered

    private void releaseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_releaseMouseExited
       release.setBackground(hover);
    }//GEN-LAST:event_releaseMouseExited

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(sDashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(sDashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(sDashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(sDashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new sDashBoard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel acc_name;
    private javax.swing.JPanel app;
    private javax.swing.JLabel approved;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label;
    private javax.swing.JLabel labelscholar;
    private javax.swing.JLabel labelscholar1;
    private javax.swing.JLabel logout;
    private javax.swing.JPanel logoutnav;
    private javax.swing.JPanel paneldashboard;
    private javax.swing.JPanel panelset;
    private javax.swing.JLabel pending;
    private javax.swing.JLabel rejected;
    private javax.swing.JPanel release;
    private javax.swing.JPanel scholarnav1;
    private javax.swing.JTable tbl_recent;
    private javax.swing.JLabel total;
    // End of variables declaration//GEN-END:variables
}
