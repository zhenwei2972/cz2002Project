import java.util.ArrayList;

/**
 * Represents the Student object, unique to the matricID and username variable, each student
 * will have a specific matricid and username.
 * each student are allowed to store multiple course object as long as the object course code is not the same.
 * student can be parttime / fulltime
 * @author Team Stars
 * @version 1.0
 * @since 2020
 */

public class Student implements User{
    //parameters for students
    private String matricID = "";
    /**
     * matricID is the key variable which has to be unique
     */
    private ArrayList<Course> modlist;
    /**
     * The list of Courses the student is registered to 
     */
    private String username = "";
    /**
     * Username for login purpose
     */
    private String password = "";
    /**
     * Username for password purpose
     */
    private Boolean fullorPart = false; //true for part time , default false for full time
    /**
     * Boolean to determine fulltime student or part time
     */
    private int year = 0;
    /**
     * year of student in study, which also allow the set time access period
     */
    private String email = "";
    /**
     * email of student allows, the mail to be sent to the student
     */

    /**
     * this is an empty constructor for passing reference after sorting student object list
     */ 
    public Student() {
        this.modlist = new ArrayList<Course>();
    }
     /**
     * This is the constructor to construct Student objects 
     * @param matricID is this Student's Matric ID ( unique )
     * @param username is this student's Username
     * @param password is this Student's password 
     * is determine by true for part time , default false for full time
     * @param year is this student's year
     * @param email is this student's email address 
     */
    public Student(String matricID, String username, String password,int year,String email) {
        this.matricID = matricID;
        this.username = username;
        this.password = password;
        this.year = year;
        this.email = email;
        this.modlist = new ArrayList<Course>();
    }
    /**
     * This is the constructor to construct Student objects 
     * @param matricID is this Student's Matric ID ( unique )
     * @param username is this student's Username
     * @param password is this Student's password 
     * @param fullorPart is this students's parttime or full time
     * is determine by true for part time , default false for full time
     * @param year is this student's year
     * @param email is this student's email address 
     */
    public Student(String matricID, String username, String password, Boolean fullorPart,int year, String email) {
        this.matricID = matricID;
        this.username = username;
        this.password = password;
        this.fullorPart = fullorPart;
        this.year = year;
        this.email = email;
        this.modlist = new ArrayList<Course>();
    }

    //---------Getters----------//

    /**
     * return student's password
     * @return String password
     */
    public String getPassword(){
        return this.password;
    }
    /**
     * return student's username
     * @return String username
     */
    public String getUsername(){
        return this.username;
    }
    /**
     * return student's matric id
     * @return String matricID
     */
    public String getMatid(){
        return this.matricID;
    }
    /**
     * return student's registered courses
     * @return ArrayList of course object
     */
    public ArrayList<Course> getCourse(){
        return this.modlist;
    }
    /**
     * return student's fulltime or parttime status
     * @return boolean true for part time , default false for full time
     */
    public Boolean getStudentStatus(){
        return this.fullorPart;
    }
     /**
     * return student's year
     * @return int year
     */
    public int getYear(){
        return this.year;
    }
     /**
     * return student's email
     * @return string email
     */
    public String getEmail(){
        return this.email;
    }

    //----------setter--------------------//

     /**
     * set student's password
     * @param password as new password
     */
    public void setPassword(String password){
        this.password = password;
    }
     /**
     * set student's username
     * @param inputID as new username
     */
    @Override
    public void setUsername(String inputID) {
        this.username = inputID;
    }
    /**
     * register course to student
     * @param course as the course to register
     */
    public void addModList(Course course){
        this.modlist.add(course);
    }
     /**
     * remove course from student
     * @param course as the course to de-register
     */
    public void removeModList(Course course){
        this.modlist.remove(course);
    }
     /**
     * set year to student
     * @param year as new year
     */
    public void setYear(int year){
        this.year = year;
    }
     /**
     * set email to student
     * @param email as new email address
     */
    public void setEmail(String email){
        this.email = email;
    }
}