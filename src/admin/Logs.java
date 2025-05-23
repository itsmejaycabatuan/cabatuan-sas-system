/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import config.dbConnect;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class Logs extends javax.swing.JFrame {

    /**
     * Creates new form Logs
     */
    public Logs() {
        initComponents();
        loadLogsIntoTable();
    }
public void loadLogsIntoTable() {
    try {
        dbConnect dc = new dbConnect();
        Connection con = dc.getConnection();

      
        String query = "SELECT l.log_id, l.user_id, u.email, l.action, l.date_time " +
                       "FROM tbl_logs l " +
                       "INNER JOIN tbl_user u ON l.user_id = u.u_id " +  
                       "ORDER BY l.date_time DESC";
        PreparedStatement pst = con.prepareStatement(query);
        ResultSet rs = pst.executeQuery();

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Log ID", "User ID", "Email Address", "Action", "Date/Time"});
        
        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getInt("log_id"),
                rs.getInt("user_id"),
                rs.getString("email"),  
                rs.getString("action"),
                rs.getTimestamp("date_time")
            });
        }

        logs.setModel(model);
        rs.close();
        pst.close();
        con.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error loading logs!", "Error", JOptionPane.ERROR_MESSAGE);
    }
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
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        logs = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        refreshnav = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 60)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 204, 102));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("LOG'S");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, 650, 100));

        logs.setBackground(new java.awt.Color(153, 153, 153));
        logs.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        logs.setForeground(new java.awt.Color(255, 255, 255));
        logs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Log_ID", "User_iD", "Action", "Date/Timestamp"
            }
        ));
        jScrollPane1.setViewportView(logs);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 1170, 520));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/finallogoadmin.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 140, 110));

        refreshnav.setBackground(new java.awt.Color(102, 102, 102));
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
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        refreshnav.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 80, 30));

        jPanel1.add(refreshnav, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 10, 100, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1211, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void refreshnavMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshnavMouseClicked
        adminDashboard ad = new adminDashboard();
        ad.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_refreshnavMouseClicked

    private void refreshnavMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshnavMouseEntered
        refreshnav.setBackground(hover);
    }//GEN-LAST:event_refreshnavMouseEntered

    private void refreshnavMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshnavMouseExited
        refreshnav.setBackground(defaultcolor);
    }//GEN-LAST:event_refreshnavMouseExited

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
       adminDashboard ad = new adminDashboard();
       ad.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jLabel8MouseClicked

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
            java.util.logging.Logger.getLogger(Logs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Logs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Logs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Logs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Logs().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable logs;
    private javax.swing.JPanel refreshnav;
    // End of variables declaration//GEN-END:variables
}
