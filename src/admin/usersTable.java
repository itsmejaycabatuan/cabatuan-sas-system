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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;
import scholarshipgui.LoginForm;

/**
 *
 * @author user
 */
public class usersTable extends javax.swing.JFrame {

    /**
     * Creates new form usersTable
     */
    public usersTable() {
        initComponents();
        displayUsers();
        
        searchfield.getDocument().addDocumentListener(new DocumentListener() {
    @Override
    public void insertUpdate(DocumentEvent e) {
        searchTable(); // Call searchTable when text is added
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        searchTable(); // Call searchTable when text is removed
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        searchTable(); // Needed for text formatting changes (rare)
    }
});

    }
    
    
   public void displayUsers() {
    try {
        dbConnect db = new dbConnect();
        Integer currentUserId = Session.getInstance().getUser_id(); // Get current user's ID

        String query = "SELECT u_id, f_name, l_name, email, status FROM tbl_user WHERE u_id != ?";
        PreparedStatement pst = db.getConnection().prepareStatement(query);
        pst.setInt(1, currentUserId); // Exclude the current user

        ResultSet rs = pst.executeQuery();
        tbl_users.setModel(DbUtils.resultSetToTableModel(rs));

        rs.close();
        pst.close();
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }
}
    private void searchTable() {
    String searchText = searchfield.getText().toLowerCase().trim(); // Normalize input

    dbConnect db = new dbConnect();
    Connection conn = db.getConnection();

    if (conn == null) {
        JOptionPane.showMessageDialog(this, "Database connection failed. Please check your settings.");
        return;
    }

    String query;
    boolean isSearchEmpty = searchText.isEmpty();
    

    if (isSearchEmpty) {
        query = "SELECT * FROM tbl_user"; 
    } else {
        query = "SELECT * FROM tbl_user WHERE LOWER(username) LIKE ? OR LOWER(status) LIKE ?";
    }

    try (PreparedStatement statement = conn.prepareStatement(query)) {
        if (!isSearchEmpty) { 
            statement.setString(1, "%" + searchText + "%");
            statement.setString(2, "%" + searchText + "%");
        }

        try (ResultSet resultSet = statement.executeQuery()) {
            tbl_users.setModel(DbUtils.resultSetToTableModel(resultSet));

            if (tbl_users.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "No matching records found.");
            }
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error executing query: " + ex.getMessage());
        ex.printStackTrace();
    } finally {
        db.close(); 
    }
}
public void updateUserStatus(int userID, String newStatus) {
    dbConnect db = new dbConnect();
    
    try {
        String query = "UPDATE tbl_user SET status = ? WHERE u_id = ?";
        PreparedStatement pstmt = db.getConnection().prepareStatement(query);
        pstmt.setString(1, newStatus);
        pstmt.setInt(2, userID);

        int rowsAffected = pstmt.executeUpdate();
        pstmt.close();
        
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "User status updated successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Failed to update user status.");
        }
    } catch (SQLException e) {
        System.out.println("Error updating status: " + e.getMessage());
    }
}
public void loadUsersIntoTable() {
    DefaultTableModel model = (DefaultTableModel) tbl_users.getModel();
    model.setRowCount(0); 

    dbConnect db = new dbConnect();
    
    try {
        String query = "SELECT u_id, username, f_name, l_name, email, contact, type, pass, status, registration_date FROM tbl_user";
        PreparedStatement pstmt = db.getConnection().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("u_id");
            String username = rs.getString("username");
            String f_name = rs.getString("f_name");
            String l_name = rs.getString("l_name");
            String email = rs.getString("email");
            String contact = rs.getString("contact");
            String type = rs.getString("type");
            String pass = rs.getString("pass");
          
            String status = rs.getString("status");
            Timestamp regTimestamp = rs.getTimestamp("registration_date");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = sdf.format(regTimestamp);
            
            model.addRow(new Object[]{id, username, f_name, l_name, email, contact, type, pass, status, formattedDate});
        }

        rs.close();
        pstmt.close();
    } catch (SQLException e) {
        System.out.println("Error loading users: " + e.getMessage());
    }
}
public void deleteUser(int userID) {
    dbConnect dc = new dbConnect();
    
    try (Connection con = dc.getConnection()) {
        // 🔹 Step 1: Delete logs related to the user
        String deleteLogsQuery = "DELETE FROM tbl_logs WHERE user_id = ?";
        try (PreparedStatement pstmtLogs = con.prepareStatement(deleteLogsQuery)) {
            pstmtLogs.setInt(1, userID);
            pstmtLogs.executeUpdate();
        }

        // 🔹 Step 2: Delete the user
        String deleteUserQuery = "DELETE FROM tbl_user WHERE u_id = ?";
        try (PreparedStatement pstmtUser = con.prepareStatement(deleteUserQuery)) {
            pstmtUser.setInt(1, userID);
            int rowsAffected = pstmtUser.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "User deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "User deletion failed!");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error deleting user: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
        addUsers = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        refreshnav = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        deletenav = new javax.swing.JPanel();
        delete = new javax.swing.JLabel();
        editnav = new javax.swing.JPanel();
        edit = new javax.swing.JLabel();
        acc_name = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_users = new javax.swing.JTable();
        searchfield = new javax.swing.JTextField();
        searchnav = new javax.swing.JPanel();
        search = new javax.swing.JLabel();

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
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 280, -1));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/finallogoadmin.png"))); // NOI18N
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 130, 110));

        usersnav.setBackground(new java.awt.Color(255, 255, 255));
        usersnav.addMouseListener(new java.awt.event.MouseAdapter() {
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

        addUsers.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        addUsers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addUsers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/adduser.png"))); // NOI18N
        addUsers.setText(" Add");
        addUsers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addUsersMouseClicked(evt);
            }
        });
        usersnav.add(addUsers, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 120, 30));

        jPanel2.add(usersnav, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 240, 50));

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconforsholarship.png"))); // NOI18N
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 260, 260));

        refreshnav.setBackground(new java.awt.Color(255, 255, 255));
        refreshnav.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                refreshnavMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                refreshnavMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                refreshnavMouseExited(evt);
            }
        });
        refreshnav.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back.png"))); // NOI18N
        jLabel8.setText(" Back");
        refreshnav.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 140, 30));

        jPanel2.add(refreshnav, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 240, 50));

        deletenav.setBackground(new java.awt.Color(255, 255, 255));
        deletenav.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                deletenavMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                deletenavMouseExited(evt);
            }
        });
        deletenav.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        delete.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        delete.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/deleteuser.png"))); // NOI18N
        delete.setText(" Delete");
        delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteMouseClicked(evt);
            }
        });
        deletenav.add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 130, 30));

        jPanel2.add(deletenav, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 240, 50));

        editnav.setBackground(new java.awt.Color(255, 255, 255));
        editnav.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                editnavMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                editnavMouseExited(evt);
            }
        });
        editnav.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        edit.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        edit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edituser.png"))); // NOI18N
        edit.setText(" Edit");
        edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editMouseClicked(evt);
            }
        });
        editnav.add(edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 120, 30));

        jPanel2.add(editnav, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 240, 50));

        acc_name.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        acc_name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        acc_name.setText("Hello, Admin");
        jPanel2.add(acc_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 190, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 280, 680));

        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 204, 102));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("USER'S");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 50, 610, 50));

        tbl_users.setBackground(new java.awt.Color(153, 153, 153));
        tbl_users.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        tbl_users.setForeground(new java.awt.Color(255, 255, 255));
        tbl_users.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tbl_users);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 180, 860, 520));

        searchfield.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        searchfield.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        searchfield.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 102), 3));
        searchfield.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                searchfieldMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                searchfieldMouseExited(evt);
            }
        });
        searchfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchfieldActionPerformed(evt);
            }
        });
        jPanel1.add(searchfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 130, 750, 40));

        searchnav.setBackground(new java.awt.Color(255, 255, 255));
        searchnav.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 102), 3));
        searchnav.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                searchnavMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                searchnavMouseExited(evt);
            }
        });
        searchnav.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        search.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/seach.png"))); // NOI18N
        search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchMouseClicked(evt);
            }
        });
        searchnav.add(search, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 40, 40));

        jPanel1.add(searchnav, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 130, 100, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1201, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 721, Short.MAX_VALUE)
                .addGap(0, 0, 0))
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

    private void editnavMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editnavMouseEntered
        editnav.setBackground(defaultcolor);
    }//GEN-LAST:event_editnavMouseEntered

    private void editnavMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editnavMouseExited
        editnav.setBackground(hover);
    }//GEN-LAST:event_editnavMouseExited

    private void deletenavMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deletenavMouseEntered
        deletenav.setBackground(defaultcolor);
    }//GEN-LAST:event_deletenavMouseEntered

    private void deletenavMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deletenavMouseExited
        deletenav.setBackground(hover);
    }//GEN-LAST:event_deletenavMouseExited

    private void refreshnavMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshnavMouseEntered
       refreshnav.setBackground(defaultcolor);
    }//GEN-LAST:event_refreshnavMouseEntered

    private void refreshnavMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshnavMouseExited
        refreshnav.setBackground(hover);
    }//GEN-LAST:event_refreshnavMouseExited

    private void refreshnavMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshnavMouseClicked
      adminDashboard ad = new adminDashboard();
      ad.setVisible(true);
      this.dispose();
    }//GEN-LAST:event_refreshnavMouseClicked

    private void searchfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchfieldActionPerformed
       

    }//GEN-LAST:event_searchfieldActionPerformed

    private void searchnavMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchnavMouseEntered
       searchnav.setBackground(defaultcolor);
    }//GEN-LAST:event_searchnavMouseEntered

    private void searchnavMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchnavMouseExited
        searchnav.setBackground(hover);
    }//GEN-LAST:event_searchnavMouseExited

    private void searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchMouseClicked
        searchTable();
    }//GEN-LAST:event_searchMouseClicked

    private void searchfieldMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchfieldMouseEntered
        searchfield.setBackground(defaultcolor);
    }//GEN-LAST:event_searchfieldMouseEntered

    private void searchfieldMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchfieldMouseExited
       searchfield.setBackground(hover);
    }//GEN-LAST:event_searchfieldMouseExited

    private void addUsersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addUsersMouseClicked
        addUsers au = new addUsers();
        au.setVisible(true);
        this.dispose();
        au.remove.setEnabled(false);
        au.select.setEnabled(true);
    }//GEN-LAST:event_addUsersMouseClicked

    private void editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editMouseClicked
                int rowIndex = tbl_users.getSelectedRow();


                if(rowIndex < 0 ){
                    JOptionPane.showMessageDialog(null, "Please select an item.", "Click a Item", JOptionPane.WARNING_MESSAGE);
                }else{

                    try{
                    dbConnect dc = new dbConnect();
                    TableModel tbl  = tbl_users.getModel();
                  ResultSet rs = dc.getData("SELECT * FROM tbl_user WHERE u_id = '" + tbl.getValueAt(rowIndex, 0) + "'");

                    if(rs.next()){
                         updateForm uf = new updateForm();
                           uf.u_id.setText(""+rs.getInt("u_id"));
                          uf.username.setText(""+rs.getString("username"));
                        uf.fname.setText(""+rs.getString("f_name"));
                        uf.lname.setText(""+rs.getString("l_name"));
                         uf.email.setText(""+rs.getString("email"));
                         uf.contact.setText(""+rs.getString("contact"));
                         uf.type.setSelectedItem(""+rs.getString("type"));
                         uf.pass.setText(""+rs.getString("pass"));
                          uf.type1.setSelectedItem(""+rs.getString("status"));
                          uf.image.setIcon( uf.ResizeImage(rs.getString("image"), null, uf.image));
                          uf.oldpath = rs.getString("image");
                          uf.path = rs.getString("image");
                          uf.destination = rs.getString("image");
                          
                          if(rs.getString("image").isEmpty()){
                              uf.select.setEnabled(true);
                              uf.remove.setEnabled(false);
                          }else{
                              uf.select.setEnabled(false);
                              uf.remove.setEnabled(true);
                          }
                          
                         uf.setVisible(true);
                        this.dispose();
                    }
                   }catch(SQLException e){
                                 System.out.println(""+e.getMessage());
                            }
                }

    }//GEN-LAST:event_editMouseClicked

    private void deleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteMouseClicked
     
        
        int selectedRow = tbl_users.getSelectedRow();
