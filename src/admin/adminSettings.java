/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import static applicant.applicantSettings.getHeightFromWidth;
import config.Session;
import config.dbConnect;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import scholarshipgui.LoginForm;

/**
 *
 * @author user
 */
public class adminSettings extends javax.swing.JFrame {

    /**
     * Creates new form adminSettings
     */
    
    public adminSettings() {
       
        initComponents();
         fetchAndDisplayImage();
         checkUserImage();
    }
    public adminSettings(String userId) {
       
        initComponents();
        
        refreshEmail(userId);
        refreshContact(userId);
        // Load latest email when opened

    // Add listener to auto-refresh when window is re-focused
    this.addWindowListener(new WindowAdapter() {
        @Override
        public void windowActivated(WindowEvent e) {
            refreshEmail(userId); // Refresh email when user clicks back to Settings
              refreshContact(userId);
        }
    });
    }
    public void refreshEmail(String userId) {
    try {
        dbConnect dc = new dbConnect();
        Connection con = dc.getConnection();
        String query = "SELECT email FROM tbl_user WHERE u_id = ?";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, userId);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            email.setText(rs.getString("email")); 
        } else {
            JOptionPane.showMessageDialog(null, "User not found!");
        }

        rs.close();
        pst.close();
        con.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error refreshing email!");
    }
}
       private void fetchAndDisplayImage() {
                       dbConnect dc = new dbConnect();
                       Connection con = dc.getConnection();
                       String query = "SELECT image FROM tbl_user WHERE u_id = ?";

                       try {
                           PreparedStatement pst = con.prepareStatement(query);
                           pst.setInt(1, Session.getInstance().getUser_id()); 
                           ResultSet rs = pst.executeQuery();

                           if (rs.next()) {
                               String imagePath = rs.getString("image");

                               if (imagePath != null && !imagePath.isEmpty()) {
                                   displayImage(imagePath);
                               }
                           }

                           rs.close();
                           pst.close();
                           con.close();
                       } catch (SQLException e) {
                           e.printStackTrace();
                       }
                   } private void displayImage(String imagePath) {
                   if (imagePath == null || imagePath.isEmpty()) {
                       System.out.println("Image path is null or empty!");
                       return;
                   }

                   File imgFile = new File(imagePath);
                   if (!imgFile.exists()) {
                       System.out.println("Image file not found: " + imagePath);
                       return; 
                   }

                   ImageIcon icon = new ImageIcon(imagePath);
                   Image img = icon.getImage().getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_SMOOTH);
                   image.setIcon(new ImageIcon(img));
               }
     private void checkUserImage() {
                       try {
                           dbConnect dc = new dbConnect();
                           Connection con = dc.getConnection();
                           if (con == null) {
                               JOptionPane.showMessageDialog(null, "Database connection failed!", "Error", JOptionPane.ERROR_MESSAGE);
                               return;
                           }


                           String imagePath = null;
                           String query = "SELECT image FROM tbl_user WHERE u_id = ?";
                           PreparedStatement pstmt = con.prepareStatement(query);
                           Integer userId = Session.getInstance().getUser_id();

                           if (userId == null) {
                               JOptionPane.showMessageDialog(null, "Session error: User ID not found!", "Error", JOptionPane.ERROR_MESSAGE);
                               return;
                           }

                           pstmt.setInt(1, userId);
                           ResultSet rs = pstmt.executeQuery();
                           if (rs.next()) {
                               imagePath = rs.getString("image");
                           }

                           rs.close();
                           pstmt.close();
                           con.close();


                           if (imagePath != null && !imagePath.isEmpty()) {
                               remove.setEnabled(true);
                               select.setEnabled(false);
                           } else {
                               remove.setEnabled(false);
                               select.setEnabled(true);
                           }

                       } catch (SQLException e) {
                           JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                       }
                   }
     public void refreshContact(String userId) {
    try {
        dbConnect dc = new dbConnect();
        Connection con = dc.getConnection();
        String query = "SELECT contact FROM tbl_user WHERE u_id = ?";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, userId);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            contact.setText(rs.getString("contact")); 
        } else {
            JOptionPane.showMessageDialog(null, "User not found!");
        }

        rs.close();
        pst.close();
        con.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error refreshing contact number!");
    }
}
      public int FileExistenceChecker(String path){
        File file = new File(path);
        String fileName = file.getName();
        
        Path filePath = Paths.get("src/userimages", fileName);
        boolean fileExists = Files.exists(filePath);
        
        if (fileExists) {
            return 1;
        } else {
            return 0;
        }
    
    }
       public  ImageIcon ResizeImage(String ImagePath, byte[] pic, JLabel label) {
    ImageIcon MyImage = null;
        if(ImagePath !=null){
            MyImage = new ImageIcon(ImagePath);
        }else{
            MyImage = new ImageIcon(pic);
        }
        
    int newHeight = getHeightFromWidth(ImagePath, label.getWidth());

    Image img = MyImage.getImage();
    Image newImg = img.getScaledInstance(label.getWidth(), newHeight, Image.SCALE_SMOOTH);
    ImageIcon image = new ImageIcon(newImg);
    return image;
}
       
    public static int getHeightFromWidth(String imagePath, int desiredWidth) {
        try {
            // Read the image file
            File imageFile = new File(imagePath);
            BufferedImage image = ImageIO.read(imageFile);
            
            // Get the original width and height of the image
            int originalWidth = image.getWidth();
            int originalHeight = image.getHeight();
            
            // Calculate the new height based on the desired width and the aspect ratio
            int newHeight = (int) ((double) desiredWidth / originalWidth * originalHeight);
            
            return newHeight;
        } catch (IOException ex) {
            System.out.println("No image found!");
        }
        
        return -1;
    }
     public void imageUpdater(String existingFilePath, String newFilePath){
        File existingFile = new File(existingFilePath);
        if (existingFile.exists()) {
            String parentDirectory = existingFile.getParent();
            File newFile = new File(newFilePath);
            String newFileName = newFile.getName();
            File updatedFile = new File(parentDirectory, newFileName);
            existingFile.delete();
            try {
                Files.copy(newFile.toPath(), updatedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Image updated successfully.");
            } catch (IOException e) {
                System.out.println("Error occurred while updating the image: "+e);
            }
        } else {
            try{
                Files.copy(selectedFile.toPath(), new File(destination).toPath(), StandardCopyOption.REPLACE_EXISTING);
            }catch(IOException e){
                System.out.println("Error on update!");
            }
        }
   }
      private void loadProfileImage() {
    String imagePath = getCurrentImagePath(); // ✅ Fetch latest image from DB

    if (imagePath == null || imagePath.isEmpty()) {
        System.out.println("No image found in database!");
        return;
    }

    File imgFile = new File(imagePath);
    if (!imgFile.exists()) {
        System.out.println("Image file does not exist: " + imagePath);
        return;
    }

    // ✅ Scale and display the image
    ImageIcon icon = new ImageIcon(imagePath);
    Image img = icon.getImage().getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_SMOOTH);
    image.setIcon(new ImageIcon(img));
}
       private String getCurrentImagePath() {
    String imagePath = null;
    try {
        dbConnect dc = new dbConnect();
        Connection con = dc.getConnection();
        
        String query = "SELECT image FROM tbl_user WHERE u_id = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setInt(1, Session.getInstance().getUser_id()); 
        
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            imagePath = rs.getString("image");
        }

        rs.close();
        pstmt.close();
        con.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return imagePath; // This should NOT be null!
}
     public String destination = "";
    File selectedFile;
    public String oldpath;
    public String path;
    
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
        jLabel3 = new javax.swing.JLabel();
        Email = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        Email1 = new javax.swing.JLabel();
        Email3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        fname = new javax.swing.JLabel();
        lname = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        username = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        email = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        contact = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        password = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        id = new javax.swing.JLabel();
        back1 = new javax.swing.JPanel();
        back = new javax.swing.JLabel();
        delete = new javax.swing.JLabel();
        cemail = new javax.swing.JLabel();
        delete2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        changelastname = new javax.swing.JLabel();
        clickforgot = new javax.swing.JLabel();
        select = new javax.swing.JLabel();
        save = new javax.swing.JLabel();
        remove = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        image = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

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

        jLabel3.setText("__________________________________________________________________________________________________________________________________");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 36, -1, 40));

        Email.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        Email.setText("Password:");
        jPanel2.add(Email, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 90, 40));

        jLabel7.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel7.setText("Name:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 70, 40));

        jLabel11.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel11.setText("Username: ");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 90, 40));

        Email1.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        Email1.setText("Email:");
        jPanel2.add(Email1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 90, 40));

        Email3.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        Email3.setText("Contact:");
        jPanel2.add(Email3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 90, 40));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fname.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        fname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fname.setText("Fname");
        jPanel3.add(fname, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 150, 50));

        lname.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        lname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lname.setText("Lname");
        jPanel3.add(lname, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 140, 50));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 350, 50));

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        username.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        username.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        username.setText("Username");
        jPanel4.add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 200, 30));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 350, 50));

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        email.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        email.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        email.setText("Email@gmal.com");
        jPanel5.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 180, 30));

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 200, 350, 50));

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        contact.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        contact.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        contact.setText("Contact#");
        jPanel6.add(contact, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 200, 30));

        jPanel2.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 260, 350, 50));

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        password.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        password.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        password.setText("Change Password");
        jPanel7.add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 180, 30));

        jPanel2.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 320, 350, 50));

        jPanel8.setBackground(new java.awt.Color(204, 204, 204));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        id.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        id.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        id.setText("User_id");
        jPanel8.add(id, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 190, 20));

        jPanel2.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 250, 40));

        back1.setBackground(new java.awt.Color(255, 255, 255));
        back1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                back1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                back1MouseExited(evt);
            }
        });
        back1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        back.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        back.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back.png"))); // NOI18N
        back.setText(" Back");
        back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backMouseClicked(evt);
            }
        });
        back1.add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 100, 30));

        jPanel2.add(back1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, 200, 50));

        delete.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        delete.setForeground(new java.awt.Color(51, 51, 51));
        delete.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/change.png"))); // NOI18N
        delete.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteMouseClicked(evt);
            }
        });
        jPanel2.add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 320, 50, 50));

        cemail.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        cemail.setForeground(new java.awt.Color(51, 51, 51));
        cemail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cemail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/change.png"))); // NOI18N
        cemail.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cemail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cemailMouseClicked(evt);
            }
        });
        jPanel2.add(cemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 200, 50, 50));

        delete2.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        delete2.setForeground(new java.awt.Color(51, 51, 51));
        delete2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        delete2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/change.png"))); // NOI18N
        delete2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        delete2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                delete2MouseClicked(evt);
            }
        });
        jPanel2.add(delete2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 260, 50, 50));

        jLabel8.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel8.setText("Personal Info");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Security Question");
        jLabel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, 170, 40));

        changelastname.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        changelastname.setForeground(new java.awt.Color(51, 51, 51));
        changelastname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        changelastname.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/change.png"))); // NOI18N
        changelastname.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        changelastname.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changelastnameMouseClicked(evt);
            }
        });
        jPanel2.add(changelastname, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 80, 50, 50));

        clickforgot.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        clickforgot.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        clickforgot.setText("Forgot Password?");
        clickforgot.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clickforgotMouseClicked(evt);
            }
        });
        jPanel2.add(clickforgot, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 410, 210, -1));

        select.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        select.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        select.setText("SELECT");
        select.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        select.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectMouseClicked(evt);
            }
        });
        jPanel2.add(select, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 390, 100, 40));

        save.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        save.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        save.setText("SAVE");
        save.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        save.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveMouseClicked(evt);
            }
        });
        jPanel2.add(save, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 390, 110, 40));

        remove.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        remove.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        remove.setText("REMOVE");
        remove.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        remove.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                removeMouseClicked(evt);
            }
        });
        jPanel2.add(remove, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 390, 110, 40));

        jPanel9.setLayout(null);
        jPanel9.add(image);
        image.setBounds(11, 10, 320, 280);

        jPanel2.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 80, 340, 300));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 930, 530));

        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 204, 102));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("PERSONAL INFORMATION");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, 770, 50));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pi.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 110, 80));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 974, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 681, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
     Session sess = Session.getInstance();
        
        if(sess.getUser_id() == 0){
            JOptionPane.showMessageDialog(null, "No account, Please Login First", "Missing Account", JOptionPane.WARNING_MESSAGE);
            LoginForm lf = new LoginForm();
            lf.setVisible(true);
            this.dispose();
     }
    fname.setText(""+sess.getFname());
       lname.setText(""+sess.getLname());
       username.setText(""+sess.getUsername());
        email.setText(""+sess.getEmail());
         contact.setText(""+sess.getContact());
          
             id.setText(""+sess.getUser_id());
    
    }//GEN-LAST:event_formWindowActivated

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked
       adminDashboard ad = new adminDashboard();
       ad.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_backMouseClicked

    private void back1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_back1MouseEntered
       back1.setBackground(defaultcolor);
    }//GEN-LAST:event_back1MouseEntered

    private void back1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_back1MouseExited
        back1.setBackground(hover);
    }//GEN-LAST:event_back1MouseExited

    private void deleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteMouseClicked
    changePassword cp  = new changePassword();
    cp.setVisible(true);
    this.dispose();
    }//GEN-LAST:event_deleteMouseClicked

    private void cemailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cemailMouseClicked
     changemail ce = new changemail();
     ce.setVisible(true);
     this.dispose();
    }//GEN-LAST:event_cemailMouseClicked

    private void delete2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_delete2MouseClicked
      changecontact cc = new changecontact();
      cc.setVisible(true);
      this.dispose();
    }//GEN-LAST:event_delete2MouseClicked

    private void changelastnameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changelastnameMouseClicked
      changefnameorLname cf = new changefnameorLname();
      cf.setVisible(true);
      this.dispose();
    }//GEN-LAST:event_changelastnameMouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
      SecurityQuestions sq = new SecurityQuestions();
      sq.setVisible(true);
      this.dispose();
           
    }//GEN-LAST:event_jLabel1MouseClicked

    private void clickforgotMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clickforgotMouseClicked
     entermail ee = new entermail();
     ee.setVisible(true);
     this.dispose();
    }//GEN-LAST:event_clickforgotMouseClicked

    private void selectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectMouseClicked

        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                selectedFile = fileChooser.getSelectedFile();
                destination = "src/userimages/" + selectedFile.getName();
                path  = selectedFile.getAbsolutePath();

                if(FileExistenceChecker(path) == 1){
                    JOptionPane.showMessageDialog(null, "File Already Exist, Rename or Choose another!");
                    destination = "";
                    path="";
                }else{
                    image.setIcon(ResizeImage(path, null, image));
                    select.setEnabled(false);
                    remove.setEnabled(true);
                }
            } catch (Exception ex) {
                System.out.println("File Error!");
            }
        }
    }//GEN-LAST:event_selectMouseClicked

    private void saveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveMouseClicked
        if (destination == null || destination.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select an image first!");
            return;
        }

        if (selectedFile == null) {
            JOptionPane.showMessageDialog(null, "No file selected!");
            return;
        }

        try {
            dbConnect dc = new dbConnect();
            Connection con = dc.getConnection();

            if (con == null) {
                JOptionPane.showMessageDialog(null, "Database connection failed!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String oldpath = null;
            String fetchQuery = "SELECT image FROM tbl_user WHERE u_id = ?";
            PreparedStatement fetchStmt = con.prepareStatement(fetchQuery);
            Integer userId = Session.getInstance().getUser_id();

            if (userId == null) {
                JOptionPane.showMessageDialog(null, "Session error: User ID not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            fetchStmt.setInt(1, userId);
            ResultSet rs = fetchStmt.executeQuery();
            if (rs.next()) {
                oldpath = rs.getString("image");
            }
            rs.close();
            fetchStmt.close();

            // ✅ DEBUG: Print paths to check correctness
            System.out.println("Old Path: " + oldpath);
            System.out.println("New Destination: " + destination);

            // ✅ Delete old image if it exists
            if (oldpath != null && !oldpath.isEmpty()) {
                File oldFile = new File(oldpath);
                if (oldFile.exists()) {
                    if (oldFile.delete()) {
                        System.out.println("Old image deleted successfully!");
                    } else {
                        System.out.println("Failed to delete old image!");
                    }
                } else {
                    System.out.println("Old image does not exist.");
                }
            }

            // ✅ Copy new image
            File newFile = new File(destination);
            Files.copy(selectedFile.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("New image copied successfully!");

            // ✅ Update image in the database
            String updateQuery = "UPDATE tbl_user SET image = ? WHERE u_id = ?";
            PreparedStatement updateStmt = con.prepareStatement(updateQuery);
            updateStmt.setString(1, destination);
            updateStmt.setInt(2, userId);

            int rowsUpdated = updateStmt.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Profile picture updated successfully!");
                loadProfileImage(); // ✅ Reload image after updating
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update profile picture!");
            }

            updateStmt.close();
            con.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "File Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_saveMouseClicked

    private void removeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removeMouseClicked
        remove.setEnabled(false);
        select.setEnabled(true);
        image.setIcon(null);
        destination = "";
        path = "";
    }//GEN-LAST:event_removeMouseClicked

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
            java.util.logging.Logger.getLogger(adminSettings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(adminSettings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(adminSettings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(adminSettings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new adminSettings().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Email;
    private javax.swing.JLabel Email1;
    private javax.swing.JLabel Email3;
    private javax.swing.JLabel back;
    private javax.swing.JPanel back1;
    private javax.swing.JLabel cemail;
    private javax.swing.JLabel changelastname;
    private javax.swing.JLabel clickforgot;
    private javax.swing.JLabel contact;
    private javax.swing.JLabel delete;
    private javax.swing.JLabel delete2;
    private javax.swing.JLabel email;
    private javax.swing.JLabel fname;
    private javax.swing.JLabel id;
    private javax.swing.JLabel image;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lname;
    private javax.swing.JLabel password;
    public javax.swing.JLabel remove;
    public javax.swing.JLabel save;
    public javax.swing.JLabel select;
    private javax.swing.JLabel username;
    // End of variables declaration//GEN-END:variables
}
