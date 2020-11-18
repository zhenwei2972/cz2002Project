

public class User {
    String username ="test";
    String password ="1234";
    Boolean Staff =true;
    public void setUserName(String inputID){
        username = inputID;
    }
    public void setPassword(String inputPW)
    {
        password = inputPW;
    }
    public void setStaff(Boolean isStaff)
    {
        Staff = isStaff;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public Boolean getStaff(){
        return Staff;
    }
}
