public class Course {
    private int index = 0, vacancy = 0, waitlist =0;
    String courseCode = "";

    public Course(int index, String courseCode, int vacancy, int waitlist){
        this.index = index;
        this.courseCode = courseCode;
        this.vacancy = vacancy;
        this.waitlist = waitlist;
    }
}
