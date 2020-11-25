import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * This is the UI/UX for the student menu
 * there is check for invalid entries
 * @author Team Stars
 * @version 1.0
 * @since 2020
 */
public class StudentInterface {
    /**
     * CourseManager for filtering list of course
     */
    CourseManager courseMgmt = new CourseManager();
    /**
     * StudentManager for filtering list of student
     */
    StudentManager studentMgmt = new StudentManager();

    /**
     * this funtions is to print the main menu and checks all the valid entries
     * handling the calling of object functions
     * @param currentStudent is an student object from login
     * @param db is the database class to load all student and course
     * @throws IOException is the trace for database reading exception
     */
    public void StudentMenuLogic(Student currentStudent, Database db) throws IOException {
        String num = "", courseIndex = "", matricNo;
        Console console = System.console();
        System.out.println("Welcome to Student Mode");
        Boolean quit = false;
        Scanner sc = new Scanner(System.in);
        // Initialize common variable/objets
        WaitList waitlist = new WaitList();
        int courseVacancy;
        List<Course> courses = db.CourseListDatabase();
        List<Student> students = db.StudentDatabase();
        Course currentCourse = new Course();
        int intCheck;
        while (!quit) {
            System.out.println(
                    " 1 *Add Course \n 2 Drop Course \n 3 Check/Print Courses Registered \n 4 Check Vacancies Available \n 5 Change Index Number of Course \n 6 Swop Index Number with Another Student \n 7 Printing all available courses \n 8 Check Waiting List \n 9 Print Current Timetable \n 10 Quit");
            num = console.readLine("Please choose your action \n");
            switch (num) {
                case "1":
                    System.out.println("\nEnter a course index");
                    // assuming course index is unique
                    courseIndex = sc.next();
                    currentCourse = studentMgmt.GetCourse(Integer.parseInt(courseIndex), courses);
                    if (currentCourse != null) {
                        studentMgmt.AddCourse(currentCourse, waitlist, currentStudent);
                    } else {
                        System.out.println(("Please enter an exisiting course index :"));
                        courseMgmt.AvailableCourse.Display(courses);
                    }
                    System.out.println("\n ----");
                    break;
                case "2":
                    System.out.println("\nRemove a course");
                    System.out.println("\nEnter a course index");
                    courseIndex = sc.next();
                    try {
                        intCheck = Integer.parseInt(courseIndex);
                    } catch (NumberFormatException ex) {
                        System.out.println("invalid input, must enter a valid course index number(Integer)");
                        break;
                    }
                    // find corresponding course object using these course code & index
                    currentCourse = studentMgmt.GetCourse(Integer.parseInt(courseIndex), courses);
                    if (currentCourse == null) {
                        System.out.println("No courses found");
                      //  sc.nextLine();
                        break;
                    }
                    studentMgmt.RemoveCourse(currentCourse, waitlist, currentStudent);

                    break;
                case "3":
                    System.out.println("\nCheck/Print Courses Registered");
                    ArrayList<Course> studentCourse = currentStudent.getCourse();
                    if (studentCourse == null) {
                        System.out.println("No courses found");
                        break;
                    }
                    studentMgmt.printAllCourse(studentCourse);
                    break;
                case "4":
                    System.out.println("\nCheck Vacancies Available");
                    System.out.println("\nEnter a course index");
                    courseIndex = sc.next();
                    try {
                        intCheck = Integer.parseInt(courseIndex);
                    } catch (NumberFormatException ex) {
                        System.out.println("invalid input, must enter a valid course index number(Integer)");
                        break;
                    }
                    currentCourse = studentMgmt.GetCourse(Integer.parseInt(courseIndex), courses);
                  
                    courseVacancy = currentCourse.getVacancy();
                    System.out.println("Course Vacancy for " + courseIndex + " is " + courseVacancy);
                    break;
                case "5":
                    System.out.println("\nChange Index Number of Course");
                    System.out.println("\nEnter current course index");
                    courseIndex = sc.next();
                    try {
                        intCheck = Integer.parseInt(courseIndex);
                    } catch (NumberFormatException ex) {
                        System.out.println("invalid input, must enter a valid course index number(Integer)");
                        break;
                    }
                    System.out.println("\nEnter a course index that you want to change to");
                    String changeIndex = sc.next();
                    try {
                        intCheck = Integer.parseInt(changeIndex);
                    } catch (NumberFormatException ex) {
                        System.out.println("invalid input, must enter a valid 2nd course index number(Integer)");
                        break;
                    }
                    currentCourse = studentMgmt.GetCourse(Integer.parseInt(courseIndex), courses);
                    if (currentCourse == null) {
                        System.out.println("Course not found");
                        break;
                    }
                    System.out.println("\nInformation for Index" + courseIndex);
                    studentMgmt.printLessonInformation(Integer.parseInt(changeIndex));
                    System.out.println("\nInformation for Index" + changeIndex);
                    studentMgmt.printLessonInformation(Integer.parseInt(changeIndex));
                    System.out.println("\nConfirm Swap? Will remove current course and try to add new course Y / N");
                    String confirmation = sc.next();
                    if (confirmation.toUpperCase().equals("Y")) {
                        Course newCourse = studentMgmt.GetCourse(Integer.parseInt(changeIndex), courses);
                        studentMgmt.RemoveCourse(currentCourse, waitlist, currentStudent);
                        System.out.println("removed course "+currentCourse);
                        System.out.println("Try to add course "+newCourse);
                        studentMgmt.AddCourse(newCourse, waitlist, currentStudent);
                    }
                    break;
                case "6":
                    System.out.println("\nSwop Index Number with Another Student");
                    System.out.println("\nEnter other student's matriculation number");
                    matricNo = sc.next();
                    // get other student's corresponding object
                    System.out.println("\nEnter a course index");
                    courseIndex = sc.next();
                    try {
                        intCheck = Integer.parseInt(courseIndex);
                    } catch (NumberFormatException ex) {
                        System.out.println("invalid input, must enter a valid course index number(Integer)");
                        break;
                    }
                    Student secondStudent = studentMgmt.GetStudent(Integer.parseInt(matricNo), students);
                    currentCourse = studentMgmt.GetCourse(Integer.parseInt(courseIndex), courses);
                    studentMgmt.SwapCourse(currentStudent, secondStudent, currentCourse, Integer.parseInt(courseIndex));
                    break;
                case "7":
                    System.out.println("\nPrinting all available courses");
                    courseMgmt.AvailableCourse.Display(courses);
                    break;
                case "8":
                    System.out.println("\nEnter Index: ");
                    String index = sc.next();
                    try {
                        intCheck = Integer.parseInt(index);
                    } catch (NumberFormatException ex) {
                        System.out.println("invalid input, must enter a valid index number(Integer)");
                        break;
                    }
                    System.out.println("\nDisplay Waiting List for Index: " + index);
                    List<Course> currentList = courseMgmt.byIndex.Invoke(courses, index);
                    courseMgmt.currentIndexWaitingList.Display(currentList);
                    break;
                case "9":
                    System.out.println("Printing Current Time Table");
                    studentMgmt.GenerateTimeTable(currentStudent.getCourse());
                    break;
                case "10":
                    System.out.println("\nQuit");
                    db.UpdateCourseDatabase(courses);
                    db.UpdateStudentDatabase(students);
                    sc.close();
                    quit = true;
                    System.exit(1);
                    break;
                default:
            }
        }

    }

}