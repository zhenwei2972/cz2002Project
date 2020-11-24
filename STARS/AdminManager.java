import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class AdminManager {
    interface PrintStudentList {
        public void Invoke(List<Student> studentList, String value);
    }

    public void addStudent(List<Student> fullStudentList) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Student's Matriculation Number");
        String matricNo = sc.next();

        if(!checkExistingStudent(fullStudentList,matricNo))
        {
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
        else{
            System.out.println("Student already exist in records");
        }
    }
    public void addCourse(List<Course> courseList,List<Lesson> lessonList) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Course Index Number");
        int courseNo =0;
        if (sc.hasNextInt()) { 
            courseNo = sc.nextInt();
        } 
        else { 
            System.out.println("Wrong input");
            return;
        } 

        if(!checkExistingCourse(courseList,courseNo))
        {
            System.out.println("Enter Course Code");
            String courseCode = sc.next();
            int vacancy =0;
            int au =0;
            int lessonNo;
            int starttime;
            int endtime;
            String classtype,courseName;
            String day;
            System.out.println("Enter Vacancy");
            if (sc.hasNextInt()) { 
                vacancy = sc.nextInt();
            } 
            else { 
                System.out.println("Wrong input");
                return;
            } 
            System.out.println("Enter Course AU");
            if (sc.hasNextInt()) { 
                au = sc.nextInt();
            } 
            else { 
                System.out.println("Wrong input");
                return;
            } 

            System.out.println("Enter Course Name");
            courseName = sc.next();

            System.out.println("Enter number of lessons");
            if (sc.hasNextInt()) { 
                lessonNo = sc.nextInt();
            } 
            else { 
                System.out.println("Wrong input");
                return;
            } 
            for(int i=0; i < lessonNo; i ++){
                System.out.println("Enter start time 0000-2359 (24hour time)");
                if (sc.hasNextInt()) { 
                    starttime = sc.nextInt();
                    if(starttime>2359)
                    {
                        System.out.println("Time must be within range 0000-2359");
                        return;
                    }
                } 
                else { 
                    System.out.println("Wrong input");
                    return;
                } 
                System.out.println("Enter end time 0000-2359 (24hour time)");
                if (sc.hasNextInt()) { 
                    endtime = sc.nextInt();
                    if(endtime>2359)
                    {
                        System.out.println("Time must be within range 0000-2359");
                        return;
                    }
                    else if( endtime<=starttime)
                    {
                        System.out.println("End time must be before Start time");
                        return;
                    }
                } 
                else { 
                    System.out.println("Wrong input");
                    return;
                } 
                System.out.println("Enter Course Type");
                classtype = sc.next();
                if(!(classtype.equals("Lecture")||classtype.equals("Tutorial")||classtype.equals("Lab")))
                {
                    return;
                }
                System.out.println("Enter Day(i.e Monday)");
                day = sc.next();
                if(!(day.equals("Monday")||day.equals("Tuesday")||day.equals("Wednesday")||day.equals("Thursday")||day.equals("Friday")))
                {
                    return;
                }
                lessonList.add(new Lesson(courseNo,courseCode,courseName,au,day,starttime,endtime,classtype));
            }
            courseList.add(new Course(courseNo,courseCode,vacancy,0,au));
            System.out.println("Course successfully added");
        }
        else{
            System.out.println("Course already exist in records");
        }
    }
    private boolean checkExistingCourse(List<Course> courseList,int courseNo){
        List<Course> tempCourse =courseList.stream().filter(x -> x.getIndex()==courseNo).collect(Collectors.toList());
        if(tempCourse.isEmpty())
        {

            return false;
        }
        return true;
    }

    public List<Student> removeStudent(List<Student> fullStudentList, String matricNo) {
        
        return fullStudentList.stream().filter(x -> x.getMatid().equals(matricNo)).collect(Collectors.toList());
    }
    private boolean checkExistingStudent(List<Student> fullStudentList,String matricNo){
        List<Student> tempStudent =fullStudentList.stream().filter(x -> x.getMatid().equals(matricNo)).collect(Collectors.toList());
        System.out.println(tempStudent);
        if(tempStudent.isEmpty())
        {

            return false;
        }
        return true;
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
        System.out.println("\nEnter Hour i.e 12");
        String hour = sc.next();
        System.out.println("\nEnter Minute i.e 15");
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
