public class Admin implements User{
    String username, password;
    public Admin(String username, String password){
        this.username = username;
        this.password = password;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    @Override
    public void setUsername(String username){}
    public void setPassword(String password){}

    
}