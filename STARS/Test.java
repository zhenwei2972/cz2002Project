import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Test {
    // interface Authentication {
    //     public boolean Invoke(List<Admin> list ,String username, String password);
    // }

    public static void main(String args[]) throws Exception{
        // Controller ctrl = new Controller();
        // List<Admin> fullAdminList = ctrl.AdminUserDatabase();
        // List<Module> fullModuleList = ctrl.ModuleListDatabase();
        
        //  Authentication loginAuthentication = (adminList, username, password) -> {
        //      List<Admin> checkLogin = adminList.stream().filter(x -> username.equals(x.getUsername()) && password.equals(x.getPassword())).collect(Collectors.toList());
        //      return (checkLogin.size() > 0) ? true : false;
        //  };

        //  boolean login1 = loginAuthentication.Invoke(fullAdminList, "iskandar", "123");
        // // boolean login2 = loginAuthentication.Invoke(fullAdminList, "iskandar", "1234");
        //  System.out.println(login1 + " Means Login pass");
        // // System.out.println(login2 +" Means Login failed");

        // Filter filters = new Filter();
        // List<Module> filterResult = new ArrayList<Module>();

        // filterResult = filters.byModuleCode.Invoke(fullModuleList, "CZ2002");
        // filters.toDisplayResult.Display(filterResult);
        
        Student a = new Student("U1920129E","chienyq","12345");
        Student b = new Student("U5856210F","student3","12345");
        Course test = new Course(10214,"CZ2003","Computer Graphics",3,"Wednesday",1030,1230,"Tutorial",5,0);
        Course test2 = new Course(10219,"CZ2003","Computer Graphics",3,"Wednesday",1030,1230,"Tutorial",5,0);
        StudControl studcontrol = new StudControl();
        a.AddCourse(test);
        b.AddCourse(test2);
        for (Course c: a.getCourse()){
            System.out.println(c.index + " | " + a.getUsername());
        }
        for (Course c: b.getCourse()){
            System.out.println(c.index + " | " + b.getUsername());
        }
        studcontrol.SwapCourse(a, b, test, 10218);

        for (Course c: a.getCourse()){
            System.out.println(c.index + " | " + a.getUsername());
        }
        for (Course c: b.getCourse()){
            System.out.println(c.index + " | " + b.getUsername());
        }


    //    String strTime = "20:15:40";
    //    DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
    //    Date d = dateFormat.parse(strTime);
    //    System.out.println("Resultant Date and Time = " + d);

            
    }
}
