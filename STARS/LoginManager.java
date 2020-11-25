import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
public class LoginManager {
     /**
     * a Functional interface is not other than a abstract method, which we are able polymorph into different methods
     * This method only works on Java 8 onwards
     * interface for authentication
     */
    @FunctionalInterface
    interface Authentication {
        public boolean Invoke(String username, String password);
    }
    /**
     * function for Authentication student login
     * @param studID as the username
     * @param studPassword as the password
     */
    Authentication studentLogin = (studID, studPassword) -> {
        Database ctrl = new Database();
        List<Student> fullStudentList = ctrl.StudentDatabase();

        List<Student> checkLogin = fullStudentList.stream()
                .filter(x -> studID.equals(x.getUsername()) && studPassword.equals(x.getPassword()))
                .collect(Collectors.toList());
       
        return (checkLogin.size() > 0) ? true : false;
    };

    
    /** 
     * function to check issit within the accessperiod
     * @param year as the year of the student
     * @param accessPeriods as the list of available access periods
     * @return object AccessPeriod 
     */
    public AccessPeriod GetAccessPeriod(String year, List<AccessPeriod> accessPeriods) {
        List<AccessPeriod> result = new ArrayList<AccessPeriod>();
        result =accessPeriods.stream().filter(x -> year.equals(x.getYear())).collect(Collectors.toList());
        for (AccessPeriod ap : result) {
            return ap;
        }
        return null;
    }
     
     /** 
      * Function for Valdating Access period
      * @param ap as the object acessperiod
      * @return boolean for within acess period
      */
     boolean validateAccessPeriod(AccessPeriod ap){
        return ap.AccessPeriodCheck();
    }
    /**
     * function for admin authenication 
     * @param adminID as the username
     * @param adminPassword as the password
     */
    Authentication adminLogin = (adminID, adminPassword) -> {
        Database ctrl = new Database();
        List<Admin> fullAdminList = ctrl.AdminUserDatabase();

        List<Admin> checkLogin = fullAdminList.stream()
                .filter(x -> adminID.equals(x.getUsername()) && adminPassword.equals(x.getPassword()))
                .collect(Collectors.toList());
        return (checkLogin.size() > 0) ? true : false;
    };

    
    /** 
     * to update the acess period
     * @param year as the new year value
     * @param accessPeriodList as the entire accessperiodlist to add on
     * @param startDateTime as when the accessperiod is available
     * @param endDateTime as when the closed time for available period
     */
    public void updateAccessPeriod(String year,List<AccessPeriod> accessPeriodList,Date startDateTime,Date endDateTime){
        AccessPeriod currentAP = GetAccessPeriod(year, accessPeriodList);
        System.out.println("Updated access period for "+year+" from "+startDateTime+" to "+endDateTime);
        currentAP.setStartDate(startDateTime);
        currentAP.setEndDate(endDateTime);

    }

}