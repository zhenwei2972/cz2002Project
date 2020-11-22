import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminInterface {
    public void AdminMenuLogic() throws IOException {
        String num = "";
        Console console = System.console();
        System.out.println("Welcome to Admin Mode");
        AdminManager adminController = new AdminManager();
        Database db = new Database();
        List<Student> fullStudentList = db.StudentDatabase();
        Boolean quit = false;
        Scanner sc = new Scanner(System.in);
        int courseVacancy;
        StudentManager studentMgmt = new StudentManager();
        List<Course> courses = db.CourseListDatabase();
        while (!quit)
        {
            System.out.println(
            " 1 Edit student access period \n 2 Add Student Records \n 3 Removing A Student \n 4 Check available slot by index number \n 5 Print student list by index number \n 6 Print student list by course");
            num = console.readLine("Please choose your action \n");
            switch (num) {
                case "1":
                    System.out.println("\nEdit student access period");
                    break;
                case "2":
                    System.out.println("\nAdding a new Student");
                    // need to populate with new student list 
                    adminController.addStudent(fullStudentList);
                    System.out.println("\n Added a new Student");
                    break;
                case "3":
                    System.out.println("\nRemoving a current Student");
                    System.out.println("Enter the Matric Number of Student to be removed");
                    String matricNo = sc.next();
                    adminController.removeStudent(fullStudentList, matricNo);
                    System.out.println("\nRemoved "+matricNo+" Student");
                    break;
                case "4":
                    System.out.println("\nCheck available slot by index number");
                    System.out.println("Enter index number");
                    String courseIndex = sc.next();
                    Course currentCourse = studentMgmt.GetCourse(Integer.parseInt(courseIndex), courses);
                    courseVacancy = currentCourse.getVacancy();
                    System.out.println("Course Vacancy for " + courseIndex + " is " + courseVacancy);
                    break;
                case "5":
                    System.out.println("\nDisplay Students by course index");
                    System.out.println("Enter the Course Index of Students to be printed");
                    String indexString = sc.next();
                    adminController.printStudentfromIndex.Invoke(fullStudentList,indexString);
                    break;
                case "6":
                    System.out.println("\nDisplay Students by Course Code");
                    System.out.println("Enter the Course Code of Students to be printed");
                    String courseCode = sc.next();
                    adminController.printStudentfromIndex.Invoke(fullStudentList,courseCode);
                    break;
                case "7":
                    db.UpdateStudentDatabase(fullStudentList);
                    //db.UpdateCourseDatabase(fullCourseList);
                    break;
                case "8":
                    System.out.println("\nQuit");
                    quit=true;
                default:
            }
          
        }
        sc.close();
    }
    
 
}