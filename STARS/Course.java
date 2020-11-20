public class Course {
    // Time Format 20:15:40 hh:mm:ss
    // data column
    // index,coursecode,coursename,AU,day,starttime,endtime,classtype,vacancy,waitlist
    int index = 0, au = 0, vacancy = 0, waitlist = 0;
    String coursecode = "", coursename = "", day = "", classtype = "";
    int starttime = 0, endtime = 0;
    
    public Course(int index, String coursecode, String coursename, int au, String day, int starttime, int endtime,String classtype,int vacancy, int waitlist){
        this.index = index;
        this.coursecode = coursecode;
        this.coursename = coursename;
        this.au = au;
        this.day = day;
        this.starttime = starttime;
        this.endtime = endtime;
        this.classtype = classtype;
        this.vacancy = vacancy;
        this.waitlist = waitlist;
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
    public int getStartTime(){
        return this.starttime;
    }
    public int getEndTime(){
        return this.endtime;
    }
    public String getClassType(){
        return this.classtype;
    }
    public int getVacancy(){
        return this.vacancy;
    }
    public int getWaitlist(){
        return this.waitlist;
    }
    public void setIndex(int newIndex){
        this.index = newIndex;
    }
    public void setCourseCode(String newCourseCode){
        this.coursecode = newCourseCode;
    }
    public void setCourseName(String newCouseName){
        this.coursename = newCouseName;
    }
    public void setAU(int newAU){
        this.au = newAU;
    }
    public void setDay(String newDay){
        this.day = newDay;
    }
    public void setStartTime(int newStartTime){
        this.starttime = newStartTime;
    }
    public void setEndTime(int newEndTime){
        this.endtime = newEndTime;
    }
    public void setClassType(String newClassType){
        this.classtype = newClassType;
    }
    public void setVacancy(int newVacancy){
        this.vacancy = newVacancy;
    }
    public void setWaitlist(int newWaitlist){
        this.waitlist = newWaitlist;
    }
}
