import java.io.*;

public class Module {
    // Time Format 20:15:40
    // data column
    // index,coursecode,coursename,AU,day,starttime,endtime,classtype,vacancy
    int index = 0, au = 0, vacancy = 0;
    String coursecode = "", coursename = "", day = "", classtype = "";
    String starttime = "", endtime = "";
    
    public Module(int index, String coursecode, String coursename, int au, String day, String starttime, String endtime,String classtype,int vacancy){
        this.index = index;
        this.coursecode = coursecode;
        this.coursename = coursename;
        this.au = au;
        this.day = day;
        this.starttime = starttime;
        this.endtime = endtime;
        this.classtype = classtype;
        this.vacancy = vacancy;
    }
    public int getIndex(){
        return this.index;
    }
    public String getCourseCode(){
        return this.coursecode;
    }
    public String getCourseName(){
        return this.coursename;
    }
    public int getAU(){
        return this.au;
    }
    public String getDay(){
        return this.day;
    }
    public String getStartTime(){
        return this.starttime;
    }
    public String getEndTime(){
        return this.endtime;
    }
    public String getClassType(){
        return this.classtype;
    }
    public int getVacancy(){
        return this.vacancy;
    }
    public void setVacancy(int newVacancy){
        this.vacancy = newVacancy;
    }
}
