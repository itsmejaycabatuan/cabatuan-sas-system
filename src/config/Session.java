/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

/**
 *
 * @author user
 */
public class Session {
    
    private static Session instance;
      private int user_id;
    private String fname;
    private String lname;
    private String email;
    private String username;
    private String type;
    private String status;
    private String contact;
    private String password;
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
   

   
        private Session(){
            //private.;con
        }

    public static synchronized Session getInstance() {
       if(instance == null){
           instance = new Session();
       } 
        return instance;
    }

    public static boolean isInstanceEmpty() {
      return instance == null;
    }
     public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
     public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
        
    
       public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
        
           
}
