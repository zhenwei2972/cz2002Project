import java.util.ArrayList;

public class Student implements User{
    //parameters for students
    private String matricID = "";
    private ArrayList<Course> modlist;
    private String username = "";
    private String password = "";
    private Boolean fullorPart = false; //true for part time , default false for full time

    public Student() {
        System.out.println("Constructing an empty Student");
        this.modlist = new ArrayList<Course>();
    }
    //default fulltime student constructor
    public Student(String matricID, String username, String password) {
        System.out.println("Constructing a Default Student");
        this.matricID = matricID;
        this.username = username;
        this.password = password;
        this.modlist = new ArrayList<Course>();
    }
    //self determine fulltime/parttime student constructor
    public Student(String matricID, String username, String password, Boolean fullorPart) {
        System.out.println("Constructing a Student" + fullorPart );
        this.matricID = matricID;
        this.username = username;
        this.password = password;
        this.fullorPart = fullorPart;
        this.modlist = new ArrayList<Course>();
    }
    
    //---------Getters----------//
    public String getPassword(){
        return this.password;
    }
    public String getUsername(){
        return this.username;
    }
    public String getMatid(){
        return this.matricID;
    }
    public ArrayList<Course> getCourse(){
        return this.modlist;
    }
    public Boolean getStudentStatus(){
        return this.fullorPart;
    }

    //----------setter--------------------//
    public void setPassword(String password){
        this.password = password;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public void AddCourse(Course mod){
        this.modlist.add(mod);
    }
    //---------delete------------------//
    public void RemoveCourse(Course mod){
        this.modlist.remove(mod);
    }
}