dbConnect dc = new dbConnect();

if (selectedRow != -1) {
    int userID = (int) tbl_users.getValueAt(selectedRow, 0);
    System.out.println("Selected user ID to delete: " + userID);

    int confirm = JOptionPane.showConfirmDialog(null, 
            "Are you sure you want to delete this user?", 
            "Confirm Deletion", 
            JOptionPane.YES_NO_OPTION);

    if (confirm == JOptionPane.YES_OPTION) {
        try (Connection con = dc.getConnection()) {
            con.setAutoCommit(false); // ✅ Disable auto-commit for transaction

           
            String deleteLogsQuery = "DELETE FROM tbl_logs WHERE user_id = ?";
            try (PreparedStatement pstmtDeleteLogs = con.prepareStatement(deleteLogsQuery)) {
                pstmtDeleteLogs.setInt(1, userID);
                pstmtDeleteLogs.executeUpdate();
            }

          
            String deleteUserQuery = "DELETE FROM tbl_user WHERE u_id = ?";
            try (PreparedStatement pstmtDeleteUser = con.prepareStatement(deleteUserQuery)) {
                pstmtDeleteUser.setInt(1, userID);
                int rowsDeleted = pstmtDeleteUser.executeUpdate();

                if (rowsDeleted > 0) {
                    System.out.println("User deleted successfully!");

                    // 🔹 Step 3: Log admin action (Admin deleting user)
                    int adminID = getUserId(acc_name.getText().trim()); // Get admin ID
                    if (adminID != -1) {
                        String logQuery = "INSERT INTO tbl_logs (user_id, action, date_time) VALUES (?, ?, NOW())";
                        try (PreparedStatement pstmtLog = con.prepareStatement(logQuery)) {
                            pstmtLog.setInt(1, adminID);
                            pstmtLog.setString(2, "Admin deleted user with ID: " + userID);
                            pstmtLog.executeUpdate();
                        }
                    }

                    con.commit(); // ✅ Commit changes

                    JOptionPane.showMessageDialog(null, "User deleted successfully and logged!");

                    // ✅ Refresh Table
                    tbl_users.clearSelection();
                    DefaultTableModel model = (DefaultTableModel) tbl_users.getModel();
                    model.setRowCount(0);
                    loadUsersIntoTable();
                } else {
                    JOptionPane.showMessageDialog(null, "User deletion failed!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting user: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
} else {
    JOptionPane.showMessageDialog(null, "Please select a user to delete.");
}

    }//GEN-LAST:event_deleteMouseClicked

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
            java.util.logging.Logger.getLogger(usersTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(usersTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(usersTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(usersTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new usersTable().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel acc_name;
    private javax.swing.JLabel addUsers;
    private javax.swing.JLabel delete;
    private javax.swing.JPanel deletenav;
    private javax.swing.JLabel edit;
    private javax.swing.JPanel editnav;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel refreshnav;
    private javax.swing.JLabel search;
    private javax.swing.JTextField searchfield;
    private javax.swing.JPanel searchnav;
    private javax.swing.JTable tbl_users;
    private javax.swing.JPanel usersnav;
    // End of variables declaration//GEN-END:variables
}
