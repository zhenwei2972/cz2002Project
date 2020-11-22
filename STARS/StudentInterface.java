
import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class StudentInterface {
    CourseManager filter = new CourseManager();
    StudentManager studentFilter = new StudentManager();
    public void StudentMenuLogic(Student currentStudent,Database db) {
        String num = "";
        Console console = System.console();
        System.out.println("Welcome to Student Mode");
        Boolean quit = false;
        Scanner sc = new Scanner(System.in);
        //Initialize common variable/objets
        WaitList w = new WaitList();
        String courseIndex = "";
        int courseCode =0;
        List<Course> courses= db.CourseListDatabase();
        List<Student> students= db.StudentDatabase();
        int courseVacancy;
        String matricNo;
        while (!quit)
        {
        System.out.println(
                " 1 *Add Course \n 2 Drop Course \n 3 Check/Print Courses Registered \n 4 Check Vacancies Available \n 5 Change Index Number of Course \n 6 Swop Index Number with Another Student ");
        num = console.readLine("Please choose your action \n");
        switch (num) {
            case "1":
            //initialize course object with default values first.

                System.out.println("\nEnter a course code, course index");
                //assuming course index is unique
                courseIndex = sc.next();
                currentStudent.AddCourse(GetCourse( Integer.parseInt(courseIndex), courses),w);
                System.out.println("\n ----");
                break;
            case "2":
                System.out.println("\nRemove a course");
                System.out.println("\nEnter a course index");
                courseIndex = sc.next();
                //find corresponding course object using these course code & index
                currentStudent.RemoveCourse(GetCourse( Integer.parseInt(courseIndex), courses),w);
                break;
            case "3":
                System.out.println("\nCheck/Print Courses Registered");
                printallcourse(currentStudent.getCourse());
                break;
            case "4":
                System.out.println("\nCheck Vacancies Available");
                System.out.println("\nEnter a course index");
                courseIndex = sc.next();
                courseVacancy =GetCourse(Integer.parseInt(courseIndex), courses).getVacancy();
                System.out.println("Course Vacancy for "+courseIndex+" is "+courseVacancy);
                break;
            case "5":
                System.out.println("\nChange Index Number of Course");
                System.out.println("\nEnter current course index");
                courseIndex = sc.next();
                System.out.println("\nEnter a course index that you want to change to");
                String changeIndex;
                changeIndex = sc.next();
                currentStudent.RemoveCourse(GetCourse( Integer.parseInt(courseIndex), courses),w);
                currentStudent.AddCourse(GetCourse( Integer.parseInt(changeIndex), courses),w);
                break;
            case "6":
                System.out.println("\nSwop Index Number with Another Student");
                System.out.println("\nEnter other student's matriculation number");
                matricNo = sc.next();
                //get other student's corresponding object
                System.out.println("\nEnter a course index");
                courseIndex = sc.next();
                currentStudent.SwapCourse(GetStudent(Integer.parseInt(matricNo),students),GetCourse(Integer.parseInt(courseIndex), courses),Integer.parseInt(courseIndex));
                break;
            default:
        }
    }
}
private Course GetCourse(int courseIndex,List<Course> courses){
    List<Course> result = new ArrayList<Course>();
    //find corresponding course object using these course code & index
    result = filter.byIndex.Invoke(courses, Integer.toString(courseIndex));
    for(Course mod: result){
        //add course if found 
        return mod;
    }
    return null;
}
private Student GetStudent(int matricNo,List<Student> students){
    List<Student> result = new ArrayList<Student>();
    //find corresponding student object using these course code & index
    result = studentFilter.byMatricNo.Invoke(students, Integer.toString(matricNo));
    for(Student student: result){
        //add course if found 
        return student;
    }
    return null;
}
private void printallcourse(ArrayList<Course> courses){
    System.out.println("Courses registered :");
    for (Course course : courses){
        System.out.println(course.getCourseCode() + " : " + course.getIndex());
    }

}


  
}