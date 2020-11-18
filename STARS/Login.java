
import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public class Login {
    interface Authentication {
        public boolean Invoke(List<Admin> list ,String username, String password);
    }
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
        Controller ctrl = new Controller();
        List<Admin> fullAdminList = ctrl.AdminUserDatabase();
        List<Module> fullModuleList = ctrl.ModuleListDatabase();
        
         Authentication loginAuthentication = (adminList, username, password) -> {
             List<Admin> checkLogin = adminList.stream().filter(x -> username.equals(x.getUsername()) && password.equals(x.getPassword())).collect(Collectors.toList());
             return (checkLogin.size() > 0) ? true : false;
         };

         return  loginAuthentication.Invoke(fullAdminList, input_id, input_pw);

    }
    public boolean GetLoginStatus(){
        return logged_in;
    }
}
