
import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class StudentInterface {
    public void StudentMenuLogic(Student currentStudent,Database ctrl) {
        String num = "";
        Console console = System.console();
        System.out.println("Welcome to Student Mode");
        Boolean quit = false;
        Scanner sc = new Scanner(System.in);
        //Initialize common variable/objets
        WaitList w = new WaitList();
        String courseCode = "";
        String courseIndex = "";
        List<Course> courses= ctrl.CourseListDatabase();

        while (!quit)
        {
        System.out.println(
                " 1 *Add Course \n 2 Drop Course \n 3 Check/Print Courses Registered \n 4 Check Vacancies Available \n 5 Change Index Number of Course \n 6 Swop Index Number with Another Student ");
        num = console.readLine("Please choose your action \n");
        switch (num) {
            case "1":
            //initialize course object with default values first.
                Course test = new Lesson(10214,"CZ2003","Computer Graphics",3,"Wednesday",830,930,"Tutorial");
                System.out.println("\nEnter a course code, course index");
                //need to convert to user input 
                courseCode = sc.next();
                courseIndex = sc.next();
                //find corresponding course object using these course code & index
                for(int i = 0; i < courses.size(); i++)
                {
                    if((courses.get(i).coursename==courseCode) && (courses.get(i).index==Integer.parseInt(courseIndex)))
                    {
                        test = courses.get(i);
                    }
                    else
                    {
                        //System.out.println("Course Does not exist");
                        break;
                    }

                }
                //add course if found 
                currentStudent.AddCourse(test,w);
                System.out.println("\n ----");
                break;
            case "2":
                System.out.println("\nRemove a course");
                System.out.println("\nEnter a course code, course index");
                //need to convert to user input  # TBD
                courseCode = sc.next();
                courseIndex = sc.next();
                //find corresponding course object using these course code & index
                Course courseToBeRemoved = new Course(10214,"CZ2003","Computer Graphics",3,"Wednesday",830,930,"Tutorial",2,1);
                currentStudent.RemoveCourse(courseToBeRemoved,w);
                break;
            case "3":
                System.out.println("\nEnter a student name");
                break;
            case "4":
                System.out.println("\nEnter a student name");
                break;
            case "5":
                System.out.println("\nEnter a student name");
                break;
            case "6":
                System.out.println("\nEnter a student name");
                break;
            default:
        }
    }
}



  
}