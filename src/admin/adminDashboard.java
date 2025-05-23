/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import config.Session;
import config.dbConnect;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import scholarcommitte.scho_table;
import scholarshipgui.LoginForm;

/**
 *
 * @author user
 */
public class adminDashboard extends javax.swing.JFrame {

    /**
     * Creates new form adminDashboard
     */
    public adminDashboard() {
        initComponents();
        PendingUser();
         ActiveUsers();
        countUsers();
       loadRecentUsers();
         
    }
   
    
public void loadRecentUsers() {
    SwingUtilities.invokeLater(() -> {
        DefaultTableModel model = (DefaultTableModel) recent_table.getModel();
        model.setRowCount(0); 
        dbConnect db = new dbConnect();

        try {
            Connection connection = db.getConnection();
            if (connection == null) {
                JOptionPane.showMessageDialog(null, "Failed to connect to the database.", "Database Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String query = "SELECT u_id, username, email, status, registration_date FROM tbl_user ORDER BY registration_date DESC LIMIT 5";
            try (PreparedStatement pstmt = connection.prepareStatement(query);
                 ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    int id = rs.getInt("u_id");
                    String username = rs.getString("username");
                    String email = rs.getString("email");
                    String status = rs.getString("status");
                    Timestamp regTimestamp = rs.getTimestamp("registration_date");

                 
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = sdf.format(regTimestamp);

                 
                    
                   
                    model.addRow(new Object[]{id, username, email, status, formattedDate});
                }
                
        
                
                // Force update the table
                model.fireTableDataChanged();
                recent_table.repaint();
                recent_table.revalidate();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error loading recent users: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    });
}
public void countUsers() {
    try {
        dbConnect db = new dbConnect();
        ResultSet rs = db.getData("SELECT COUNT(*) AS total FROM tbl_user");

        if (rs.next()) {
            int count = rs.getInt("total");
            total.setText(""+count); 
        }

        rs.close();
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }
}
public void ActiveUsers() {
    try {
        dbConnect db = new dbConnect();
        ResultSet rs = db.getData("SELECT COUNT(*) AS total FROM tbl_user WHERE status = 'Active'");

        if (rs.next()) {
            int count = rs.getInt("total");
            active.setText(""+count); 
        }

        rs.close();
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }
}
public void PendingUser() {
    try {
        dbConnect db = new dbConnect();
        ResultSet rs = db.getData("SELECT COUNT(*) AS total FROM tbl_user WHERE status = 'Pending'");

        if (rs.next()) {
            int count = rs.getInt("total");
            pending.setText(""+count); 
        }

        rs.close();
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
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

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        usersnav = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        logoutnav = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        logout = new javax.swing.JLabel();
        scholarnav1 = new javax.swing.JPanel();
        labelscholar = new javax.swing.JLabel();
        acc_name = new javax.swing.JLabel();
        paneldashboard = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        panelset = new javax.swing.JPanel();
        settings = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        logout1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        total = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        recent_table = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        active = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        pending = new javax.swing.JLabel();

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

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

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("_____________________________________");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 280, 20));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/finallogoadmin.png"))); // NOI18N
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 130, 110));

        usersnav.setBackground(new java.awt.Color(255, 255, 255));
        usersnav.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usersnavMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                usersnavMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                usersnavMouseExited(evt);
            }
        });
        usersnav.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setText("jLabel5");
        usersnav.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 320, -1, -1));

        jLabel6.setFont(new java.awt.Font("Arial Black", 1, 15)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userdashboardlogo.png"))); // NOI18N
        jLabel6.setText("  Users");
        usersnav.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 120, 30));

        jPanel2.add(usersnav, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 260, 50));

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
        logoutnav.add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 150, 30));

        jPanel2.add(logoutnav, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 510, 270, 50));

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

        labelscholar.setFont(new java.awt.Font("Arial Black", 1, 15)); // NOI18N
        labelscholar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sdashboard.png"))); // NOI18N
        labelscholar.setText("  Scholarships");
        labelscholar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelscholarMouseClicked(evt);
            }
        });
        scholarnav1.add(labelscholar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 190, 30));

        jPanel2.add(scholarnav1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 260, 50));

        acc_name.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        acc_name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        acc_name.setText("Hello, Admin");
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

        settings.setFont(new java.awt.Font("Arial Black", 1, 15)); // NOI18N
        settings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/settings.png"))); // NOI18N
        settings.setText(" Settings");
        settings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                settingsMouseClicked(evt);
            }
        });
        panelset.add(settings, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 160, 29));

        jPanel2.add(panelset, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 280, 50));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 270, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 600, -1, -1));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logout1.setFont(new java.awt.Font("Arial Black", 1, 15)); // NOI18N
        logout1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logout1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logs.png"))); // NOI18N
        logout1.setText("  Logs");
        logout1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logout1MouseClicked(evt);
            }
        });
        jPanel5.add(logout1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 100, 30));

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 270, 50));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 310, 680));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 102), 3));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Arial Black", 1, 15)); // NOI18N
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/total.png"))); // NOI18N
        jPanel6.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 60, 70));

        total.setFont(new java.awt.Font("Arial Black", 1, 48)); // NOI18N
        total.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        total.setText("0");
        jPanel6.add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 110, 80));

        jLabel21.setFont(new java.awt.Font("Arial Black", 1, 16)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Total User's");
        jPanel6.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 120, 50));

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

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 160, 270, 220));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 102), 3));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Welcome to Scholarship Hub !");
        jPanel7.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 440, 30));

        jLabel14.setFont(new java.awt.Font("Arial Black", 0, 20)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("secure funding for a brighter future!\"");
        jPanel7.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 490, 50));

        jLabel15.setFont(new java.awt.Font("Arial Black", 0, 20)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("\" Streamline applications, track eligibility, and ");
        jPanel7.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 530, 50));

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 160, 560, 220));

        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 40)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 204, 102));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("SCHOLARSHIP HEAD DASHBOARD");
        jLabel2.setToolTipText("");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 60, 850, 50));

        recent_table.setBackground(new java.awt.Color(153, 153, 153));
        recent_table.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        recent_table.setForeground(new java.awt.Color(255, 255, 255));
        recent_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "u_id", "username", "email", "status", "registration_date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        recent_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                recent_tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(recent_table);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 430, 560, 270));

        jLabel16.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Recent User Submission");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 380, 240, 50));

        jLabel18.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("System Reports");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 380, 140, 50));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/userlogin.png"))); // NOI18N
        jLabel19.setText("  ACTIVE USERS");
        jLabel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 102), 3));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 430, 270, 50));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 102), 3));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        active.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        active.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        active.setText("0");
        jPanel9.add(active, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 110, 40));

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 490, 270, 60));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 102), 3));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel26.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pending.png"))); // NOI18N
        jLabel26.setText(" PENDING USERS");
        jPanel10.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 190, 50));

        jPanel1.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 560, 270, 50));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 102), 3));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pending.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        pending.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pending.setText("0");
        jPanel11.add(pending, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 110, 50));

        jPanel1.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 620, 270, 80));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void usersnavMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usersnavMouseEntered
        usersnav.setBackground(defaultcolor);
    }//GEN-LAST:event_usersnavMouseEntered

    private void usersnavMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usersnavMouseExited
        usersnav.setBackground(hover);
    }//GEN-LAST:event_usersnavMouseExited

    private void usersnavMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usersnavMouseClicked
       usersTable ut = new usersTable();
       ut.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_usersnavMouseClicked

    private void logoutnavMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutnavMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_logoutnavMouseClicked

    private void logoutnavMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutnavMouseEntered
        logoutnav.setBackground(defaultcolor);
    }//GEN-LAST:event_logoutnavMouseEntered

    private void logoutnavMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutnavMouseExited
        logoutnav.setBackground(hover);
    }//GEN-LAST:event_logoutnavMouseExited

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

    private void labelscholarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelscholarMouseClicked
        scho_table st = new scho_table();
        st.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_labelscholarMouseClicked

    private void logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseClicked
          
   String username = acc_name.getText();
   System.out.println("Attempting to get userId for: " + username);

    int userId = getUserId(username); 

                if (userId == -1) { 
                    JOptionPane.showMessageDialog(null, "Error: Cannot log action. User not found!", "Error", JOptionPane.ERROR_MESSAGE);
                    return; 
                }
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

    private void recent_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_recent_tableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_recent_tableMouseClicked

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

    private void settingsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingsMouseClicked
   
  
    adminSettings as = new adminSettings();
    as.setVisible(true);
    this.dispose(); // Close adminDashboard if needed
    }//GEN-LAST:event_settingsMouseClicked

    private void logout1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logout1MouseClicked
        Logs lg = new Logs();
        lg.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_logout1MouseClicked

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
            java.util.logging.Logger.getLogger(adminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(adminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(adminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(adminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new adminDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel acc_name;
    private javax.swing.JLabel active;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelscholar;
    private javax.swing.JLabel logout;
    private javax.swing.JLabel logout1;
    private javax.swing.JPanel logoutnav;
    private javax.swing.JPanel paneldashboard;
    private javax.swing.JPanel panelset;
    private javax.swing.JLabel pending;
    private javax.swing.JTable recent_table;
    private javax.swing.JPanel scholarnav1;
    private javax.swing.JLabel settings;
    private javax.swing.JLabel total;
    private javax.swing.JPanel usersnav;
    // End of variables declaration//GEN-END:variables
}
