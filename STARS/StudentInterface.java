

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class StudentInterface {
    public void StudentMenuLogic() {
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

  
}