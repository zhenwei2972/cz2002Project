import java.io.Console;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class LoginInterface {
    Database ctrl = new Database();

    public String readPassword() {
        Console console = System.console();
        String enteredPassword = new String(console.readPassword("Please enter your password: "));
        return enteredPassword;
    }

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
        sc.close();
    }

    public void StudentLoginInterface() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Login as Student:");
        System.out.println("Enter Student Username:");
        String studUsername = sc.next();
        String studPassword = readPassword();

        LoginManager loginManager = new LoginManager();
        boolean login = loginManager.studentLogin.Invoke(studUsername, studPassword);
        if (login) {
            // construct temporary student object with dummy matrix number
            String dummymatric = "U1920129E";
            Student StudentController = new Student(dummymatric, studUsername, studPassword);
            // studentFilter.byMatricNo.Invoke(students, Integer.toString(matricNo));
            StudentInterface homePage = new StudentInterface();
            homePage.StudentMenuLogic(StudentController, ctrl);
        } else {
            System.out.println("Wrong Username or password! Please enter details again");
            StudentLoginInterface();
        }
        sc.close();
    }

    public void AdminLoginInterface() throws IOException, ParseException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Login as Admin:");
        System.out.println("Enter Admin Username:");
        String adminUsername = sc.next();
        String adminPassword = readPassword();

        LoginManager loginManager = new LoginManager();
        boolean login = loginManager.adminLogin.Invoke(adminUsername, adminPassword);
        if (login) {
            AdminInterface homePage = new AdminInterface();
            homePage.AdminMenuLogic();
        } else {
            System.out.println("Wrong Username or password! Please enter details again");
            AdminLoginInterface();
        }
        sc.close();
    }

}