import java.util.List;
import java.util.stream.Collectors;

import javax.mail.MessagingException;

import java.util.ArrayList;

/**
 * Represents the StudentManager object, 
 * this is to do the required functions that student can perform
 * and it does filtering of the student list.
 * @author Team Stars
 * @version 1.0
 * @since 2020
 */


public class StudentManager {
    /**
     * this is a courseManager for filtering courses
     */
    CourseManager courseMgmt = new CourseManager();
    /**
     * this is a lessonmanager for filtering lessons
     */
    LessonManager lessonMgmt = new LessonManager();
    /**
     * this is a mail for sending emails to student
     */
    Mail mailer = new Mail();
    /**
     * this is a limit au for student
     */
    private int auLimit = 23;

    /**
     * this is a flitering interface for filtering students
     * 
     */
    interface StudentFiltering {
        public List<Student> Filtering(List<Student> list, String value);
    }
    /**
     * this uses the matricid of the student to filter out
     * since the matric id is unquie it will always return 1 item in the list
     */
    StudentFiltering byMatricNo = (fullStudentList, matricNo) -> {
        return fullStudentList.stream().filter(x -> matricNo.equals(x.getMatid())).collect(Collectors.toList());
    };
    /**
     * this uses the username of the student to filter out
     * 
     */
    StudentFiltering byUsername = (fullStudentList, username) -> {
        return fullStudentList.stream().filter(x -> username.equals(x.getUsername())).collect(Collectors.toList());
    };


    /**
     * this is a course filtering by index using it to get a module of the speficic index
     * return a course object if the index mataches
     * no module found will return null
     * @param courseIndex is the course index
     * @param courses is the List of courses for filtering
     */
    public Course GetCourse(int courseIndex, List<Course> courses) {
        List<Course> result = new ArrayList<Course>();
        // find corresponding course object using these course code & index
        result = courseMgmt.byIndex.Invoke(courses, Integer.toString(courseIndex));
        for (Course mod : result) {
            // add course if found
            return mod;
        }
        return null;
    }

    /**
     * Finding a student function
     * will return the student object when matric id mataches
     * if not return null
     * @param matricNo is the matric numbner of the student
     * @param students is the list of student object
     */
    public Student GetStudent(int matricNo, List<Student> students) {
        List<Student> result = new ArrayList<Student>();
        // find corresponding student object using these course code & index
        result = byMatricNo.Filtering(students, Integer.toString(matricNo));
        for (Student student : result) {
            // add course if found
            return student;
        }
        return null;
    }
    /**
     * Finding a student function
     * will return the student object when matric id mataches
     * if not return null
     * @param username is the student's username or input from login
     * @param students is the list of student object
     */
    public Student GetStudentByUserName(String username, List<Student> students) {
        List<Student> result = new ArrayList<Student>();
        // find corresponding student object using these course code & index
        result = byUsername.Filtering(students, username);
        for (Student student : result) {
            // add course if found
            return student;
        }
        return null;
    }
    /**
     * this function prints all courses from the list of courses object given
     * @param courses is the list of courses object
     */
    public void printAllCourse(ArrayList<Course> courses) {
        System.out.println("Courses registered :");
        for (Course course : courses) {
            System.out.println(course.getCourseCode() + " : " + course.getIndex());
        }
        Integer totalAU = calculateTotalAU(courses);
        System.out.println("Total AU for this semester: " + totalAU);
    }
    /**
     * to calculate the sum AU from the course object list 
     * @param courses is the list of courses object
     */
    public Integer calculateTotalAU(ArrayList<Course> courses) {
        return courses.stream().mapToInt(x -> x.getAU()).sum();
    }


    /**
     * this function does a email sending check and calling the sendmail function from mail class
     * @param email is the email of a student
     */
    public void SendEmail(String email) {
        try {
            mailer.sendMail(email);
        } catch (MessagingException ex) {
            System.out.println("Email Sending Error");
        }

    }

