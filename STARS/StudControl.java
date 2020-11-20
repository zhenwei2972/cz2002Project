import java.util.ArrayList;
import java.util.List;

public class StudControl {
    Filter filter = new Filter();
    public void SwapCourse(Student a , Student b , Course m , int modid){

        List<Course> result = new ArrayList<Course>();
        List<Course> courselist =  b.getmodules();
        result = filter.byIndex.Invoke(courselist, Integer.toString(modid));
        
        b.AddCourse(m);
        b.RemoveCourse(result.get(0));
        a.RemoveCourse(m);
        a.AddCourse(result.get(0));
    }
}
