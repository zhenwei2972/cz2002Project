import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class AdminInterface {
    Scanner sc = new Scanner(System.in);
    public void AdminMenuLogic() throws IOException {
        String num = "";
        Console console = System.console();
        System.out.println("Welcome to Admin Mode");
        AdminManager adminController = new AdminManager();
        Database db = new Database();
        List<Student> fullStudentList = db.StudentDatabase();
        Boolean quit = false;
        
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
                    System.out.println("\nEdit student access period by date and time");
                    System.out.println("\n Enter Access Period Start Date dd MMMM, yyyy HH:mm");
                    Date startDate =InputDateTime();
                    System.out.println("\n Enter Access Period End Date DD/MM/YYYY");   
                    Date endDate =InputDateTime();


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
                    db.UpdateCourseDatabase(courses);
                    break;
                case "8":
                    System.out.println("\nQuit");
                    quit=true;
                default:
            }
          
        }
        sc.close();
    }
    private Date InputDateTime(){

        System.out.println("\nEnter Day i.e 01");
        String day = sc.next();
        System.out.println("\nEnter Month i.e (JAN)");
        String month = sc.next();
        System.out.println("\nEnter Year i.e 2002");
        String year = sc.next();
        System.out.println("\n Enter Hour i.e 12");
        String hour = sc.next();
        System.out.println("\n Enter Minute i.e 15");
        String min = sc.next();
        String dateInString = month+" "+day+", "+year+" "+hour+":"+min;
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy HH:mm");
       // String example = "Jun 7, 2013 12:10";

        try {
            Date date = formatter.parse(dateInString);
          //  System.out.println(date);
            System.out.println(formatter.format(date));
            return date;

        } catch (ParseException e) {
            System.out.println("Incorect input");
            e.printStackTrace();
        }
        return null;
    }
    
 
}
