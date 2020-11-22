import java.util.ArrayList;
import java.util.List;
public class Student implements User{
    //parameters for students
    private String matricID = "";
    private ArrayList<Course> modlist;
    private String username = "";
    private String password = "";
    private Boolean fullorPart = false; //true for part time , default false for full time
    private int year = 0;


    public Student() {
        this.modlist = new ArrayList<Course>();
    }
    //default fulltime student constructor
    public Student(String matricID, String username, String password) {
        this.matricID = matricID;
        this.username = username;
        this.password = password;
        this.modlist = new ArrayList<Course>();
    }
    //self determine fulltime/parttime student constructor
    public Student(String matricID, String username, String password, Boolean fullorPart,int year) {
        this.matricID = matricID;
        this.username = username;
        this.password = password;
        this.fullorPart = fullorPart;
        this.year = year;
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
    public int getYear(){
        return this.year;
    }

    //----------setter--------------------//
    public void setPassword(String password){
        this.password = password;
    }
    @Override
    public void setUsername(String inputID) {
        this.username = inputID;
    }
    public void addModList(Course course){
        this.modlist.add(course);
    }
    public void removeModList(Course course){
        this.modlist.remove(course);
    }
    public void setYear(int year){
        this.year = year;
    }
}