    /**
     * This is the Registering course function for the student
     * It consist 4 types of checking
     * 1. checking for exisiting courses in the student's registered courses
     * 2. the total au in student not exceeding the limit
     * 3. the time check clash before adding
     * 4. the vaccancy of the course index
     * @param mod this is the course that the student wish to add
     * @param waitinglist is the waiting list
     * @param currentStudent is the current logged in student
     */
    public void AddCourse(Course mod, WaitList waitinglist, Student currentStudent) {
        if (checkExist(mod, currentStudent)) {
            if (calculateTotalAU(currentStudent.getCourse()) <= auLimit) {
                if (!checkClash(mod, currentStudent)) {
                    if (mod.getVacancy() > 0) {
                        currentStudent.addModList(mod);
                        mod.setVacancy(mod.getVacancy() - 1);
                        SendEmail(currentStudent.getEmail());
                    } else {
                        System.out.println("This course " + mod.getCourseCode() + " : " + mod.getIndex() + " is full");
                        System.out.println("Adding to waitlist");
                        waitinglist.AddtoWaitList(currentStudent, mod);
                    }
                } else
                    System.out.println("There is a clash for this course added -index : " + mod.getIndex());
            } else {
                System.out.println("Number of total AU exceeded 23, not allowed to add");
            }
        } else
            System.out.println("There is an exisitng course added index : " + mod.getIndex());
    }
   
    /**
     * This is the Registering course function for the student
     * It consist 4 types of checking
     * 1. checking for exisiting courses in the student's registered courses
     * 2. the total au in student not exceeding the limit
     * 3. the time check clash before adding
     * 4. the vaccancy of the course index
     * @param mod this is the course that the student wish to add
     * @param currentStudent is the current logged in student
     * 
     * Will not print anything and will not affect waiting list
     */
    public void AddCourse(Course mod, Student currentStudent) {
        if (checkExist(mod, currentStudent)) {
            if (!checkClash(mod, currentStudent)) {
                if (mod.getVacancy() > 0) {
                    currentStudent.addModList(mod);
                    mod.setVacancy(mod.getVacancy() - 1);
                    SendEmail(currentStudent.getEmail());
                }
            }
        }
    }

    // ---------delete------------------//
    /**
     * This function remove the student from thier registered course
     * @param mod is the course or deletion
     * @param waitinglist is the waiting list 
     * @param currentStudent is the current logged in student
     */
    public void RemoveCourse(Course mod, WaitList waitinglist, Student currentStudent) {
        if (!checkExistIndex(mod, currentStudent)) {
            mod.setVacancy(mod.getVacancy() + 1);
            currentStudent.removeModList(mod);
            waitinglist.AddCoursetoStudent(mod);
            System.out.println("\nCourse Removed");
        } else
            System.out.println("There is no " + mod.getCourseCode() + " existing in your registered course");
    }
    /**
     * This function remove the student from thier registered course
     * @param mod is the course for deletion
     * @param currentStudent is the current logged in student
     * 
     * Will not print anything and will not affect waiting list
    */
    public void RemoveCourse(Course mod, Student currentStudent) {
        if (!checkExistIndex(mod, currentStudent)) {
            mod.setVacancy(mod.getVacancy() + 1);
            currentStudent.removeModList(mod);
        }
    }

    // ------------------- checking -------------------//
    /**
     * This a private function to check that module existing in thier registered courses
     * with the course code
     * @param mod is the course 
     * @param currentStudent is the current logged in student
     * 
     * returning the result of a boolean
     * false there is an exisiting course of same course code
     * true when there is no mataches
    */
    private boolean checkExist(Course mod, Student currentStudent) {
        List<Course> courses = currentStudent.getCourse();
        List<Course> result = new ArrayList<Course>();
        result = courseMgmt.byModuleCode.Invoke(courses, mod.getCourseCode());
        if (result.isEmpty()) {
            return true;
        }
        return false;
    }
    //-------------------checking by index-------------------------//
    /**
     * This a private function to check that module existing in thier registered courses
     * with the course index
     * @param mod is the course 
     * @param currentStudent is the current logged in student
     * 
     * returning the result of a boolean
     * false there is an exisiting course of same course index
     * true when there is no mataches
    */
    private boolean checkExistIndex(Course mod, Student currentStudent) {
        List<Course> courses = currentStudent.getCourse();
        List<Course> result = new ArrayList<Course>();
        result = courseMgmt.byIndex.Invoke(courses, Integer.toString(mod.getIndex()));
        if (result.isEmpty()) {
            return true;
        }
        return false;
    }
    /**
     * Printing of all the lesson information of a certain index
     * @param courseIndex is an integer of course index
     */
    public void printLessonInformation(int courseIndex) {
        List<Lesson> result = new ArrayList<Lesson>();
        result = lessonMgmt.byIndex.Invoke(Integer.toString(courseIndex));
        for (Lesson mod : result) {
            System.out.println("Class type " + mod.getClassType() + "\nCourse Name " + mod.getCourseName()
                    + "\nCourse Code " + mod.getCourseCode() + "\nCourse Index " + mod.getIndex() + "\nDay "
                    + mod.getDay() + "\nStar Time " + mod.getStartTime() + "\nEnd Time " + mod.getEndTime());
        }

    }

