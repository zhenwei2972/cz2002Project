import java.util.ArrayList;

public class Student {
    //parameters for students
    private String matircid = "";
    private ArrayList<Module> modlist;
    private String username = "";
    private String password = "";
    private Boolean fullorPart = false; //true for part time , default false for full time

    public Student() {
        System.out.println("Constructing an empty Student");
        this.modlist = new ArrayList<Module>();
    }
    //default fulltime student constructor
    public Student(String matircid, String username, String password) {
        System.out.println("Constructing a Default Student");
        this.matircid = matircid;
        this.username = username;
        this.password = password;
        this.modlist = new ArrayList<Module>();
    }
    //self determine fulltime/parttime student constructor
    public Student(String matircid, String username, String password, Boolean fullorPart) {
        System.out.println("Constructing a Student" + fullorPart );
        this.matircid = matircid;
        this.username = username;
        this.password = password;
        this.fullorPart = fullorPart;
        this.modlist = new ArrayList<Module>();
    }
    
    //---------Getters----------//
    public String getpassword(){
        return this.password;
    }
    public String getusername(){
        return this.username;
    }
    public String getMatid(){
        return this.matircid;
    }
    public ArrayList<Module> getmodules(){
        return this.modlist;
    }
    public Boolean getStudentStatus(){
        return this.fullorPart;
    }

    //----------setter--------------------//
    public void setpassword(String password){
        this.password = password;
    }
    public void setusername(String username){
        this.username = username;
    }
    public void setmodule(Module mod){
        this.modlist.add(mod);
    }
}