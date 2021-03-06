import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * This is the database class,
 * handles loading of data from text and export to text
 * @author Team Stars
 * @version 1.0
 * @since 2020
 */
public class Database {
    /**
     * This function reads the lesson.txt and create all the lesson object
     * @return the list of lesson object
     */
    public List<Lesson> LessonListDatabase() {
        List<Lesson> fulllessonlist = new ArrayList<Lesson>();
        ArrayList<String> list = new ArrayList<String>();
        try {
            FileInputStream fis = new FileInputStream("Database\\lesson.txt");
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                list.add(sc.nextLine());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        // from courseList.txt

        for (String i : list) {
            String[] splitdata = i.split(",");
            fulllessonlist.add(new Lesson(Integer.parseInt(splitdata[0]), splitdata[1], splitdata[2],
                    Integer.parseInt(splitdata[3]), splitdata[4], Integer.parseInt(splitdata[5]),
                    Integer.parseInt(splitdata[6]), splitdata[7],splitdata[8]));
        }
        return fulllessonlist;
    }
    /**
     * write out the lessons to a lesson.txt file 
     * @param fullLessonList is the list of lesson object
     * @throws IOException is to handle write out exception or fails
     */
    public void UpdateLessonDatabase(List<Lesson> fullLessonList) throws IOException {
        FileWriter myWriter = new FileWriter("Database\\lesson.txt");
        // Index, Coursecode, Coursename, AU, Day, starttime, endtime, classtype
        fullLessonList.forEach(x -> {
            try {
                myWriter.write(x.getIndex() + "," + x.getCourseCode() + "," + x.getCourseName() + "," + x.getAU() + ","
                        + x.getDay() + "," + x.getStartTime() + "," + x.getEndTime() + "," + x.getClassType() + "," + x.getVenue() + "\n");
            } catch (IOException e) {
                System.out.println("Update Lesson Database Failed");
                e.printStackTrace();
            }
        });
        myWriter.close();
    }
    /**
     * this function loads the admin.txt
     * create a list of admin object(account)
     * @return a list of admin object
     */
    public List<Admin> AdminUserDatabase() {
        List<Admin> fullAdminList = new ArrayList<Admin>();
        ArrayList<String> list = new ArrayList<String>();
        try {
            FileInputStream fis = new FileInputStream("Database\\admin.txt");
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                list.add(sc.nextLine());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        // username, password
        for (String i : list) {
            String[] splitdata = i.split(",");
            fullAdminList.add(new Admin(splitdata[0], splitdata[1]));
        }
        return fullAdminList;
    }
    /**
     * this function write out the admin.txt
     * @param fullAdminList the list of admin object
     * @throws IOException handles the fail and writeout error
     */
    public void UpdateAdminDatabase(List<Admin> fullAdminList) throws IOException {
        FileWriter myWriter = new FileWriter("Database\\admin.txt");
        // username, password
        fullAdminList.forEach(x -> {
            try {
                myWriter.write(x.getUsername() + "," + x.getUsername() + "\n");
            } catch (IOException e) {
                System.out.println("Update Admin Database Failed");
                e.printStackTrace();
            }
        });
        myWriter.close();
    }
    /**
     * Load and read student.txt 
     * create the list of student object(account)
     * @return the list of student object
     */
    public List<Student> StudentDatabase() {
        List<Student> fullStudentList = new ArrayList<Student>();
        ArrayList<String> list = new ArrayList<String>();
        try {
            FileInputStream fis = new FileInputStream("Database\\student.txt");
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                list.add(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // MatricNo, Username, Password, PartTimeStudent (true / false)
        for (String i : list) {
            String[] splitdata = i.split(",");
            fullStudentList.add(new Student(splitdata[0], splitdata[1], splitdata[2],
                    Boolean.parseBoolean(splitdata[3]), Integer.parseInt(splitdata[4]), splitdata[5]));
        }
        return fullStudentList;
    }
    /**
     * write out the list of student data to txt
     * @param fullStudentList the lsit of student acount    
     * @throws IOException handles the error and fails of write out
     */
    public void UpdateStudentDatabase(List<Student> fullStudentList) throws IOException {
        FileWriter myWriter = new FileWriter("Database\\student.txt");
        // MatricNo, Username, Password, PartTimeStudent (true / false)
        fullStudentList.forEach(x -> {
            try {
                myWriter.write(x.getMatid() + "," + x.getUsername() + "," + x.getPassword() + "," + x.getStudentStatus()
                        + "," + x.getYear() + "," + x.getEmail() + "\n");
            } catch (IOException e) {
                System.out.println("Update Student Database Failed");
                e.printStackTrace();
            }
        });
        myWriter.close();
    }

    /**
     * Load and read course.txt 
     * create the list of course object(account)
     * @return the list of course object
     */
    public List<Course> CourseListDatabase() {
        List<Course> fullcourselist = new ArrayList<Course>();
        ArrayList<String> list = new ArrayList<String>();
        try {
            FileInputStream fis = new FileInputStream("Database\\course.txt");
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                list.add(sc.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // index, coursecode, vacancy, waitlist
        for (String i : list) {
            String[] splitdata = i.split(",");
            fullcourselist.add(new Course(Integer.parseInt(splitdata[0]), splitdata[1], Integer.parseInt(splitdata[2]),
                    Integer.parseInt(splitdata[3]), Integer.parseInt(splitdata[4])));
        }
        return fullcourselist;
    }

    /**
     * this function write out the course list to course.txt
     * @param fullCourseList the course list
     * @throws IOException handle the error and write out fails
     */
    public void UpdateCourseDatabase(List<Course> fullCourseList) throws IOException {
        FileWriter myWriter = new FileWriter("Database\\course.txt");
        // index, coursecode, vacancy, waitlist
        fullCourseList.forEach(x -> {
            try {
                myWriter.write(x.getIndex() + "," + x.getCourseCode() + "," + x.getVacancy() + "," + x.getWaitlist()
                        + "," + x.getAU() + "\n");
            } catch (IOException e) {
                System.out.println("Update Course Database Failed");
                e.printStackTrace();
            }
        });
        myWriter.close();
    }
    /**
     * Load and read accessperiod.txt 
     * create the list of acessperiod object(account)
     * @return the list of accessperiod object
     */
    public List<AccessPeriod> AccessPeriodDatabase() {
        List<AccessPeriod> fullAccessPeriodlist = new ArrayList<AccessPeriod>();
        ArrayList<String> list = new ArrayList<String>();
        try {
            FileInputStream fis = new FileInputStream("Database\\accessperiod.txt");
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                list.add(sc.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // index, coursecode, vacancy, waitlist
        for (String i : list) {
            String[] splitdata = i.split(",");
            String startdate = splitdata[0] + "," + splitdata[1];
            String enddate = splitdata[2] + "," + splitdata[3];
            fullAccessPeriodlist.add(new AccessPeriod(startdate, enddate, splitdata[4]));
        }
        return fullAccessPeriodlist;
    }
     /**
     * this function write out the accessperiod to accessperiod.txt
     * @param fullAccessPeriodlist the accessperiod
     * @throws IOException handle the error and write out fails
     */
    public void UpdateAccessListDatabase(List<AccessPeriod> fullAccessPeriodlist) throws IOException {
        FileWriter myWriter = new FileWriter("Database\\accessperiod.txt");
        // index, coursecode, vacancy, waitlist
        fullAccessPeriodlist.forEach(x -> {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy HH:mm");
                String startDate = sdf.format(x.getStartDate());
                String endDate = sdf.format(x.getEndDate());
                myWriter.write(startDate+ "," + endDate + "," + x.getYear() + "\n");
            } catch (IOException e) {
                System.out.println("Update Access Period Database Failed");
                e.printStackTrace();
            }
        });
        myWriter.close();
    }
    /**
     * This function to prepopulate Student with courses
     * @param fullStudentList as the student list
     * @param fullCourseList as the course list
     * @param sm as the studentmanager
     * @param waitList as the waitlist
     */
    public void PrepopulateStudentCourses(List<Student> fullStudentList,List<Course> fullCourseList,StudentManager sm,WaitList waitList){
          //populate student1 with course 10271, so can drop later to add student2 from waitlist to course.
       System.out.println("Pre-initializing data");
       Student student1 = fullStudentList.get(1);
       Course chosenCourse = (fullCourseList.stream().filter(x -> Integer.parseInt("10271") == x.getIndex()).collect(Collectors.toList())).get(0);
       sm.AddCourse(chosenCourse, waitList, student1);
       System.out.println(fullStudentList.get(1).getUsername()+chosenCourse.getIndex());
       //STUDENT 2 ONLY FOR WAITLIST
       //prepopulate student2 with course 10271 to add student2 to wait list for 10271
       Student student2 = fullStudentList.get(2);
       chosenCourse = (fullCourseList.stream().filter(x -> Integer.parseInt("10271") == x.getIndex()).collect(Collectors.toList())).get(0);
       System.out.println(student2.getUsername()+chosenCourse.getIndex());
       
       sm.AddCourse(chosenCourse, waitList, student2);
       //populate extra students to other courses for printCoursesByIndex etc
       Student student3 = fullStudentList.get(3);
       chosenCourse = (fullCourseList.stream().filter(x -> Integer.parseInt("10242") == x.getIndex()).collect(Collectors.toList())).get(0);
       sm.AddCourse(chosenCourse, waitList, student3);

       //add 10215 , drop 10271, add 10215 , swap student 3  10242 , change index 10242 with 10241 , check vacancy with 10241
    }
}
