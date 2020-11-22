import java.io.IOException;
import java.util.Scanner;

public class LoginInterface {
    Database ctrl = new Database();

    public void StartupInterface() throws IOException {

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
        System.out.println("Enter Student Password:");
        String studPassword = sc.next();

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

    public void AdminLoginInterface() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Login as Admin:");
        System.out.println("Enter Admin Username:");
        String adminUsername = sc.next();
        System.out.println("Enter Admin Password:");
        String adminPassword = sc.next();

        LoginManager loginManager = new LoginManager();
        boolean login = loginManager.adminLogin.Invoke(adminUsername, adminPassword);
        if(login){
            AdminInterface homePage = new AdminInterface();
            homePage.AdminMenuLogic();
        }
        else{
            System.out.println("Wrong Username or password! Please enter details again");
            AdminLoginInterface();
        }
        sc.close();
    }

}