    /**
     * checking for time slot clash between modules.
     * @param course is the course
     * @param currentStudent is the student object
     * this checks the lessons of the course and student registered courses lessons
     * check of each lessons if there is any clashes
     * returning a boolean value of  true if there is a clash
     * false if there isnt any clashes
     */
    public boolean checkClash(Course course, Student currentStudent) {
        List<Lesson> result = new ArrayList<Lesson>();
        result = lessonMgmt.byIndex.Invoke(Integer.toString(course.getIndex()));
        for (Course mycourse : currentStudent.getCourse()) {
            List<Lesson> currentmodules = new ArrayList<Lesson>();
            currentmodules = lessonMgmt.byIndex.Invoke(Integer.toString(mycourse.getIndex()));
            for (Lesson current : currentmodules) {
                for (Lesson mod : result) {
                    if (current.getDay() == mod.getDay()
                            && (current.getStartTime() < mod.getEndTime() && current.getEndTime() >= mod.getEndTime())
                            || (current.getStartTime() == mod.getStartTime()
                                    && current.getEndTime() == mod.getEndTime()))
                        return true;
                }
            }
        }
        return false;
    }

    // ----------------- functions ---------------------//
    /**
     * This is a Swapping of courses with another student
     * @param current student that called the function
     * @param target student that was targeted to swap with
     * @param course the course intended to swap from current student
     * @param modid the index of the course from target
     * 
     * will 1st do a check for target and id if target have registered the index
     * if exist will do a swap
     */
    public void SwapCourse(Student current, Student target, Course course, int modid,String password) {
        List<Course> result = new ArrayList<Course>();
        List<Course> courselist = target.getCourse();
        result = courseMgmt.byIndex.Invoke(courselist, Integer.toString(modid));
        if(!(target.getPassword().equals(password)))
        {
            System.out.println("Invalid Password");
            return;
        }
        if (result.isEmpty()) {
            System.out.println(
                    "Student" + target.getUsername() + " does not have " + course.getCourseCode() + ":" + modid);
            return;
        } else {
    
            this.RemoveCourse(result.get(0), target);
            this.RemoveCourse(course, current);
            System.out.println("Swapping course for student 1 (Skip Wait list)");
            this.AddCourse(result.get(0), current);
            System.out.println("Swapping coures for student 2 (Skip Wait list)");
            this.AddCourse(course, target);
           
        }
    }
    /**
     * Generating timetable function
     * Create a list of lesson for each day
     * Printing lessons in sets of days
     * @param courses from the student
     */
    public void GenerateTimeTable(ArrayList<Course> courses) {
        List<Lesson> mondayLesson = new ArrayList<Lesson>();
        List<Lesson> tuesdayLesson = new ArrayList<Lesson>();
        List<Lesson> wednesdayLesson = new ArrayList<Lesson>();
        List<Lesson> thursdayLesson = new ArrayList<Lesson>();
        List<Lesson> fridayLesson = new ArrayList<Lesson>();

        List<Integer> indexArr = new ArrayList<Integer>();
        // Testing using 10271, 10242
        for (Course i : courses) {
            indexArr.add(i.getIndex());
        }
        mondayLesson = lessonMgmt.getMondayClasses.Invoke(indexArr);
        tuesdayLesson = lessonMgmt.getTuesdayClasses.Invoke(indexArr);
        wednesdayLesson = lessonMgmt.getWednesdayClasses.Invoke(indexArr);
        thursdayLesson = lessonMgmt.getThursdayClasses.Invoke(indexArr);
        fridayLesson = lessonMgmt.getFridayClasses.Invoke(indexArr);
        PrintTimeTable(mondayLesson, "Monday");
        PrintTimeTable(tuesdayLesson, "Tuesday");
        PrintTimeTable(wednesdayLesson, "Wednesday");
        PrintTimeTable(thursdayLesson, "Thursday");
        PrintTimeTable(fridayLesson, "Friday");
        System.out.println("");
    }

    public void PrintTimeTable(List<Lesson> lessonList, String day) {
        System.out.println(day + " Classes:");
        System.out.println("---------------");
        if (lessonList.size() > 0)
            lessonList.forEach(x -> System.out
                    .println("Index: " + x.getIndex() + " Course Code: " + x.getCourseCode() + " Module Name: "
                            + x.getCourseName() + " Start Time: " + x.getStartTime() + " End Time: " + x.getEndTime() + "Venue: " + x.getVenue()));
        else
            System.out.println("You have no classes on " + day);
    }
}
