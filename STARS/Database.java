import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Database {

    public List<Lesson> LessonListDatabase() {
        List<Lesson> fullmodulelist = new ArrayList<Lesson>();
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
        //from courseList.txt

        for (String i : list) {
            String[] splitdata = i.split(",");
            fullmodulelist.add(new Lesson(Integer.parseInt(splitdata[0]), splitdata[1], splitdata[2],
                    Integer.parseInt(splitdata[3]), splitdata[4], Integer.parseInt(splitdata[5]), Integer.parseInt(splitdata[6]), splitdata[7]));
        }
        return fullmodulelist;
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
        for (String i : list) {
            String[] splitdata = i.split(",");
            fullStudentList.add(new Student(splitdata[0],splitdata[1],splitdata[2],Boolean.parseBoolean(splitdata[3])));
        }
        return fullStudentList;
    }

    public List<Course> ModuleListDatabase() {
        List<Course> fullmodulelist = new ArrayList<Course>();
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
        //from courseList.txt
        // index, coursecode, vacancy, waitlist
        for (String i : list) {
            String[] splitdata = i.split(",");
            fullmodulelist.add(new Course(Integer.parseInt(splitdata[0]), splitdata[1],Integer.parseInt(splitdata[2]),Integer.parseInt(splitdata[3]));
        }
        return fullmodulelist;
    }
}
