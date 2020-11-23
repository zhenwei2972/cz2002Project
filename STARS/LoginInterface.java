import java.io.Console;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class LoginInterface {
    Database ctrl = new Database();
    List<AccessPeriod> accessPeriodList = ctrl.AccessPeriodDatabase();
    List<Student> fullStudentList = ctrl.StudentDatabase();

    
    public String readPassword() {
        Console console = System.console();
        String enteredPassword = new String(console.readPassword("Please enter your password: "));
        return enteredPassword;
    }

    public void StartupInterface() throws IOException, ParseException {
        /*
        AccessPeriod ap2020 = new AccessPeriod("2020");
        AccessPeriod ap2019 = new AccessPeriod("2019");
        accessPeriodList.add(ap2019);
        accessPeriodList.add(ap2020);
        */
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Option 1: Login as Student");
        System.out.println("Option 2: Login as Admin");
        System.out.println("Select Option: ");
        int option = sc.nextInt();
        switch (option) {
            case 1:
                StudentLoginInterface();
            case 2:
                AdminLoginInterface();
            default:
                StartupInterface();
        }
        sc.close();
    }

    public void StudentLoginInterface() throws IOException {
        //initialize acccess period list
       
        Scanner sc = new Scanner(System.in);
        System.out.println("Login as Student:");
        System.out.println("Enter Student Username:");
        String studUsername = sc.next();
        String studPassword = readPassword();
        LoginManager loginManager = new LoginManager();
        boolean login = loginManager.studentLogin.Invoke(studUsername, studPassword);
        if (login) 
        {
            // construct temporary student object with dummy matrix number
            StudentManager sm = new StudentManager();
            Student studentObject = sm.GetStudentByUserName(studUsername, fullStudentList);
            StudentInterface homePage = new StudentInterface();
            AccessPeriod currentAP = loginManager.GetAccessPeriod(Integer.toString(studentObject.getYear()), accessPeriodList);
            if(currentAP==null)
            {
                System.out.println("Access period is not set, unable to login ");
                StudentLoginInterface();
            }
            else
            {
                boolean validAccessTime = loginManager.validateAccessPeriod(currentAP);
                System.out.println(accessPeriodList);
                if(validAccessTime)
                {
                    homePage.StudentMenuLogic(studentObject, ctrl);
                }
                else{
                    System.out.println("Access period is not valid you should login on "+currentAP.getStartDate()+" to "+currentAP.getEndDate());
                }
            }
        } 
        else 
        {
            System.out.println("Wrong Username or password! Please enter details again");
            StudentLoginInterface();
        }
       
    }

    public void AdminLoginInterface() throws IOException, ParseException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Login as Admin:");
        System.out.println("Enter Admin Username:");
        String adminUsername = sc.next();
        String adminPassword = readPassword();
        sc.close();
        LoginManager loginManager = new LoginManager();
        boolean login = loginManager.adminLogin.Invoke(adminUsername, adminPassword);
        if (login) {
            AdminInterface homePage = new AdminInterface();
            homePage.AdminMenuLogic(accessPeriodList);
        } else {
            System.out.println("Wrong Username or password! Please enter details again");
            AdminLoginInterface();
        }
    }

}