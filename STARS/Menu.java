package STARS;

import java.io.Console;
import java.util.ArrayList;

public class Menu {
    public static void AdminMenuLogic() {
        String num = "";
        Console console = System.console();
        System.out.println("Welcome to Admin Mode");
        System.out.println(
                " 1 Edit student access period \n 2 Add Student Records \n 3 Add/Update a course \n 4 Check available slot for an index number \n 5 Print student list by index number \n 6 Print student list by course");
        num = console.readLine("Please choose your action \n");
        switch (num) {
            case "1":
                System.out.println("\nChoose a student, Modify his access period");
                break;
            case "2":
                System.out.println("\nEnter a student name");
                break;
            case "3":
                System.out.println("\nEnter a student name");
                break;
            case "4":
                System.out.println("\nEnter a student name");
                break;
            case "5":
                System.out.println("\nEnter a student name");
                break;
            case "6":
                System.out.println("\nEnter a student name");
                break;
            default:
        }
    }

    public static void StudentMenuLogic() {
        String num = "";
        Console console = System.console();
        System.out.println("Welcome to Student Mode");
        System.out.println(
                " 1 *Add Course \n 2 Drop Course \n 3 Check/Print Courses Registered \n 4 Check Vacancies Available \n 5 Change Index Number of Course \n 6 Swop Index Number with Another Student ");
        num = console.readLine("Please choose your action \n");
        switch (num) {
            case "1":
                System.out.println("\nTry to Add");
                break;
            case "2":
                System.out.println("\nEnter a student name");
                break;
            case "3":
                System.out.println("\nEnter a student name");
                break;
            case "4":
                System.out.println("\nEnter a student name");
                break;
            case "5":
                System.out.println("\nEnter a student name");
                break;
            case "6":
                System.out.println("\nEnter a student name");
                break;
            default:
        }
    }

    public static void main(String[] args) {

        System.out.println("Welcome to MySTARs System");
        Login loginObject = new Login();
        loginObject.LoginMenu();

        // To Zhenwei
        // Column format
        // index, coursecode, coursename, AU, day, starttime, endtime, classtype, vacancy
        Controller ctrl = new Controller();
        ArrayList<Module> fullModuleList = ctrl.ImportAllModules();

        //Filter System Parameters
        // Invoke(ArrayList<Module> list, String value); [If it's int put '.toString()' at the back]
        // Filter filters = new Filter();
        // List<Module> filterResult = new ArrayList<Module>();

        // filterResult = filters.byModuleCode.Invoke(fullModuleList, "CZ2002");
        // filters.toDisplayResult.Display(filterResult);

        
        // filterResult = filters.byIndex.Invoke(fullModuleList, "10214");
        // filters.toDisplayResult.Display(filterResult);


        // filterResult = filters.byDays.Invoke(fullModuleList, "Wednesday");
        // filters.toDisplayResult.Display(filterResult);

        if (loginObject.GetLoginStatus()) {
            // if is staff show staff menu
            if (loginObject.ReturnUser().getStaff()) {
                AdminMenuLogic();

            }
            // if student show student menu
            else if (loginObject.ReturnUser().getStaff() == false) {
                StudentMenuLogic();
            }
        }
    }

}