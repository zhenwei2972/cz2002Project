import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;

/**
 * This is the admin manager where all the admin functions are written
 * 
 * @author Team Stars
 * @version 1.0
 * @since 2020
 */
public class AdminManager {
    /**
     * an interface for flitering studentlist
     */
    interface PrintStudentList {
        public void Invoke(List<Student> studentList, String value);
    }

    /**
     * creating new student accounts
     * 
     * @param fullStudentList is the entire list of student accounts
     */
    LoginManager loginMgmt = new LoginManager();
    /**
     * register new student function
     * @param fullStudentList as student list
     */
    public void addStudent(List<Student> fullStudentList) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Student's Matriculation Number");
        String matricNo = sc.next();
        int year = 0;
        if (!checkExistingStudent(fullStudentList, matricNo)) {
            Boolean partTime = false;
            System.out.println("Enter Student's Username(Minimum 6 characters)");
            String studentName = sc.next();
            if (studentName.length() < 6) {
                System.out.println("Username must be longer than 6 characters");
                return;
            }

            System.out.println("Enter Student's Password(Minimum 6 characters)");
            String studentPassword = sc.next();
            if (studentPassword.length() < 6) {
                System.out.println("Password must be longer than 6 characters");
                return;
            }
            studentPassword = loginMgmt.passwordHashing(studentPassword);

            System.out.println("Is this Part-Time Student? (Y/N)");
            String option = sc.next().toUpperCase();
            if (option.equals("Y") || option.equals("N")) {
                partTime = (option.equals("Y")) ? true : false;
            } else {
                System.out.println("Invalid Option, only pick Y/N");
                return;
            }

            System.out.println("Enter Student Year");
            if (sc.hasNextInt()) {
                year = sc.nextInt();
                if (Integer.toString(year).length() != 4) {
                    System.out.println("Incorrect input 4 digits are required");
                    return;
                } else {
                    // successfully entered year
                }
            } else {
                System.out.println("Incorrect input, only integers allowed");
                return;
            }

            System.out.println("Enter Student Email");
            String email = sc.next();
            if (!isValidEmailAddress(email)) {
                System.out.println("Invalid Student Email, please use valid email format ");
                return;
            }

            fullStudentList.add(new Student(matricNo, studentName, studentPassword, partTime, year, email));
            System.out.println("\n Added a new Student");
            System.out.println("\n All Students in records");
            AllStudents.Display(fullStudentList);
        } else {
            System.out.println("Student already exist in records");
        }
    }

    /**
     * a Functional interface is not other than a abstract method, which we are able polymorph into different methods
     * This method only works on Java 8 onwards
     * usage for displays
     * */
    @FunctionalInterface
    interface StudentDiplay {
        public void Display(List<Student> list);
    }

    /**
     * Print all students
     */
    StudentDiplay AllStudents = (result) -> result
            .forEach(x -> System.out.println(" Username " + x.getUsername() + " Matriculation Number " + x.getMatid()));

    @FunctionalInterface
    interface CourseDisplay {
        public void Display(List<Course> list);
    }

    /**
     * Print all courses
     */
    CourseDisplay AllCourses = (result) -> result
            .forEach(x -> System.out.println(" Course Index " + x.getIndex() + " Course Code " + x.getCourseCode()));
    /**
     * adding a new course to the system
     * 
     * @param courseList the entire list of courses
     * @param lessonList the entire list of lessons
     */
    public void addCourse(List<Course> courseList, List<Lesson> lessonList) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Course Index Number");
        int courseNo = 0;
        if (sc.hasNextInt()) {
            courseNo = sc.nextInt();
        } else {
            System.out.println("Wrong input");
            return;
        }

        if (!checkExistingCourse(courseList, courseNo)) {
            System.out.println("Enter Course Code");
            String courseCode = sc.next();
            int vacancy = 0;
            int au = 0;
            int lessonNo;
            int starttime;
            int endtime;
            String classtype, courseName;
            String day;
            String venue;
            System.out.println("Enter Vacancy");
            if (sc.hasNextInt()) {
                vacancy = sc.nextInt();
            } else {
                System.out.println("Wrong input");
                return;
            }
            System.out.println("Enter Course AU");
            if (sc.hasNextInt()) {
                au = sc.nextInt();
            } else {
                System.out.println("Wrong input");
                return;
            }

            System.out.println("Enter Course Name");
            courseName = sc.next();

            System.out.println("Enter number of lessons");
            if (sc.hasNextInt()) {
                lessonNo = sc.nextInt();
            } else {
                System.out.println("Wrong input");
                return;
            }
            for (int i = 0; i < lessonNo; i++) {
                System.out.println("Enter start time 0000-2359 (24hour time)");
                if (sc.hasNextInt()) {
                    starttime = sc.nextInt();
                    if (starttime > 2359) {
                        System.out.println("Time must be within range 0000-2359");
                        return;
                    }
                } else {
                    System.out.println("Wrong input");
                    return;
                }
                System.out.println("Enter end time 0000-2359 (24hour time)");
                if (sc.hasNextInt()) {
                    endtime = sc.nextInt();
                    if (endtime > 2359) {
                        System.out.println("Time must be within range 0000-2359");
                        return;
                    } else if (endtime <= starttime) {
                        System.out.println("End time must be before Start time");
                        return;
                    }
                } else {
                    System.out.println("Wrong input");
                    return;
                }
                System.out.println("Enter Course Type");
                classtype = sc.next();
                if (!(classtype.equals("Lecture") || classtype.equals("Tutorial") || classtype.equals("Lab"))) {
                    System.out.println("Invalid input, must be Lecture/Tutorial/Lab");
                    return;
                }
                System.out.println("Enter Day(i.e Monday)");
                day = sc.next();
                if (!(day.equals("Monday") || day.equals("Tuesday") || day.equals("Wednesday") || day.equals("Thursday")
                        || day.equals("Friday"))) {
                    System.out.println("Invalid input must be Monday/Tuesday/Wednesday/Thursday/Friday");
                    return;
                }
                System.out.println("Enter Venue of Lesson");
                venue = sc.next();
                lessonList.add(
                        new Lesson(courseNo, courseCode, courseName, au, day, starttime, endtime, classtype, venue));
            }
            courseList.add(new Course(courseNo, courseCode, vacancy, 0, au));
            System.out.println("Course successfully added");
            System.out.println("\n All Courses in records");
            AllCourses.Display(courseList);
        } else {
            System.out.println("Course already exist in records");
        }
    }

    /**
     * a check for adding course to make sure there is no duplicated index
     * 
     * @param courseList the entire course list
     * @param courseNo   the index to be created
     * @return a boolean. False when it is not existed. True when it exist.
     */
    private boolean checkExistingCourse(List<Course> courseList, int courseNo) {
        List<Course> tempCourse = courseList.stream().filter(x -> x.getIndex() == courseNo)
                .collect(Collectors.toList());
        if (tempCourse.isEmpty()) {

            return false;
        }
        return true;
    }

    /**
     * Deleting student account
     * 
     * @param fullStudentList The entire student list
     * @param matricNo        the matric number of account to be deleted
     * @return the studentlist and is found
     */
    public List<Student> removeStudent(List<Student> fullStudentList, String matricNo) {

        return fullStudentList.stream().filter(x -> x.getMatid().equals(matricNo)).collect(Collectors.toList());
    }

    /**
     * a check for creating student to make sure there is no duplicated matric id
     * 
     * @param fullStudentList the entire list of student
     * @param matricNo as the matric id to be created
     * @return a boolean. False when it is not existed. True when it exist.
     */
    private boolean checkExistingStudent(List<Student> fullStudentList, String matricNo) {
        List<Student> tempStudent = fullStudentList.stream().filter(x -> x.getMatid().equals(matricNo))
                .collect(Collectors.toList());
        if (tempStudent.isEmpty()) {

            return false;
        }
        return true;
    }

    /**
     * rewrite from interface this will print all the student that have registered
     * for the course index
     * 
     * @param fullStudentList
     * @param IndexInString
     */
    PrintStudentList printStudentfromIndex = (fullStudentList, indexInString) -> {
        AtomicInteger studentcount = new AtomicInteger(0);
        int index = Integer.parseInt(indexInString);
        fullStudentList.stream().forEach(i -> {
            List<Course> studentCourse = i.getCourse();
            List<Course> displaycourse = studentCourse.stream().filter(j -> index == j.getIndex())
                    .collect(Collectors.toList());
            if (displaycourse.size() < 1)
                studentcount.getAndIncrement();
            else
                displaycourse.forEach(x -> System.out.println("Student: " + i.getUsername()));
        });
        if (studentcount.get() == 0)
            System.out.println("No Student Registered in this index");

        // This code is the same as the top
        // for (Student i : fullStudentList) {
        // List<Course> studentCourse = i.getmodules();
        // for (Course j : studentCourse) {
        // if (index == j.index) {
        // System.out.println("Student: " + i.getUsername());
        // }
        // }
        // }
    };
    /**
     * rewrite from interface this will print all the student that have registered
     * for the course
     * 
     * @param fullStudentList
     * @param CourseCode
     */
    PrintStudentList printStudentFromCourse = (fullStudentList, courseCode) -> {
        AtomicInteger studentcount = new AtomicInteger(0);
        fullStudentList.stream().forEach(i -> {
            List<Course> studentCourse = i.getCourse();
            List<Course> displayCourse = studentCourse.stream().filter(j -> courseCode.equals(j.getCourseCode()))
                    .collect(Collectors.toList());
            if (displayCourse.size() < 1)
                studentcount.getAndIncrement();
            else
                displayCourse.forEach(x -> System.out.println("Student: " + i.getUsername()));
        });
        if (studentcount.get() == 0)
            System.out.println("No Student Registered in this course");

        // This code is the same as the top
        // for (Student i : fullStudentList) {
        // List<Course> studentCourse = i.getmodules();
        // for (Course j : studentCourse) {
        // if (courseCode.equals(j.coursecode)) {
        // System.out.println("Student Name: " + i.getUsername());
        // }
        // }
        // }
    };

    /**
     * Entering a new access period
     * 
     * @return null when exception or invalid input
     */
    public Date InputDateTime() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter Day i.e 01");
        String day = sc.next();
        int numberCheck;
        try {
            numberCheck = Integer.parseInt(day);
            if (day.length() != 2) {
                System.out.println("\nInvalid input, enter 2 digit integers");
                return null;
            }
        } catch (NumberFormatException ex) {
            System.out.println("invalid input, must enter a (Integer)");
            return null;
        }
        System.out.println("\nEnter Month i.e (JAN)");
        String month = sc.next();
        if (month.length() != 3) {
            return null;
        }
        System.out.println("\nEnter Year i.e 2002");
        String year = sc.next();
        try {
            numberCheck = Integer.parseInt(year);
            if (year.length() != 4) {
                System.out.println("\nInvalid input, enter 4 digit integers");
                return null;
            }
        } catch (NumberFormatException ex) {
            System.out.println("invalid input, must enter a (Integer)");
            return null;
        }
        System.out.println("\nEnter Hour i.e 12");
        String hour = sc.next();
        try {
            numberCheck = Integer.parseInt(hour);
            if (hour.length() != 2) {
                System.out.println("\nInvalid input, enter 2 digit integers");
                return null;
            }
        } catch (NumberFormatException ex) {
            System.out.println("invalid input, must enter a (Integer)");
            return null;
        }
        System.out.println("\nEnter Minute i.e 15");
        String min = sc.next();
        try {
            numberCheck = Integer.parseInt(min);
            if (min.length() != 2) {
                System.out.println("\nInvalid input, enter 2 digit integers");
                return null;
            }
        } catch (NumberFormatException ex) {
            System.out.println("invalid input, must enter a (Integer)");
            return null;
        }
        String dateInString = month + " " + day + ", " + year + " " + hour + ":" + min;
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy HH:mm");

        try {
            Date date = formatter.parse(dateInString);
            System.out.println("Entered " + formatter.format(date));
            return date;

        } catch (ParseException e) {
            System.out.println("Incorrect Entry");
            e.printStackTrace();
        }
        return null;

    }

    // regex to validate email address pattern
    private static final String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    /**
     * checking valid email
     * 
     * @param email is the creating new student email
     * @return boolean match and mismatch
     */
    public static boolean isValidEmailAddress(String email) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

}
