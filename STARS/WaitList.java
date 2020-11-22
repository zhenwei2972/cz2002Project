import java.util.ArrayList;
import java.util.List;

public class WaitList {
    List<ArrayList<Object>> waitlist = new ArrayList<ArrayList<Object>>();
    StudentManager studentManager = new StudentManager();
    CourseManager courseMgmt = new CourseManager();

    public WaitList() {
    }

    // Debugging Purposes
    public void printWaitList() {
        for (ArrayList<Object> i : this.waitlist) {
            for (Object j : i) {
                if (j instanceof Student) {
                    Student stud = (Student) j;
                    System.out.print("Student name: " + stud.getUsername() + " ");
                } else if (j instanceof Course) {
                    Course course = (Course) j;
                    System.out.print(
                            "Course Index: " + course.getIndex() + "Course Code: " + course.getCourseCode() + "\n");
                }
            }
        }
    }

    public void AddtoWaitList(Student student, Course course) {
        ArrayList<Object> details = new ArrayList<Object>();
        details.add(student);
        details.add(course);
        this.waitlist.add(details);
        course.setWaitlist(course.getWaitlist() + 1);
    }

    public void RemoveFromWaitList(Student student, Course course) {
        if (this.waitlist.isEmpty())
            return;
        for (ArrayList<Object> items : this.waitlist) {
            if (items.get(0) == student && items.get(1) == course) {
                course.setWaitlist(course.getWaitlist() - 1);
                this.waitlist.remove(items);
                return;
            }
        }
    }

    public void AddCoursetoStudent(Course course) {
        for (ArrayList<Object> items : this.waitlist) {
            if (items.get(1) == course) {
                course.setWaitlist(course.getWaitlist() - 1);
                Student s = (Student) items.get(0);
                studentManager.AddCourse(course, s);
                this.waitlist.remove(items);
                return;
            }
        }
    }
}
