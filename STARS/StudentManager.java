import java.util.List;
import java.util.stream.Collectors;

import javax.mail.MessagingException;

import java.util.ArrayList;

public class StudentManager {
    CourseManager courseMgmt = new CourseManager();
    LessonManager lessonMgmt = new LessonManager();
    Mail mailer = new Mail();

    interface StudentFiltering {
        public List<Student> Filtering(List<Student> list, String value);
    }

    StudentFiltering byMatricNo = (fullStudentList, matricNo) -> {
        return fullStudentList.stream().filter(x -> matricNo.equals(x.getMatid())).collect(Collectors.toList());
    };
    StudentFiltering byUsername = (fullStudentList, username) -> {
        return fullStudentList.stream().filter(x -> username.equals(x.getUsername())).collect(Collectors.toList());
    };

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

    public void printAllCourse(ArrayList<Course> courses) {
        System.out.println("Courses registered :");
        for (Course course : courses) {
            System.out.println(course.getCourseCode() + " : " + course.getIndex());
        }
        Integer totalAU = calculateTotalAU(courses);
        System.out.println("Total AU for this semester: " + totalAU);
    }

    public Integer calculateTotalAU(ArrayList<Course> courses) {
        return courses.stream().mapToInt(x -> x.getAU()).sum();
    }

    public void SendEmail(String email) {
        try {
            mailer.sendMail(email);
        } catch (MessagingException ex) {
            System.out.println("Email Sending Error");
        }

    }

    public void AddCourse(Course mod, WaitList waitinglist, Student currentStudent) {
        if (checkExist(mod, currentStudent)) {
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
        } else
            System.out.println("There is an exisitng course added index : " + mod.getIndex());
    }

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
    public void RemoveCourse(Course mod, WaitList waitinglist, Student currentStudent) {
        if (!checkExist(mod, currentStudent)) {
            mod.setVacancy(mod.getVacancy() + 1);
            currentStudent.removeModList(mod);
            waitinglist.AddCoursetoStudent(mod);
        } else
            System.out.println("There is no " + mod.getCourseCode() + " exsisting in your registered course");
    }

    public void RemoveCourse(Course mod, Student currentStudent)  {
        if (!checkExist(mod, currentStudent)) {
            mod.setVacancy(mod.getVacancy() + 1);
            currentStudent.removeModList(mod);
        } else
            System.out.println("There is no " + mod.getCourseCode() + " exsisting in your registered course");
    }

    // ------------------- checking -------------------//
    private boolean checkExist(Course mod, Student currentStudent) {
        List<Course> courses = currentStudent.getCourse();
        List<Course> result = new ArrayList<Course>();
        result = courseMgmt.byModuleCode.Invoke(courses, mod.getCourseCode());
        if (result.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * checking for time slot clash between modules.
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
    public void SwapCourse(Student current, Student target, Course course, int modid) {
        List<Course> result = new ArrayList<Course>();
        List<Course> courselist = target.getCourse();
        result = courseMgmt.byIndex.Invoke(courselist, Integer.toString(modid));
        if (result.isEmpty()) {
            System.out.println(
                    "Student" + target.getUsername() + " does not have " + course.getCourseCode() + ":" + modid);
            return;
        } else {
            this.AddCourse(course, target);
            this.RemoveCourse(result.get(0), target);
            this.RemoveCourse(course, current);
            this.AddCourse(result.get(0), current);
        }
    }

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
                            + x.getCourseName() + " Start Time: " + x.getStartTime() + " End Time: " + x.getEndTime()));
        else
            System.out.println("You have no classes on " + day);
    }
}
