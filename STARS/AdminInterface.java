import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class AdminInterface {
    public void AdminMenuLogic() {
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
 
}