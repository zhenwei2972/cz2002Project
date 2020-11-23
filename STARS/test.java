import java.util.List;

public class test {
    

    public static void main(String args[]) {
        Database ctrl = new Database();
        List<Student> fullStudentList = ctrl.StudentDatabase();

        for (int i = 0; i < fullStudentList.size(); i++) {
            System.out.println(fullStudentList.get(i).getUsername());
        }

    }
}
