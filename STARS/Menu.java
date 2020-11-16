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

    //Read data from file, this method must be called when application starts
    public static ArrayList<Module> DownloadModuleList() {
        // Data Column
        // index,coursecode,coursename,AU,day,starttime,endtime,classtype,vacancy
        ArrayList<Module> fullmodulelist = new ArrayList<Module>();
        Controller ctrl = new Controller();
        ArrayList<String> data = ctrl.ImportData();

        // Insert into module's arraylist
        for (String i : data) {
            String[] splitdata = i.split(",");
            fullmodulelist.add(new Module(Integer.parseInt(splitdata[0]), splitdata[1], splitdata[2],
                    Integer.parseInt(splitdata[3]), splitdata[4], splitdata[5], splitdata[6], splitdata[7],
                    Integer.parseInt(splitdata[8])));
        }
        return fullmodulelist;
        //Testing Purpose
        // for (Module x : fullmodulelist) {
        //     System.out.println(x.coursename);
        // }

    }

    public static void main(String[] args) {

        System.out.println("Welcome to MySTARs System");
        Login loginObject = new Login();
        loginObject.LoginMenu();
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