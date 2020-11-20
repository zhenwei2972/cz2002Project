import java.util.ArrayList;
import java.util.List;
/**
 * Mainly handles the functions done by students account
 * 
 */
public class StudControl {
    Filter filter = new Filter();
    // swaping of courses between 2 students object.
    public void SwapCourse(Student a , Student b , Course m , int modid){

        List<Course> result = new ArrayList<Course>();
        List<Course> courselist =  b.getCourse();
        result = filter.byIndex.Invoke(courselist, Integer.toString(modid));
        if (result.isEmpty()){
            System.out.println("Student B does not have " + m.getCourseName() + ":" + modid);
            return;
        }
        else{
            b.AddCourse(m);
            b.RemoveCourse(result.get(0));
            a.RemoveCourse(m);
            a.AddCourse(result.get(0));
        }
    }
}
