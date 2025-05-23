/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import static admin.addUsers.getHeightFromWidth;
import config.dbConnect;
import config.passwordHasher;
import java.awt.Color;
import java.awt.Image;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class updateForm extends javax.swing.JFrame {

    /**
     * Creates new form updateForm
     */
    public updateForm() {
        initComponents();
    }
     public String destination = "";
    File selectedFile;
    public String oldpath;
    public String path;
      public static String email1,username1;
    public boolean duplicateChecker() {
    dbConnect db = new dbConnect();
    boolean isDuplicate = false;

    try {
        String query = "SELECT email FROM tbl_user WHERE email = ?";
        PreparedStatement pstmt = db.getConnection().prepareStatement(query);
        pstmt.setString(1, email.getText());
        ResultSet resultSet = pstmt.executeQuery();

        if (resultSet.next()) { // Check if any record exists
            JOptionPane.showMessageDialog(null, "Email is Already Used");
            email.setText(""); // Clear the email field
            isDuplicate = true;
        }

        resultSet.close();
        pstmt.close();
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }

    return isDuplicate;
}
    
    public boolean updatechecker(){
        dbConnect db = new dbConnect();
        try{
        String query = "SELECT * FROM tbl_user WHERE (email = '"+email.getText()+"') AND u_id != '"+u_id.getText()+"'";
        ResultSet resultSet = db.getData(query);
        
        
        if (resultSet.next()) { 
            email1 = resultSet.getString("email");
        if(email1.equals(email.getText())){
             JOptionPane.showMessageDialog(null, "Email is Already Used");
             email.setText("");
        }
         return true;  
        }else{
            return false;
        }
        }catch(SQLException e){
            System.out.println(""+e.getMessage());
            return false;
        }
    }

    private boolean isEmailValid(String email) {
        // More robust regex (but still not perfect for all valid email addresses)
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        // Compile the regex pattern only ONCE (outside the function for efficiency)
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        u_id = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        fname = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        lname = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        contact = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        registernav = new javax.swing.JPanel();
        update = new javax.swing.JLabel();
        lgnav3 = new javax.swing.JPanel();
        lgnav4 = new javax.swing.JLabel();
        registernav2 = new javax.swing.JPanel();
        lgnav8 = new javax.swing.JLabel();
        lgnav9 = new javax.swing.JPanel();
        lgnav10 = new javax.swing.JLabel();
        registernav3 = new javax.swing.JPanel();
        lgnav11 = new javax.swing.JLabel();
        lgnav12 = new javax.swing.JPanel();
        lgnav13 = new javax.swing.JLabel();
        registernav4 = new javax.swing.JPanel();
        lgnav14 = new javax.swing.JLabel();
        lgnav15 = new javax.swing.JPanel();
        lgnav16 = new javax.swing.JLabel();
        registernav5 = new javax.swing.JPanel();
        lgnav17 = new javax.swing.JLabel();
        lgnav18 = new javax.swing.JPanel();
        lgnav19 = new javax.swing.JLabel();
        registernav6 = new javax.swing.JPanel();
        lgnav20 = new javax.swing.JLabel();
        lgnav21 = new javax.swing.JPanel();
        lgnav22 = new javax.swing.JLabel();
        registernav7 = new javax.swing.JPanel();
        lgnav23 = new javax.swing.JLabel();
        lgnav24 = new javax.swing.JPanel();
        lgnav25 = new javax.swing.JLabel();
        cancelnav = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        type1 = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        iconhide1 = new javax.swing.JLabel();
        viewicon = new javax.swing.JLabel();
        pass = new javax.swing.JPasswordField();
        jLabel13 = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        type = new javax.swing.JComboBox<>();
        jPanel9 = new javax.swing.JPanel();
        image = new javax.swing.JLabel();
        select = new javax.swing.JLabel();
        remove = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 204, 102));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("UPDATING FORM");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 680, 60));

        jLabel4.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("User ID:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 110, 30));

        u_id.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        u_id.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        u_id.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        u_id.setEnabled(false);
        u_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                u_idActionPerformed(evt);
            }
        });
        jPanel1.add(u_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, 310, 50));

        jLabel5.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("First Name:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 110, 30));

        fname.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        fname.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        fname.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        fname.setEnabled(false);
        fname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fnameActionPerformed(evt);
            }
        });
        jPanel1.add(fname, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 240, 310, 50));

        jLabel6.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Last Name:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 110, 30));

        lname.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        lname.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lname.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        lname.setEnabled(false);
        lname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lnameActionPerformed(evt);
            }
        });
        jPanel1.add(lname, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 300, 310, 50));

        jLabel7.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Email:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 110, 30));

        email.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        email.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        email.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });
        jPanel1.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 360, 310, 50));

        contact.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        contact.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        contact.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        contact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contactActionPerformed(evt);
            }
        });
        jPanel1.add(contact, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 420, 310, 50));

        jLabel8.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Type:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 490, 110, 30));

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/register11.png"))); // NOI18N
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel11.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Status:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 610, 100, 30));

        jLabel12.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Password:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 550, 130, 30));

        registernav.setBackground(new java.awt.Color(102, 102, 102));
        registernav.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registernavMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                registernavMouseExited(evt);
            }
        });
        registernav.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        update.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        update.setForeground(new java.awt.Color(51, 51, 51));
        update.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        update.setText("UPDATE");
        update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                updateMouseEntered(evt);
            }
        });
        registernav.add(update, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 130, 20));

        lgnav3.setBackground(new java.awt.Color(204, 255, 255));
        lgnav3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lgnav3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lgnav3MouseExited(evt);
            }
        });
        lgnav3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lgnav4.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        lgnav4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lgnav4.setText("REGISTER");
        lgnav4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lgnav4MouseEntered(evt);
            }
        });
        lgnav3.add(lgnav4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 120, 20));

        registernav.add(lgnav3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 620, 150, 40));

        registernav2.setBackground(new java.awt.Color(204, 255, 255));
        registernav2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registernav2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                registernav2MouseExited(evt);
            }
        });
        registernav2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lgnav8.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        lgnav8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lgnav8.setText("REGISTER");
        lgnav8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lgnav8MouseEntered(evt);
            }
        });
        registernav2.add(lgnav8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 130, 20));

        lgnav9.setBackground(new java.awt.Color(204, 255, 255));
        lgnav9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lgnav9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lgnav9MouseExited(evt);
            }
        });
        lgnav9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lgnav10.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        lgnav10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lgnav10.setText("REGISTER");
        lgnav10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lgnav10MouseEntered(evt);
            }
        });
        lgnav9.add(lgnav10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 120, 20));

        registernav2.add(lgnav9, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 620, 150, 40));

        registernav3.setBackground(new java.awt.Color(204, 255, 255));
        registernav3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registernav3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                registernav3MouseExited(evt);
            }
        });
        registernav3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lgnav11.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        lgnav11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lgnav11.setText("REGISTER");
        lgnav11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lgnav11MouseEntered(evt);
            }
        });
        registernav3.add(lgnav11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 130, 20));

        lgnav12.setBackground(new java.awt.Color(204, 255, 255));
        lgnav12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lgnav12MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lgnav12MouseExited(evt);
            }
        });
        lgnav12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lgnav13.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        lgnav13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lgnav13.setText("REGISTER");
        lgnav13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lgnav13MouseEntered(evt);
            }
        });
        lgnav12.add(lgnav13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 120, 20));

        registernav3.add(lgnav12, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 620, 150, 40));

        registernav2.add(registernav3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 620, 150, 40));

        registernav.add(registernav2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 620, 150, 40));

        registernav4.setBackground(new java.awt.Color(204, 255, 255));
        registernav4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registernav4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                registernav4MouseExited(evt);
            }
        });
        registernav4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lgnav14.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        lgnav14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lgnav14.setText("REGISTER");
        lgnav14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lgnav14MouseEntered(evt);
            }
        });
        registernav4.add(lgnav14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 130, 20));

        lgnav15.setBackground(new java.awt.Color(204, 255, 255));
        lgnav15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lgnav15MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lgnav15MouseExited(evt);
            }
        });
        lgnav15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lgnav16.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        lgnav16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lgnav16.setText("REGISTER");
        lgnav16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lgnav16MouseEntered(evt);
            }
        });
        lgnav15.add(lgnav16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 120, 20));

        registernav4.add(lgnav15, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 620, 150, 40));

        registernav5.setBackground(new java.awt.Color(204, 255, 255));
        registernav5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registernav5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                registernav5MouseExited(evt);
            }
        });
        registernav5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lgnav17.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        lgnav17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lgnav17.setText("REGISTER");
        lgnav17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lgnav17MouseEntered(evt);
            }
        });
        registernav5.add(lgnav17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 130, 20));

        lgnav18.setBackground(new java.awt.Color(204, 255, 255));
        lgnav18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lgnav18MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lgnav18MouseExited(evt);
            }
        });
        lgnav18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lgnav19.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        lgnav19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lgnav19.setText("REGISTER");
        lgnav19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lgnav19MouseEntered(evt);
            }
        });
        lgnav18.add(lgnav19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 120, 20));

        registernav5.add(lgnav18, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 620, 150, 40));

        registernav4.add(registernav5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 620, 150, 40));

        registernav6.setBackground(new java.awt.Color(204, 255, 255));
        registernav6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registernav6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                registernav6MouseExited(evt);
            }
        });
        registernav6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lgnav20.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        lgnav20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lgnav20.setText("REGISTER");
        lgnav20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lgnav20MouseEntered(evt);
            }
        });
        registernav6.add(lgnav20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 130, 20));

        lgnav21.setBackground(new java.awt.Color(204, 255, 255));
        lgnav21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lgnav21MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lgnav21MouseExited(evt);
            }
        });
        lgnav21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lgnav22.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        lgnav22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lgnav22.setText("REGISTER");
        lgnav22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lgnav22MouseEntered(evt);
            }
        });
        lgnav21.add(lgnav22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 120, 20));

        registernav6.add(lgnav21, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 620, 150, 40));

        registernav7.setBackground(new java.awt.Color(204, 255, 255));
        registernav7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registernav7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                registernav7MouseExited(evt);
            }
        });
        registernav7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lgnav23.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        lgnav23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lgnav23.setText("REGISTER");
        lgnav23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lgnav23MouseEntered(evt);
            }
        });
        registernav7.add(lgnav23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 130, 20));

        lgnav24.setBackground(new java.awt.Color(204, 255, 255));
        lgnav24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lgnav24MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lgnav24MouseExited(evt);
            }
        });
        lgnav24.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lgnav25.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        lgnav25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lgnav25.setText("REGISTER");
        lgnav25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lgnav25MouseEntered(evt);
            }
        });
        lgnav24.add(lgnav25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 120, 20));

        registernav7.add(lgnav24, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 620, 150, 40));

        registernav6.add(registernav7, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 620, 150, 40));

        registernav4.add(registernav6, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 620, 150, 40));

        registernav.add(registernav4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 620, 150, 40));

        jPanel1.add(registernav, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 670, 150, 40));

        cancelnav.setBackground(new java.awt.Color(102, 102, 102));
        cancelnav.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cancelnavMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cancelnavMouseExited(evt);
            }
        });
        cancelnav.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CANCEL");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        cancelnav.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 90, 20));

        jPanel1.add(cancelnav, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 670, 150, 40));

        type1.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        type1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Status", "Pending", "Active" }));
        type1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        type1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                type1ActionPerformed(evt);
            }
        });
        jPanel1.add(type1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 600, 310, 50));

        jLabel9.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Contact:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, 110, 30));

        iconhide1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconhide.png"))); // NOI18N
        iconhide1.setText("jLabel1");
        iconhide1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                iconhide1MousePressed(evt);
            }
        });
        jPanel1.add(iconhide1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 550, 30, 30));

        viewicon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/viewicon.png"))); // NOI18N
        viewicon.setText("jLabel1");
        viewicon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                viewiconMousePressed(evt);
            }
        });
        jPanel1.add(viewicon, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 550, 30, 30));

        pass.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        pass.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pass.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        pass.setEchoChar('\u2022');
        pass.setEnabled(false);
        pass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passActionPerformed(evt);
            }
        });
        jPanel1.add(pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 540, 310, 50));

        jLabel13.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("User Name:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 110, 30));

        username.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        username.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        username.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        username.setEnabled(false);
        username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameActionPerformed(evt);
            }
        });
        jPanel1.add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 310, 50));

        type.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Type of User", "Admin", "Applicant", "Scholarship Providers/Committee" }));
        type.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeActionPerformed(evt);
            }
        });
        jPanel1.add(type, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 480, 310, 50));

        jPanel9.setLayout(null);

        image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel9.add(image);
        image.setBounds(10, 10, 320, 280);

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 120, 340, 300));

        select.setBackground(new java.awt.Color(255, 255, 255));
        select.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        select.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        select.setText("SELECT");
        select.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        select.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectMouseClicked(evt);
            }
        });
        jPanel1.add(select, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 430, 150, 40));

        remove.setBackground(new java.awt.Color(255, 255, 255));
        remove.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        remove.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        remove.setText("REMOVE");
        remove.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        remove.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                removeMouseClicked(evt);
            }
        });
        jPanel1.add(remove, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 430, 160, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 845, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 763, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void u_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_u_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_u_idActionPerformed

    private void fnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fnameActionPerformed

    private void lnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lnameActionPerformed

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed
    
    private void contactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_contactActionPerformed

    private void updateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateMouseClicked
   try {
    String passText = pass.getText();
    dbConnect dc = new dbConnect(); 

   
    if (email.getText().isEmpty() || contact.getText().isEmpty() || type.getSelectedIndex() == 0 ||
        passText.isEmpty() || type1.getSelectedIndex() == 0) {
        JOptionPane.showMessageDialog(null, "All fields required");
        return;
    }

   
    if (destination.isEmpty()) {
        File existingFile = new File(oldpath);
        if (existingFile.exists()) { 
            existingFile.delete();
        }
    } else {
        if (!oldpath.equals(path)) { 
            imageUpdater(oldpath, path);
        }
    }

    // ✅ Password validation
    if (passText.length() < 8) {
        JOptionPane.showMessageDialog(null, "Password must be at least 8 characters long");
        return;
    }
    if (!isEmailValid(email.getText())) {
        JOptionPane.showMessageDialog(null, "Invalid email format");
        return;
    }
    if (updatechecker()) {
        System.out.println("Duplicate Existed");
        return;
    }

  
    try (Connection con = dc.getConnection()) {
       
        String currentPass = "";
        String getPasswordQuery = "SELECT pass FROM tbl_user WHERE u_id = ?";
        
        try (PreparedStatement pstmt1 = con.prepareStatement(getPasswordQuery)) {
            pstmt1.setString(1, u_id.getText());
            ResultSet rs = pstmt1.executeQuery();
            
            if (rs.next()) {
                currentPass = rs.getString("pass"); 
            }
        }

      
        String pass1 = passText.equals(currentPass) ? currentPass : passwordHasher.hashPassword(passText);

       
        String query = "UPDATE tbl_user SET email = ?, contact = ?, type = ?, pass = ?, status = ?, image = ? WHERE u_id = ?";
        
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, email.getText());
            pstmt.setString(2, contact.getText());
            pstmt.setString(3, (String) type.getSelectedItem());
            pstmt.setString(4, pass1);
            pstmt.setString(5, (String) type1.getSelectedItem());
            pstmt.setString(6, destination); 
            pstmt.setString(7, u_id.getText());

            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "User updated successfully!");
                
               
                int userId = Integer.parseInt(u_id.getText()); 
                dc.insertLog(userId, "Admin updated user with ID: " + userId);
            } else {
                JOptionPane.showMessageDialog(null, "User update failed!");
            }
        }
    }

 
    usersTable ut = new usersTable();
    ut.setVisible(true);
    this.dispose();

} catch (Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(null, "Unexpected error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
}

        
    }//GEN-LAST:event_updateMouseClicked

    private void updateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateMouseEntered

    }//GEN-LAST:event_updateMouseEntered

    private void lgnav4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lgnav4MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lgnav4MouseEntered

    private void lgnav3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lgnav3MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lgnav3MouseEntered

    private void lgnav3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lgnav3MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_lgnav3MouseExited

    private void lgnav8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lgnav8MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lgnav8MouseEntered

    private void lgnav10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lgnav10MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lgnav10MouseEntered

    private void lgnav9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lgnav9MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lgnav9MouseEntered

    private void lgnav9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lgnav9MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_lgnav9MouseExited

    private void lgnav11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lgnav11MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lgnav11MouseEntered

    private void lgnav13MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lgnav13MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lgnav13MouseEntered

    private void lgnav12MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lgnav12MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lgnav12MouseEntered

    private void lgnav12MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lgnav12MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_lgnav12MouseExited

    private void registernav3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registernav3MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_registernav3MouseEntered

    private void registernav3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registernav3MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_registernav3MouseExited

    private void registernav2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registernav2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_registernav2MouseEntered

    private void registernav2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registernav2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_registernav2MouseExited

    private void lgnav14MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lgnav14MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lgnav14MouseEntered

    private void lgnav16MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lgnav16MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lgnav16MouseEntered

    private void lgnav15MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lgnav15MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lgnav15MouseEntered

    private void lgnav15MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lgnav15MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_lgnav15MouseExited

    private void lgnav17MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lgnav17MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lgnav17MouseEntered

    private void lgnav19MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lgnav19MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lgnav19MouseEntered

    private void lgnav18MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lgnav18MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lgnav18MouseEntered

    private void lgnav18MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lgnav18MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_lgnav18MouseExited

    private void registernav5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registernav5MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_registernav5MouseEntered

    private void registernav5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registernav5MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_registernav5MouseExited

    private void lgnav20MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lgnav20MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lgnav20MouseEntered

    private void lgnav22MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lgnav22MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lgnav22MouseEntered

    private void lgnav21MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lgnav21MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lgnav21MouseEntered

    private void lgnav21MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lgnav21MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_lgnav21MouseExited

    private void lgnav23MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lgnav23MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lgnav23MouseEntered

    private void lgnav25MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lgnav25MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lgnav25MouseEntered

    private void lgnav24MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lgnav24MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lgnav24MouseEntered

    private void lgnav24MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lgnav24MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_lgnav24MouseExited

    private void registernav7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registernav7MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_registernav7MouseEntered

    private void registernav7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registernav7MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_registernav7MouseExited

    private void registernav6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registernav6MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_registernav6MouseEntered

    private void registernav6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registernav6MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_registernav6MouseExited

    private void registernav4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registernav4MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_registernav4MouseEntered

    private void registernav4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registernav4MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_registernav4MouseExited

    private void registernavMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registernavMouseEntered
        registernav.setBackground(hover);
    }//GEN-LAST:event_registernavMouseEntered

    private void registernavMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registernavMouseExited
        registernav.setBackground(defaultcolor);
    }//GEN-LAST:event_registernavMouseExited

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
      usersTable ut = new usersTable();
      ut.setVisible(true);
      this.dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void cancelnavMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelnavMouseEntered
        cancelnav.setBackground(hover);
    }//GEN-LAST:event_cancelnavMouseEntered

    private void cancelnavMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelnavMouseExited
        cancelnav.setBackground(defaultcolor);
    }//GEN-LAST:event_cancelnavMouseExited

    private void type1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_type1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_type1ActionPerformed

    private void viewiconMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewiconMousePressed
        viewicon.setVisible(false);
        iconhide1.setVisible(true);
        pass.setEchoChar('•');
    }//GEN-LAST:event_viewiconMousePressed

    private void passActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passActionPerformed

    private void iconhide1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconhide1MousePressed

        viewicon.setVisible(true);
        iconhide1.setVisible(false);
        pass.setEchoChar((char)0);
    }//GEN-LAST:event_iconhide1MousePressed

    private void usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameActionPerformed

    private void typeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_typeActionPerformed

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
            java.util.logging.Logger.getLogger(updateForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(updateForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(updateForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(updateForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new updateForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel cancelnav;
    public javax.swing.JTextField contact;
    public javax.swing.JTextField email;
    public javax.swing.JTextField fname;
    private javax.swing.JLabel iconhide1;
    public javax.swing.JLabel image;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lgnav10;
    private javax.swing.JLabel lgnav11;
    private javax.swing.JPanel lgnav12;
    private javax.swing.JLabel lgnav13;
    private javax.swing.JLabel lgnav14;
    private javax.swing.JPanel lgnav15;
    private javax.swing.JLabel lgnav16;
    private javax.swing.JLabel lgnav17;
    private javax.swing.JPanel lgnav18;
    private javax.swing.JLabel lgnav19;
    private javax.swing.JLabel lgnav20;
    private javax.swing.JPanel lgnav21;
    private javax.swing.JLabel lgnav22;
    private javax.swing.JLabel lgnav23;
    private javax.swing.JPanel lgnav24;
    private javax.swing.JLabel lgnav25;
    private javax.swing.JPanel lgnav3;
    private javax.swing.JLabel lgnav4;
    private javax.swing.JLabel lgnav8;
    private javax.swing.JPanel lgnav9;
    public javax.swing.JTextField lname;
    public javax.swing.JPasswordField pass;
    private javax.swing.JPanel registernav;
    private javax.swing.JPanel registernav2;
    private javax.swing.JPanel registernav3;
    private javax.swing.JPanel registernav4;
    private javax.swing.JPanel registernav5;
    private javax.swing.JPanel registernav6;
    private javax.swing.JPanel registernav7;
    public javax.swing.JLabel remove;
    public javax.swing.JLabel select;
    public javax.swing.JComboBox<String> type;
    public javax.swing.JComboBox<String> type1;
    public javax.swing.JTextField u_id;
    private javax.swing.JLabel update;
    public javax.swing.JTextField username;
    private javax.swing.JLabel viewicon;
    // End of variables declaration//GEN-END:variables
}
