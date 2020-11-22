import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class StudentManager {
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
        CourseManager courseMgmt = new CourseManager();
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

    public void printAllCourse(ArrayList<Course> courses) {
        System.out.println("Courses registered :");
        for (Course course : courses) {
            System.out.println(course.getCourseCode() + " : " + course.getIndex());
        }
    }

    public void AddCourse(Course mod, WaitList waitinglist, Student currentStudent) {
        if (checkExist(mod)) {
            if (!checkclash(mod)) {
                if (mod.getVacancy() > 0) {
                    currentStudent.addModList(mod);
                    mod.setVacancy(mod.getVacancy() - 1);
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
        if (checkExist(mod)) {
            if (!checkclash(mod)) {
                if (mod.getVacancy() > 0) {
                    currentStudent.addModList(mod);
                    mod.setVacancy(mod.getVacancy() - 1);
                }
            } else
                System.out.println("There is a clash for this course added -index : " + mod.getIndex());
        } else
            System.out.println("There is and exisitng course : " + mod.getIndex());
    }

    // ---------delete------------------//
    public void RemoveCourse(Course mod, WaitList waitinglist, Student currentStudent) {
        if (!checkExist(mod)) {
            mod.setVacancy(mod.getVacancy() + 1);
            currentStudent.removeModList(mod);
            waitinglist.AddCoursetoStudent(mod);
        } else
            System.out.println("There is no " + mod.getCourseCode() + " exsisting in your registered course");
    }

    public void RemoveCourse(Course mod, Student currentStudent) {
        if (!checkExist(mod)) {
            mod.setVacancy(mod.getVacancy() + 1);
            currentStudent.removeModList(mod);
        } else
            System.out.println("There is no " + mod.getCourseCode() + " exsisting in your registered course");
    }

    // ------------------- checking -------------------//
    private boolean checkExist(Course mod, Student currentStudent) {
        List<Course> courses = this.modlist;
        List<Course> result = new ArrayList<Course>();
        result = filter.byModuleCode.Invoke(courses, mod.getCourseCode());
        if (result.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * checking for time slot clash between modules.
     */
    public boolean checkclash(Course course) {
        List<Lesson> result = new ArrayList<Lesson>();
        result = Lfilter.byIndex.Invoke(Integer.toString(course.getIndex()));
        for (Course mycourse : this.modlist) {
            List<Lesson> currentmodules = new ArrayList<Lesson>();
            currentmodules = Lfilter.byIndex.Invoke(Integer.toString(mycourse.getIndex()));
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
    public void SwapCourse(Student b, Course m, int modid) {
        List<Course> result = new ArrayList<Course>();
        List<Course> courselist = b.getCourse();
        result = filter.byIndex.Invoke(courselist, Integer.toString(modid));
        if (result.isEmpty()) {
            System.out.println("Student" + b.username + " does not have " + m.getCourseCode() + ":" + modid);
            return;
        } else {
            b.AddCourse(m);
            b.RemoveCourse(result.get(0));
            this.RemoveCourse(m);
            this.AddCourse(result.get(0));
        }
    }
}
