import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;

public class Test {
    interface Authentication {
        public boolean Invoke(List<Admin> list ,String username, String password);
    }

    public static void main(String args[]) {
        Controller ctrl = new Controller();
        List<Admin> fullAdminList = ctrl.AdminUserDatabase();
        List<Module> fullModuleList = ctrl.ModuleListDatabase();
        
        // Authentication loginAuthentication = (adminList, username, password) -> {
        //     List<Admin> checkLogin = adminList.stream().filter(x -> username.equals(x.getUsername()) && password.equals(x.getPassword())).collect(Collectors.toList());
        //     return (checkLogin.size() > 0) ? true : false;
        // };

        // boolean login1 = loginAuthentication.Invoke(fullAdminList, "iskandar", "123");
        // boolean login2 = loginAuthentication.Invoke(fullAdminList, "iskandar", "1234");
        // System.out.println(login1 + " Means Login pass");
        // System.out.println(login2 +" Means Login failed");

        Filter filters = new Filter();
        List<Module> filterResult = new ArrayList<Module>();

        filterResult = filters.byModuleCode.Invoke(fullModuleList, "CZ2002");
        filters.toDisplayResult.Display(filterResult);
    }
}
