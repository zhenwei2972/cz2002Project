import java.io.Console;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import java.text.ParseException;
import java.util.Date;
public class AdminInterface {
    Scanner sc = new Scanner(System.in);
    public void AdminMenuLogic(List<AccessPeriod> accessPeriodList) throws IOException, ParseException {
        String num = "";
        Console console = System.console();
        System.out.println("Welcome to Admin Mode");
        AdminManager adminController = new AdminManager();
        Database db = new Database();
        List<Student> fullStudentList = db.StudentDatabase();
        Boolean quit = false;
        LoginManager loginManager = new LoginManager();
        int courseVacancy;
        StudentManager studentMgmt = new StudentManager();
        List<Course> courseList = db.CourseListDatabase();
        List<Lesson> lessonList = db.LessonListDatabase();
        while (!quit)
        {
            System.out.println(
            " 1 Edit student access period \n 2 Add Student Records \n 3 Removing A Student \n 4 Check available slot by index number \n 5 Print student list by index number \n 6 Print student list by course \n 7 Add a new Course \n 8 Save to file and Quit");
            num = console.readLine("Please choose your action \n");
            switch (num) {
                case "1":
                    //start date/ end date can be adjusted here.
                    System.out.println("\nEdit student access period by date and time");
                    System.out.println("\n Enter student year to edit access period");
                    String year = sc.next();
                    System.out.println("\n Enter Access Period Start Date ");
                    Date startDateTime =adminController.InputDateTime();
                    System.out.println("\n Enter Access Period End Date ");   
                    Date endDateTime =adminController.InputDateTime();
                    loginManager.validateLogin(year,accessPeriodList,startDateTime,endDateTime);

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
                    Course currentCourse = studentMgmt.GetCourse(Integer.parseInt(courseIndex), courseList);
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
                    System.out.println("\nAdd a new course");
                    adminController.addCourse(courseList, lessonList);
                    break;
                case "8":
                    db.UpdateStudentDatabase(fullStudentList);
                    db.UpdateCourseDatabase(courseList);
                    db.UpdateAccessListDatabase(accessPeriodList);
                    db.UpdateLessonDatabase(lessonList);
                    System.out.println("\nQuit");
                    quit=true;
                    System.exit(1);
                    break;
                default:
            }
          
        }

    }
    
    
 
}
