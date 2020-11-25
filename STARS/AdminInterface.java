import java.io.Console;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import java.text.ParseException;
import java.util.Date;

/**
 * This is the admin UI/UX prints the menu and also checks for valid inputs
 * 
 * @author Team Stars
 * @version 1.0
 * @since 2020
 */
public class AdminInterface {
    private Console console = System.console();
    private AdminManager adminController = new AdminManager();
    private Database db = new Database();
    private LoginManager loginManager = new LoginManager();
    private StudentManager studentMgmt = new StudentManager();
    private WaitList waitList = new WaitList();
    private List<Student> students;
    private List<Course> courses;
    private List<Lesson> lessonList;

    public AdminInterface(List<Student> fullStudentList2, List<Course> coursesInput, List<Lesson> lessonList) {
        this.students = fullStudentList2;
        this.courses = coursesInput;
        this.lessonList = lessonList;
    }

    /**
     * scanner for taking in user input
     */
    Scanner sc = new Scanner(System.in);

    /**
     * the function that prints the menu and handle input checks
     * 
     * @param accessPeriodList is the list of available access periods for student
     * @throws IOException    is to check for database load fails
     * @throws ParseException is to check for casting/parsing fails
     */
    public void AdminMenuLogic(List<AccessPeriod> accessPeriodList) throws IOException, ParseException {
        String num = "";
        Boolean quit = false;
        System.out.println("Welcome to Admin Mode");
        int courseVacancy;
        this.db.PrepopulateStudentCourses(students, courses,studentMgmt,waitList);

        int intCheck;
        while (!quit) {
            System.out.println(
                    " 1 Edit student access period \n 2 Add Student Records \n 3 Removing A Student \n 4 Check available slot by index number \n 5 Print student list by course index  \n 6 Print student list by course code \n 7 Add a new Course \n 8 Save to file and Quit");
            num = console.readLine("Please choose your action \n");
            switch (num) {
                case "1":
                    // start date/ end date can be adjusted here.
                    System.out.println("\nEdit student access period by date and time");
                    System.out.println("\nEnter student year to edit access period");
                    String year = sc.next();
                    try {
                        intCheck = Integer.parseInt(year);
                        if (year.length() != 4) {
                            System.out.println("\nWrong input, require year (4 number Integer)");
                            break;
                        }
                    } catch (NumberFormatException ex) {
                        System.out.println("invalid input, must enter a valid year (Integer)");
                        break;
                    }
                    System.out.println("\nEnter Access Period Start Date ");
                    Date startDateTime = adminController.InputDateTime();
                    if (startDateTime == null) {
                        break;
                    }
                    System.out.println("\nEnter Access Period End Date ");
                    Date endDateTime = adminController.InputDateTime();
                    if (endDateTime == null) {
                        break;
                    }
                    loginManager.updateAccessPeriod(year, accessPeriodList, startDateTime, endDateTime);

                    break;
                case "2":
                    System.out.println("\nAdding a new Student");
                    // need to populate with new student list
                    adminController.addStudent(students);

                    break;
                case "3":
                    System.out.println("\nRemoving a current Student");
                    System.out.println("Enter the Matric Number of Student to be removed");
                    String matricNo = sc.next();
                    adminController.removeStudent(students, matricNo);
                    System.out.println("\nRemoved " + matricNo + " Student");
                    break;
                case "4":
                    System.out.println("\nCheck available slot by index number");
                    System.out.println("Enter index number");
                    String courseIndex = sc.next();
                    try {
                        intCheck = Integer.parseInt(courseIndex);
                    } catch (NumberFormatException ex) {
                        System.out.println("invalid input, must enter a valid course index (Integer)");
                        break;
                    }
                    Course currentCourse = studentMgmt.GetCourse(Integer.parseInt(courseIndex), courses);
                    if (currentCourse == null) {
                        System.out.println("Invalid course index");
                        sc.nextLine();
                        break;
                    }
                    courseVacancy = currentCourse.getVacancy();
                    System.out.println("Course Vacancy for " + courseIndex + " is " + courseVacancy);
                    break;
                case "5":
                    System.out.println("\nDisplay Students by course index");
                    System.out.println("Enter the Course Index of Students to be printed");
                    String indexString = sc.next();
                    try {
                        intCheck = Integer.parseInt(indexString);
                    } catch (NumberFormatException ex) {
                        System.out.println("invalid input, must enter a valid index number (Integer)");
                        break;
                    }
                    adminController.printStudentfromIndex.Invoke(students, indexString);
                    break;
                case "6":
                    System.out.println("\nDisplay Students by Course Code");
                    System.out.println("Enter the Course Code of Students to be printed");
                    String courseCode = sc.nextLine();
                    adminController.printStudentFromCourse.Invoke(students, courseCode);
                    break;
                case "7":
                    System.out.println("\nAdd a new course");
                    adminController.addCourse(courses, lessonList);
                    break;
                case "8":
                    db.UpdateStudentDatabase(students);
                    db.UpdateCourseDatabase(courses);
                    db.UpdateAccessListDatabase(accessPeriodList);
                    db.UpdateLessonDatabase(lessonList);
                    System.out.println("\nQuit");
                    quit = true;
                    System.exit(1);
                    break;
                default:
            }

        }

    }

}
