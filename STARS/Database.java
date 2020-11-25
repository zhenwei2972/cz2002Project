import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class Database {

    
    /** 
     * @return List<Lesson>
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
                    Integer.parseInt(splitdata[6]), splitdata[7]));
        }
        return fulllessonlist;
    }

    
    /** 
     * @param fullLessonList
     * @throws IOException
     */
    public void UpdateLessonDatabase(List<Lesson> fullLessonList) throws IOException {
        FileWriter myWriter = new FileWriter("Database\\lesson.txt");
        // Index, Coursecode, Coursename, AU, Day, starttime, endtime, classtype
        fullLessonList.forEach(x -> {
            try {
                myWriter.write(x.getIndex() + "," + x.getCourseCode() + "," + x.getCourseName() + "," + x.getAU() + ","
                        + x.getDay() + "," + x.getStartTime() + "," + x.getEndTime() + "," + x.getClassType() + "\n");
            } catch (IOException e) {
                System.out.println("Update Lesson Database Failed");
                e.printStackTrace();
            }
        });
        myWriter.close();
    }

    
    /** 
     * @return List<Admin>
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
     * @param fullAdminList
     * @throws IOException
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
     * @return List<Student>
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
     * @param fullStudentList
     * @throws IOException
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
     * @return List<Course>
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
     * @param fullCourseList
     * @throws IOException
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
     * @return List<AccessPeriod>
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
     * @param fullAccessPeriodlist
     * @throws IOException
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
}
