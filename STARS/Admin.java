/**
 * Admin class that implements users
 * and object that is admin account
 * @author Team Stars
 * @version 1.0
 * @since 2020
 */
public class Admin implements User{
    /**
     * Username as the unique value
     * password as the password for admin
     */
    String username, password;

    /**
     * Admin constructor
     * @param username as the username for admin account
     * @param password as the password for admin account
     */
    public Admin(String username, String password){
        this.username = username;
        this.password = password;
    }
    /**
     * return the username of admin object
     */
    public String getUsername(){
        return username;
    }
    /**
     * return the password of the admin object
     */
    public String getPassword(){
        return password;
    }
    /**
     * setting the username of admin object'
     * @param username as the new username
     */
    @Override
    public void setUsername(String username){}
    /**
     * setting the password of admin object
     * @param password as the new password
     */
    public void setPassword(String password){}

    
}