/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicant;

import config.dbConnect;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author user
 */
public class applyScholarship extends javax.swing.JFrame {

    /**
     * Creates new form applyScholarship
     */
    public applyScholarship() {
        initComponents();
         displayScholarship();
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
        query = "SELECT * FROM tbl_scholarship"; // Load all records
    } else {
        query = "SELECT * FROM tbl_scholarship WHERE LOWER(s_name) LIKE ? OR LOWER(status) LIKE ?";
    }

    try (PreparedStatement statement = conn.prepareStatement(query)) {
        if (!isSearchEmpty) { 
            statement.setString(1, "%" + searchText + "%");
            statement.setString(2, "%" + searchText + "%");
        }

        try (ResultSet rs = statement.executeQuery()) {
          
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Scholarship Name");
            model.addColumn("Description");
            model.addColumn("Fund Amount");
            model.addColumn("Capacity");
            model.addColumn("Status");
            model.addColumn("Deadline");
            model.addColumn("Action"); 

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("s_id"),
                    rs.getString("s_name"),
                    rs.getString("description"),
                    rs.getDouble("fund_amount"),
                    rs.getInt("capacity"),
                    rs.getString("status"),
                    rs.getString("deadline"),
                    "View" 
                });
            }

            tbl_scholarship.setModel(model);

            // ✅ Reapply button renderer & editor
            tbl_scholarship.getColumn("Action").setCellRenderer(new ButtonRenderer());
          tbl_scholarship.getColumn("Action").setCellEditor(new ButtonEditor(new JCheckBox(), this));


            if (tbl_scholarship.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "No matching records found.");
            }
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error executing query: " + ex.getMessage());
        ex.printStackTrace();
    } finally {
        db.close(); 
    }


}private void showScholarshipDetails(int id) {
    dbConnect db = new dbConnect();
   new scholarshipDetails(id).setVisible(true);
}
   public void displayScholarship() {
    try {
        dbConnect db = new dbConnect();
        ResultSet rs = db.getData("SELECT s_id, s_name, description, fund_amount, capacity, status, deadline FROM tbl_scholarship");

       
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Scholarship Name");
        model.addColumn("Description");
        model.addColumn("Fund Amount");
        model.addColumn("Capacity");
        model.addColumn("Status");
        model.addColumn("Deadline");
        model.addColumn("Action"); 

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getInt("s_id"),
                rs.getString("s_name"),
                rs.getString("description"),
                rs.getString("fund_amount"),
                rs.getInt("capacity"),
                rs.getString("status"),
                rs.getString("deadline"),
                "View" 
            });
        }

        tbl_scholarship.setModel(model);

       
        tbl_scholarship.getColumn("Action").setCellRenderer(new ButtonRenderer());
       tbl_scholarship.getColumn("Action").setCellEditor(new ButtonEditor(new JCheckBox(), this));


        rs.close();
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
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
        tbl_scholarship = new javax.swing.JTable();
        searchfield = new javax.swing.JTextField();
        searchnav = new javax.swing.JPanel();
        search = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        backingnav = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 204, 102));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("LIST OF AVAILABLE SCHOLARSHIP");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 1030, 50));

        tbl_scholarship.setBackground(new java.awt.Color(153, 153, 153));
        tbl_scholarship.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        tbl_scholarship.setForeground(new java.awt.Color(255, 255, 255));
        tbl_scholarship.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "s_id", "s_name", "description", "fund_amount", "capacity", "status", "deadline", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbl_scholarship);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 1170, 520));

        searchfield.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        searchfield.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        searchfield.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
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
        jPanel1.add(searchfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 1060, 40));

        searchnav.setBackground(new java.awt.Color(255, 255, 255));
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

        jPanel1.add(searchnav, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 170, 100, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/listofavailabe.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 120, 110));

        backingnav.setBackground(new java.awt.Color(102, 102, 102));
        backingnav.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backingnavMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backingnavMouseExited(evt);
            }
        });
        backingnav.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        backingnav.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 50, 20));

        jPanel1.add(backingnav, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 30, 120, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1212, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 766, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void searchfieldMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchfieldMouseEntered
        searchfield.setBackground(defaultcolor);
    }//GEN-LAST:event_searchfieldMouseEntered

    private void searchfieldMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchfieldMouseExited
        searchfield.setBackground(hover);
    }//GEN-LAST:event_searchfieldMouseExited

    private void searchfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchfieldActionPerformed

    }//GEN-LAST:event_searchfieldActionPerformed

    private void searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchMouseClicked

    }//GEN-LAST:event_searchMouseClicked

    private void searchnavMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchnavMouseEntered
        searchnav.setBackground(defaultcolor);
    }//GEN-LAST:event_searchnavMouseEntered

    private void searchnavMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchnavMouseExited
        searchnav.setBackground(hover);
    }//GEN-LAST:event_searchnavMouseExited

    private void backingnavMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backingnavMouseExited
               backingnav.setBackground(defaultcolor);

    }//GEN-LAST:event_backingnavMouseExited

    private void backingnavMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backingnavMouseEntered
            backingnav.setBackground(hover);
    }//GEN-LAST:event_backingnavMouseEntered

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
       applicantDashboard ad = new applicantDashboard();
       ad.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jLabel3MouseClicked

    /**
     * @param args the command line arguments
     */
  class ButtonRenderer extends JButton implements TableCellRenderer {
    public ButtonRenderer() {
        setText("View");
        setOpaque(true);
        setForeground(Color.BLACK); 
        setBackground(Color.LIGHT_GRAY);
        setFont(new Font("SansSerif", Font.PLAIN, 14)); 
        setBorder(BorderFactory.createLineBorder(Color.GRAY, 1)); 
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setBackground(Color.DARK_GRAY); 
            setForeground(Color.WHITE); 
        } else {
            setBackground(Color.LIGHT_GRAY);
            setForeground(Color.BLACK);
        }
        return this;
    }
}
  class ButtonEditor extends DefaultCellEditor {
    private JButton button;
    private String label;
    private boolean clicked;
    private int selectedRow;
    private JTable table;
    private JFrame parentFrame; // Reference to the parent frame

    public ButtonEditor(JCheckBox checkBox, JFrame parent) {
        super(checkBox);
        this.parentFrame = parent; // Store reference to the parent frame
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.table = table;
        this.selectedRow = row;
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        clicked = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (clicked) {
            int scholarshipID = (int) table.getValueAt(selectedRow, 0); // Get scholarship ID
            openScholarshipDetails(scholarshipID);
        }
        clicked = false;
        return label;
    }

    private void openScholarshipDetails(int scholarshipID) {
        JOptionPane.showMessageDialog(null, "Opening details for Scholarship ID: " + scholarshipID);

        // Open the scholarship details frame
        scholarshipDetails sd = new scholarshipDetails(scholarshipID);
        sd.setVisible(true);

        // Dispose the parent frame
        if (parentFrame != null) {
            parentFrame.dispose();
        }
    }

    @Override
    public boolean stopCellEditing() {
        clicked = false;
        return super.stopCellEditing();
    }


}
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
            java.util.logging.Logger.getLogger(applyScholarship.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(applyScholarship.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(applyScholarship.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(applyScholarship.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new applyScholarship().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel backingnav;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel search;
    private javax.swing.JTextField searchfield;
    private javax.swing.JPanel searchnav;
    private javax.swing.JTable tbl_scholarship;
    // End of variables declaration//GEN-END:variables
}
