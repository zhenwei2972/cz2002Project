import java.util.stream.Collectors;
import java.util.List;

public class LoginManager {
    
    @FunctionalInterface
    interface Authentication {
        public boolean Invoke(String username, String password);
    }

    Authentication studentLogin = (studID, studPassword) -> {
        Controller ctrl = new Controller();
        List<Student> fullStudentList = ctrl.StudentDatabase();

        List<Student> checkLogin = fullStudentList.stream()
                .filter(x -> studID.equals(x.getUsername()) && studPassword.equals(x.getPassword()))
                .collect(Collectors.toList());
        return (checkLogin.size() > 0) ? true : false;
    };

    Authentication adminLogin = (adminID, adminPassword) -> {
        Controller ctrl = new Controller();
        List<Admin> fullAdminList = ctrl.AdminUserDatabase();

        List<Admin> checkLogin = fullAdminList.stream()
                .filter(x -> adminID.equals(x.getUsername()) && adminPassword.equals(x.getPassword()))
                .collect(Collectors.toList());
        return (checkLogin.size() > 0) ? true : false;
    };

}