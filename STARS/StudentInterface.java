import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentInterface {
    CourseManager courseMgmt = new CourseManager();
    StudentManager studentMgmt = new StudentManager();

    public void StudentMenuLogic(Student currentStudent, Database db) {
        String num = "", courseIndex = "", matricNo;
        Console console = System.console();
        System.out.println("Welcome to Student Mode");
        Boolean quit = false;
        Scanner sc = new Scanner(System.in);
        // Initialize common variable/objets
        WaitList waitlist = new WaitList();
        int courseCode = 0, courseVacancy;
        List<Course> courses = db.CourseListDatabase();
        List<Student> students = db.StudentDatabase();
        while (!quit) {
            System.out.println(
                    " 1 *Add Course \n 2 Drop Course \n 3 Check/Print Courses Registered \n 4 Check Vacancies Available \n 5 Change Index Number of Course \n 6 Swop Index Number with Another Student ");
            num = console.readLine("Please choose your action \n");
            switch (num) {
                case "1":
                    // initialize course object with default values first.

                    System.out.println("\nEnter a course code, course index");
                    // assuming course index is unique
                    courseIndex = sc.next();
                    studentMgmt.AddCourse(studentMgmt.GetCourse(Integer.parseInt(courseIndex), courses), waitlist,
                            currentStudent);
                    System.out.println("\n ----");
                    break;
                case "2":
                    System.out.println("\nRemove a course");
                    System.out.println("\nEnter a course index");
                    courseIndex = sc.next();
                    // find corresponding course object using these course code & index
                    studentMgmt.RemoveCourse(studentMgmt.GetCourse(Integer.parseInt(courseIndex), courses), waitlist,
                            currentStudent);
                    break;
                case "3":
                    System.out.println("\nCheck/Print Courses Registered");
                    studentMgmt.printAllCourse(currentStudent.getCourse());
                    break;
                case "4":
                    System.out.println("\nCheck Vacancies Available");
                    System.out.println("\nEnter a course index");
                    courseIndex = sc.next();
                    courseVacancy = studentMgmt.GetCourse(Integer.parseInt(courseIndex), courses).getVacancy();
                    System.out.println("Course Vacancy for " + courseIndex + " is " + courseVacancy);
                    break;
                case "5":
                    System.out.println("\nChange Index Number of Course");
                    System.out.println("\nEnter current course index");
                    courseIndex = sc.next();
                    System.out.println("\nEnter a course index that you want to change to");
                    String changeIndex = sc.next();
                    studentMgmt.RemoveCourse(studentMgmt.GetCourse(Integer.parseInt(courseIndex), courses), waitlist,
                            currentStudent);
                    studentMgmt.AddCourse(studentMgmt.GetCourse(Integer.parseInt(courseIndex), courses), waitlist,
                            currentStudent);
                    break;
                case "6":
                    System.out.println("\nSwop Index Number with Another Student");
                    System.out.println("\nEnter other student's matriculation number");
                    matricNo = sc.next();
                    // get other student's corresponding object
                    System.out.println("\nEnter a course index");
                    courseIndex = sc.next();
                    studentMgmt.SwapCourse(currentStudent, studentMgmt.GetStudent(Integer.parseInt(matricNo), students),
                            studentMgmt.GetCourse(Integer.parseInt(courseIndex), courses),
                            Integer.parseInt(courseIndex));
                    break;
                default:
            }
        }
    }

}