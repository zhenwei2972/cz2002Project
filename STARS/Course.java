public class Course {
    private int index = 0, vacancy = 0, waitlist =0;
    private String courseCode = "";

    public Course(){

    }
    public Course(int index, String courseCode, int vacancy, int waitlist){
        this.index = index;
        this.courseCode = courseCode;
        this.vacancy = vacancy;
        this.waitlist = waitlist;
    }
    public int getIndex(){
        return index;
    }
    public String getCourseCode(){
        return courseCode;
    }
    public int getVacancy(){
        return vacancy;
    }
    public int getWaitlist(){
        return waitlist;
    }
    public void setIndex(int newIndex){
        this.index = newIndex;
    }
    public void setCourseCode(String newcourseCode){
        this.courseCode = newcourseCode;
    }
    public void setVacancy(int newVacancy){
        this.vacancy = newVacancy;
    }
    public void setWaitlist(int newWaitlist){
        this.waitlist = newWaitlist;
    }

}
