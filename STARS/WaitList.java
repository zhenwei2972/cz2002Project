import java.util.ArrayList;
import java.util.List;


public class WaitList {
    List<ArrayList<Object>> waitlist = new ArrayList<ArrayList<Object>>();

    public WaitList(){

    }

    public void AddtoWaitList(Student student,Course course){
        ArrayList<Object> details = new ArrayList<Object>();
        details.add(student);
        details.add(course);
        this.waitlist.add(details);
        course.setWaitlist(course.getWaitlist()+1);
    }

    public void RemoveFromWaitList(Student student, Course course){
        if(this.waitlist.isEmpty()) return;
        for(ArrayList<Object> items:this.waitlist){
            if(items.get(0) == student && items.get(1) == course){
                course.setWaitlist(course.getWaitlist()-1);
                this.waitlist.remove(items);
                return;
            }
        }
    }
    public void AddCoursetoStudent(Course course){
        for(ArrayList<Object> items:this.waitlist){
            if(items.get(1) == course){
                course.setWaitlist(course.getWaitlist()-1);
                Student s = (Student)items.get(0);
                s.AddCourse(course);
                this.waitlist.remove(items);
                return;
            }
        }
    }
}
