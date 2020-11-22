import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AdminManager {
    interface PrintStudentList {
        public void Invoke(List<Student> studentList, String value);
    }

    public List<Student> addStudent(List<Student> fullStudentList) {
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
        fullStudentList.add(new Student(matricNo, studentName, studentPassword, partTime,year));

        sc.close();
        return fullStudentList;
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
}
