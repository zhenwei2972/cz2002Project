import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
public class AdminManager {
    interface PrintStudentList {
        public void Invoke(List<Student> studentList, String value);
    }

    public void addStudent(List<Student> fullStudentList) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Student's Matriculation Number");
        String matricNo = sc.next();

        System.out.println("Enter Student's Username");
        String studentName = sc.next();

        System.out.println("Enter Student's Password");
        String studentPassword = sc.next();

        System.out.println("Is this Part-Time Student? (Y/N)");
        String option = sc.next().toUpperCase();
        Boolean partTime = (option.equals("Y")) ? true : false;

        System.out.println("Enter Student Year");
        int year = sc.nextInt();

        System.out.println("Enter Student Email");
        String email = sc.next();
        fullStudentList.add(new Student(matricNo, studentName, studentPassword, partTime,year, email));

    }

    public List<Student> removeStudent(List<Student> fullStudentList, String matricNo) {
        return fullStudentList.stream().filter(x -> !x.getMatid().equals(matricNo)).collect(Collectors.toList());
    }

    PrintStudentList printStudentfromIndex = (fullStudentList, indexInString) -> {

        int index = Integer.parseInt(indexInString);
        fullStudentList.stream().forEach(i -> {
            List<Course> studentCourse = i.getCourse();
            studentCourse.stream().filter(j -> index == j.getIndex()).forEach(x -> System.out.println("Student: " + i.getUsername()));
        });

        //This code is the same as the top
        // for (Student i : fullStudentList) {
        //     List<Course> studentCourse = i.getmodules();
        //     for (Course j : studentCourse) {
        //         if (index == j.index) {
        //             System.out.println("Student: " + i.getUsername());
        //         }
        //     }
        // }
    };

    PrintStudentList printStudentFromCourse = (fullStudentList, courseCode) -> {
        fullStudentList.stream().forEach(i -> {
            List<Course> studentCourse = i.getCourse();
            studentCourse.stream().filter(j -> courseCode.equals(j.getCourseCode())).forEach(x -> System.out.println("Student: " + i.getUsername()));
        });

        //This code is the same as the top
        // for (Student i : fullStudentList) {
        //     List<Course> studentCourse = i.getmodules();
        //     for (Course j : studentCourse) {
        //         if (courseCode.equals(j.coursecode)) {
        //             System.out.println("Student Name: " + i.getUsername());
        //         }
        //     }
        // }
    };
    public Date InputDateTime(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter Day i.e 01");
        String day = sc.next();
        System.out.println("\nEnter Month i.e (JAN)");
        String month = sc.next();
        System.out.println("\nEnter Year i.e 2002");
        String year = sc.next();
        System.out.println("\n Enter Hour i.e 12");
        String hour = sc.next();
        System.out.println("\n Enter Minute i.e 15");
        String min = sc.next();
        String dateInString = month+" "+day+", "+year+" "+hour+":"+min;
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy HH:mm");
       // String example = "Jun 7, 2013 12:10";

        try {
            Date date = formatter.parse(dateInString);
          //  System.out.println(date);
            System.out.println("entered "+formatter.format(date));
            return date;

        } catch (ParseException e) {
            System.out.println("Incorrect Entry");
            e.printStackTrace();
        }
        return null;
    }
}
