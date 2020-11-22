import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Database {

    public List<Lesson> LessonListDatabase() {
        List<Lesson> fulllessonlist = new ArrayList<Lesson>();
        ArrayList<String> list = new ArrayList<String>();
        try {
            FileInputStream fis = new FileInputStream("Database\\lesson.txt");
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                list.add(sc.nextLine());
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // from courseList.txt

        for (String i : list) {
            String[] splitdata = i.split(",");
            fulllessonlist.add(new Lesson(Integer.parseInt(splitdata[0]), splitdata[1], splitdata[2],
                    Integer.parseInt(splitdata[3]), splitdata[4], Integer.parseInt(splitdata[5]),
                    Integer.parseInt(splitdata[6]), splitdata[7]));
        }
        return fulllessonlist;
    }

    public void UpdateLessonDatabase(List<Lesson> fullLessonList) throws IOException {
        FileWriter myWriter = new FileWriter("Database\\lesson.txt");
        // Index, Coursecode, Coursename, AU, Day, starttime, endtime, classtype
        fullLessonList.forEach(x -> {
            try {
                myWriter.write(x.getIndex() + "," + x.getCourseCode() + "," + x.getCourseName() + "," + x.getAU() + "," + x.getDay() + "," + x.getStartTime() + "," + x.getEndTime() + "," + x.getClassType()
                        + "\n");
            } catch (IOException e) {
                System.out.println("Update Lesson Database Failed");
                e.printStackTrace();
            }
        });
        myWriter.close();
    }
    

    public List<Admin> AdminUserDatabase() {
        List<Admin> fullAdminList = new ArrayList<Admin>();
        ArrayList<String> list = new ArrayList<String>();
        try {
            FileInputStream fis = new FileInputStream("Database\\admin.txt");
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                list.add(sc.nextLine());
            }
            sc.close();
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

    public List<Student> StudentDatabase() {
        List<Student> fullStudentList = new ArrayList<Student>();
        ArrayList<String> list = new ArrayList<String>();
        try {
            FileInputStream fis = new FileInputStream("Database\\student.txt");
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                list.add(sc.nextLine());
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // MatricNo, Username, Password, PartTimeStudent (true / false)
        for (String i : list) {
            String[] splitdata = i.split(",");
            fullStudentList
                    .add(new Student(splitdata[0], splitdata[1], splitdata[2], Boolean.parseBoolean(splitdata[3])));
        }
        return fullStudentList;
    }

    public void UpdateStudentDatabase(List<Student> fullStudentList) throws IOException {
        FileWriter myWriter = new FileWriter("Database\\student.txt");
        // MatricNo, Username, Password, PartTimeStudent (true / false)
        fullStudentList.forEach(x -> {
            try {
                myWriter.write(x.getMatid() + "," + x.getUsername() + "," + x.getPassword() + "," + x.getStudentStatus()
                        + "\n");
            } catch (IOException e) {
                System.out.println("Update Student Database Failed");
                e.printStackTrace();
            }
        });
        myWriter.close();
    }

    public List<Course> CourseListDatabase() {
        List<Course> fullcourselist = new ArrayList<Course>();
        ArrayList<String> list = new ArrayList<String>();
        try {
            FileInputStream fis = new FileInputStream("Database\\course.txt");
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                list.add(sc.nextLine());
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // index, coursecode, vacancy, waitlist
        for (String i : list) {
            String[] splitdata = i.split(",");
            fullcourselist.add(new Course(Integer.parseInt(splitdata[0]), splitdata[1], Integer.parseInt(splitdata[2]),
                    Integer.parseInt(splitdata[3])));
        }
        return fullcourselist;
    }
    public void UpdateCourseDatabase(List<Course> fullCourseList) throws IOException {
        FileWriter myWriter = new FileWriter("Database\\course.txt");
       // index, coursecode, vacancy, waitlist
        fullCourseList.forEach(x -> {
            try {
                myWriter.write(x.getIndex() + "," + x.getCourseCode() + "," + x.getVacancy() + "," + x.getWaitlist()
                        + "\n");
            } catch (IOException e) {
                System.out.println("Update Course Database Failed");
                e.printStackTrace();
            }
        });
        myWriter.close();
    }
}
