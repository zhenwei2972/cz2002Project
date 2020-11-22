import java.util.List;
public class Test {
    public static void main(String args[]) throws Exception {
        Database db = new Database();
        List<Lesson> fullLessonList = db.LessonListDatabase();
        List<Course> fullCourseList = db.CourseListDatabase();
        List<Admin> fullAdminList = db.AdminUserDatabase();
        List<Student> fullStudentList = db.StudentDatabase();
        
        db.UpdateStudentDatabase(fullStudentList);
    }
}