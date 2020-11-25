/**
 * This is the Course Object
 * it will have a unique index for each course
 * @author Team Stars
 * @version 1.0
 * @since 2020
 */
public class Course {
    /**
     * interger of index (unique) , vacancy , waitlist and au
     *
     */
    private int index = 0, vacancy = 0, waitlist =0, au =0;
    /**
     * string for the coursecode
     */
    private String courseCode = "";

    /**
     * empty constructor use for references 
     */
    public Course(){

    }
    /**
     * This is the constructor used to create course with the loaded text file
     * @param index index of each course
     * @param courseCode course code for the course
     * @param vacancy the ammount of slots available for students
     * @param waitlist the number students queueing for the index course
     * @param au the number of au is for the module
     */
    public Course(int index, String courseCode, int vacancy, int waitlist, int au){
        this.index = index;
        this.courseCode = courseCode;
        this.vacancy = vacancy;
        this.waitlist = waitlist;
        this.au = au;
    }
    //----------------------Getter----------------------//
    /**
     * getter method for index
     * @return integer course index
     */
    public int getIndex(){
        return index;
    }
    /**
     * getter method for course code
     * @return string course code
     */
    public String getCourseCode(){
        return courseCode;
    }
    /**
     * getter for slots available
     * @return integer course vacancy
     */
    public int getVacancy(){
        return vacancy;
    }
    /**
     * getter for numbers in queue
     * @return integer waitlist
     */
    public int getWaitlist(){
        return waitlist;
    }
    /**
     * getter mehtod for AU
     * @return integer of course AU
     */
    public int getAU(){
        return au;
    }
    //----------------------setter--------------------//
    /**
     * setter method for index
     * @param newIndex is the new index to update
     */
    public void setIndex(int newIndex){
        this.index = newIndex;
    }
    /**
     * setter method for course code
     * @param newcourseCode is the new course code to update
     */
    public void setCourseCode(String newcourseCode){
        this.courseCode = newcourseCode;
    }
    /**
     * setter method for course available slots
     * @param newVacancy is the new vacancy to update
     */
    public void setVacancy(int newVacancy){
        this.vacancy = newVacancy;
    }
    /**
     * setter method for course waitlist
     * @param newWaitlist is the new course waitlist to update
     */
    public void setWaitlist(int newWaitlist){
        this.waitlist = newWaitlist;
    }
    /**
     * setter method for course au
     * @param newAU is the new course au to update
     */
    public void setAu(int newAU){
        this.au = newAU;
    }
}
