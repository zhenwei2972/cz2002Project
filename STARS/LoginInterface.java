import java.io.Console;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;
/**
 * This is the LoginInterface.
 * it handles the menu of the login page
 * @author Team Stars
 * @version 1.0
 * @since 2020
 */
public class LoginInterface {
    /**
     * requires the database to load
     */
    Database ctrl = new Database();
    /**
     * to load in data for student accounts
     */
    List<Student> fullStudentList = ctrl.StudentDatabase();
    /**
     * to load in data for Admin accounts
     */
    List<AccessPeriod> accessPeriodList = ctrl.AccessPeriodDatabase();
    
    WaitList waitList = new WaitList();

    List<Course> allCourses =   ctrl.CourseListDatabase();

    List<Lesson> lessonList = ctrl.LessonListDatabase();
    
    /** 
     * to pass the in the password to the system
     * @return String User password keyed in
     */
    public String readPassword() {
        Console console = System.console();
        String enteredPassword = new String(console.readPassword("Please enter your password: "));
        return enteredPassword;
    }

    
    /** 
     * Print out the menu of the login page
     * @throws IOException for loading the datas
     * @throws ParseException for casting or parsing the data keyed
     */
    public void StartupInterface() throws IOException, ParseException {
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
    }

    
    /** 
     * print for student's login menu
     * @throws IOException to catch the load expception
     */
    public void StudentLoginInterface() throws IOException {
        //initialize acccess period list
       
        Scanner sc = new Scanner(System.in);
        System.out.println("Login as Student:");
        System.out.println("Enter Student Username:");
        String studUsername = sc.next();
        String studPassword = readPassword();
        LoginManager loginManager = new LoginManager();
        studPassword = loginManager.passwordHashing(studPassword);
        boolean login = loginManager.studentLogin.Invoke(studUsername, studPassword);
        if (login) 
        {
            // construct temporary student object with dummy matrix number
            StudentManager sm = new StudentManager();
            Student studentObject = sm.GetStudentByUserName(studUsername, fullStudentList);
            StudentInterface homePage = new StudentInterface(fullStudentList,allCourses,waitList);
            AccessPeriod currentAP = loginManager.GetAccessPeriod(Integer.toString(studentObject.getYear()), accessPeriodList);
            if(currentAP==null)
            {
                System.out.println("Access period is not set, unable to login ");
                StudentLoginInterface();
            }
            else
            {
                boolean validAccessTime = loginManager.validateAccessPeriod(currentAP);
                if(validAccessTime)
                {
                    homePage.StudentMenuLogic(studentObject, ctrl);
                }
                else{
                    System.out.println("Access period is not valid you should login on "+currentAP.getStartDate()+" to "+currentAP.getEndDate());
                    StudentLoginInterface();
                }
            }
        } 
        else 
        {
            System.out.println("Wrong Username or password! Please enter details again");
            StudentLoginInterface();
        }
       
    }

    
    /** 
     * Print the admin login menu
     * @throws IOException to catch database load fail
     * @throws ParseException to catch casting or parsing fail
     */
    public void AdminLoginInterface() throws IOException, ParseException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Login as Admin:");
        System.out.println("Enter Admin Username:");
        String adminUsername = sc.next();
        String adminPassword = readPassword();
        LoginManager loginManager = new LoginManager();
        adminPassword = loginManager.passwordHashing(adminPassword);
        boolean login = loginManager.adminLogin.Invoke(adminUsername, adminPassword);
        if (login) {
            AdminInterface homePage = new AdminInterface(fullStudentList,allCourses,lessonList);
            homePage.AdminMenuLogic(accessPeriodList);
        } else {
            System.out.println("Wrong Username or password! Please enter details again");
            AdminLoginInterface();
        }
    }

}