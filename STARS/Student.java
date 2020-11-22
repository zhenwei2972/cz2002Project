import java.util.ArrayList;
import java.util.List;
public class Student implements User{
    //parameters for students
    private String matricID = "";
    private ArrayList<Course> modlist;
    private String username = "";
    private String password = "";
    private Boolean fullorPart = false; //true for part time , default false for full time

    CourseManager filter = new CourseManager();

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
    @Override
    public void setUserName(String inputID) {
        this.username = inputID;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public void AddCourse(Course mod,WaitList waitinglist){
        if(checkexist(mod)){
            if(!checkclash(mod)){
                if(mod.getVacancy() > 0){
                this.modlist.add(mod);
                mod.setVacancy(mod.getVacancy()-1);
                }
                else
                {
                    System.out.println("This course "+ mod.getCourseCode() + " : " + mod.getIndex() +" is full");
                    System.out.println("Adding to waitlist");
                    waitinglist.AddtoWaitList(this, mod);
                }
            } 
            else 
            System.out.println("There is a clash for this course added -index : " + mod.getIndex());
        }
        else
        System.out.println("There is an exisitng course added index : " + mod.getIndex());
    }
    public void AddCourse(Course mod){
        if(checkexist(mod)){
            if(!checkclash(mod)){
                if(mod.getVacancy() > 0){
                this.modlist.add(mod);
                mod.setVacancy(mod.getVacancy()-1);
                }
            }
            else  
            System.out.println("There is a clash for this course added -index : " + mod.getIndex());
        }
        else
        System.out.println("There is and exisitng course : " + mod.getIndex());
    }

    //---------delete------------------//
    public void RemoveCourse(Course mod,WaitList waitinglist){
        if(!checkexist(mod)){
            mod.setVacancy(mod.getVacancy()+1);
            this.modlist.remove(mod);
            waitinglist.AddCoursetoStudent(mod);
        }
        else
        System.out.println("There is no " + mod.getCourseCode() + " exsisting in your registered course");
    }
    public void RemoveCourse(Course mod){
        if(!checkexist(mod)){
            mod.setVacancy(mod.getVacancy()+1);
            this.modlist.remove(mod);
        }
        else
        System.out.println("There is no " + mod.getCourseCode() + " exsisting in your registered course");
    }

    //------------------- checking -------------------//
    private boolean checkexist(Course mod){
        List<Course> courses = this.modlist;
        List<Course> result = new ArrayList<Course>();
        result = filter.byModuleCode.Invoke(courses, mod.getCourseCode());
        if(result.isEmpty()){
            return true;
        }
        return false;
    }
    /**
     * checking for time slot clash between modules.
     */
    private boolean checkclash(Course course){
        List<Course> courses = this.modlist;
        List<Course> result = new ArrayList<Course>();
        for(Course mod: result){
           
        }
        return false;
    }
    //----------------- functions ---------------------//
    public void SwapCourse(Student b , Course m , int modid){
        List<Course> result = new ArrayList<Course>();
        List<Course> courselist =  b.getCourse();
        result = filter.byIndex.Invoke(courselist, Integer.toString(modid));
        if (result.isEmpty()){
            System.out.println("Student"+ b.username + " does not have " + m.getCourseCode() + ":" + modid);
            return;
        }
        else{
            b.AddCourse(m);
            b.RemoveCourse(result.get(0));
            this.RemoveCourse(m);
            this.AddCourse(result.get(0));
        }
    }
}