import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {

    public List<Course> ModuleListDatabase() {
        List<Course> fullmodulelist = new ArrayList<Course>();
        ArrayList<String> list = new ArrayList<String>();
        try {
            FileInputStream fis = new FileInputStream("Database\\courseList.txt");
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                list.add(sc.nextLine());
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // index, coursecode, coursename, AU, day, starttime, endtime, classtype, vacancy
        for (String i : list) {
            String[] splitdata = i.split(",");
            fullmodulelist.add(new Course(Integer.parseInt(splitdata[0]), splitdata[1], splitdata[2],
                    Integer.parseInt(splitdata[3]), splitdata[4], Integer.parseInt(splitdata[5]), Integer.parseInt(splitdata[6]), splitdata[7],
                    Integer.parseInt(splitdata[8]),Integer.parseInt(splitdata[9])));
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
}
