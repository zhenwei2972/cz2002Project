import java.util.ArrayList;
import java.util.List;
/**
 * This is a waitlist object. it is a list of objects to act as a queuing system.
 * @version 1.0
 * @since 2020
 */
public class WaitList {
    /**
     * a 2d array of Objects. to act as the queue
     */
    List<ArrayList<Object>> waitlist = new ArrayList<ArrayList<Object>>();
    /**
     * studentmanger for filtering students
     */
    StudentManager studentManager = new StudentManager();
    /**
     * coursemanger for filtering course
     */
    CourseManager courseMgmt = new CourseManager();

     /**
     * This is the constructor, since waitlist conisit of object and it is gobal
     * there is no object inserted upon constructing
     */
    public WaitList() {
    }

    // Debugging Purposes
      /**
     * to print all student in the wait list and thier index of course they are waiting for
     */
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
    /**
     * Adding the student to the waitlist
     * @param student the student object
     * @param course the course object
     */
    public void AddtoWaitList(Student student, Course course) {
        ArrayList<Object> details = new ArrayList<Object>();
        details.add(student);
        details.add(course);
        this.waitlist.add(details);
        course.setWaitlist(course.getWaitlist() + 1);
    }

    /**
     * Removing the student from the waitlist from the certain index
     * @param student the student object
     * @param course the course object
     */
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

    /**
     * Automated system to register student to the course from waitlist
     * @param course the course object
     */
    public void AddCoursetoStudent(Course course) {

        for (ArrayList<Object> items : this.waitlist) {

            if (items.get(1) == course) {
                Student s = (Student) items.get(0);
                int slot = course.getVacancy();
                studentManager.AddCourse(course, s);

                if (slot != course.getVacancy()){
                    course.setWaitlist(course.getWaitlist() - 1);
                    this.waitlist.remove(items);
                    System.out.println("Successfully added "+ s.getUsername() + " from waitlist");
                    return;
                }
                else{
                    System.out.println("Clashing time slots with "+s.getUsername());
                }
            }
        }
    }
}
