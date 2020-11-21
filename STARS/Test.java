import java.util.ArrayList;
public class Test {
    public static void main(String args[]) throws Exception {
        WaitList w = new WaitList();
        Student a = new Student("U1920129E","chienyq","12345");
        Course test = new Course(10214,"CZ2003","Computer Graphics",3,"Wednesday",830,930,"Tutorial",2,1);
        Course test1 = new Course(10216,"CZ2003","Computer Graphics",3,"Wednesday",830,930,"Tutorial",5,2);
        a.AddCourse(test,w);
        a.AddCourse(test);
        a.AddCourse(test1,w);
        ArrayList<Course> mods= a.getCourse();
        for (Course m: mods){
            System.out.println(m.getIndex());

        }
        w.RemoveFromWaitList(a, test);
    }
}