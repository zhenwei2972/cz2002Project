package STARS;
import java.io.Console;

public class Login {
    Console console = System.console();
    String ID ="";
    String PW ="";
    User loginUser = new User();
    Boolean logged_in = false;
    public void LoginMenu(){
        
        ID=console.readLine("Please enter your username: ");
        char[] password = console.readPassword("Please enter your password: ");  
        //convert char to string
        PW =String.valueOf(password);
        logged_in =Attempt_Login(ID,PW);
        while(!GetLoginStatus())
        {
            LoginMenu();
        }
        
        
    }
    public User ReturnUser()
    {
       return loginUser;
    }
    
    public boolean Attempt_Login(String input_id, String input_pw){
        if(input_id.equals(loginUser.getUsername()))
        {
            if(input_pw.equals(loginUser.getPassword()))
            {
                System.out.println("Login Success");
            
                return true;
            }
            else
            {
                System.out.println("Wrong PW");
                return false;
            }
        }
        else
        {
            System.out.println("Wrong ID");
            return false;
        }

    }
    public boolean GetLoginStatus(){
        return logged_in;
    }
}
