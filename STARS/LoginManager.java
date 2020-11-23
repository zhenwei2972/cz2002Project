import java.util.stream.Collectors;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
public class LoginManager {
    
    @FunctionalInterface
    interface Authentication {
        public boolean Invoke(String username, String password);
    }
    Authentication studentLogin = (studID, studPassword) -> {
        Database ctrl = new Database();
        List<Student> fullStudentList = ctrl.StudentDatabase();

        List<Student> checkLogin = fullStudentList.stream()
                .filter(x -> studID.equals(x.getUsername()) && studPassword.equals(x.getPassword()))
                .collect(Collectors.toList());
       
        return (checkLogin.size() > 0) ? true : false;
    };

    public AccessPeriod GetAccessPeriod(String year, List<AccessPeriod> accessPeriods) {
        List<AccessPeriod> result = new ArrayList<AccessPeriod>();
        result =accessPeriods.stream().filter(x -> year.equals(x.getYear())).collect(Collectors.toList());
        for (AccessPeriod ap : result) {
            return ap;
        }
        return null;
    }
     boolean validateAccessPeriod(AccessPeriod ap){
        return ap.AccessPeriodCheck();
    }

    Authentication adminLogin = (adminID, adminPassword) -> {
        Database ctrl = new Database();
        List<Admin> fullAdminList = ctrl.AdminUserDatabase();

        List<Admin> checkLogin = fullAdminList.stream()
                .filter(x -> adminID.equals(x.getUsername()) && adminPassword.equals(x.getPassword()))
                .collect(Collectors.toList());
        return (checkLogin.size() > 0) ? true : false;
    };

}