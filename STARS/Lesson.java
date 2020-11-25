/**
 * This is a Lesson Object class
 * it stores the lessons:
 * index , coursecode, day , time ,au and lesson type eg. Lab/Tut/Lec
 * @author Team Stars
 * @version 1.0
 * @since 2020
 */
public class Lesson {
    /** Time Format 20:15:40 hh:mm:ss
    * data column
    * index,coursecode,coursename,AU,day,starttime,endtime,classtype
    */
    private int index = 0, au = 0;
    private String coursecode = "", coursename = "", day = "", classtype = "", venue ="";
    private int starttime = 0, endtime = 0;
    /**
     * This is the lesson constructor
     * @param index as the course index for refernce
     * @param coursecode as the course code
     * @param coursename as the course name
     * @param au as the au for the course
     * @param day as which day the lesson is on
     * @param starttime as the starting time of the lesson  
     * @param endtime as the ending time of the lesson
     * @param classtype as the type of lesson it is Lab/Lec/Tut
     * @param venue as the place the lesson is held
     */
    public Lesson(int index, String coursecode, String coursename, int au, String day, int starttime, int endtime,String classtype,String venue){
        this.index = index;
        this.coursecode = coursecode;
        this.coursename = coursename;
        this.au = au;
        this.day = day;
        this.starttime = starttime;
        this.endtime = endtime;
        this.classtype = classtype;
        this.venue = venue;
    }
    
    /** 
     * getter method for index
     * @return integer index
     */
    public int getIndex(){
        return this.index;
    }
    
    /** 
     * getter method for course code
     * @return String course code
     */
    public String getCourseCode(){
        return this.coursecode;
    }
    
    /**
     * getter method to get course name
     * @return String course name
     */
    public String getCourseName(){
        return this.coursename;
    }
    
    /**
     * getter method for AU
     * @return integer course AU
     */
    public int getAU(){
        return this.au;
    }
    
    /** 
     * getter method for Day
     * @return String Day
     */
    public String getDay(){
        return this.day;
    }
    
    /** 
     * getter method for start time
     * @return integer start time
     */
    public int getStartTime(){
        return this.starttime;
    }
    
    /** 
     * getter method for end time
     * @return integer end time
     */
    public int getEndTime(){
        return this.endtime;
    }
    
    /** 
     * getter method for class type
     * @return String class type 
     */
    public String getClassType(){
        return this.classtype;
    }
     /**
      * getter method for venue
      * @return String venue
      */
    public String getVenue(){
        return this.venue;
    }
    
    /**
     * setter method for index
     * @param newIndex as the new index
     */
    public void setIndex(int newIndex){
        this.index = newIndex;
    }
    
    /** 
     * setter method for course code
     * @param newCourseCode as the new course code
     */
    public void setCourseCode(String newCourseCode){
        this.coursecode = newCourseCode;
    }
    
    /** 
     * setter method for course name
     * @param newCouseName as the new course name
     */
    public void setCourseName(String newCouseName){
        this.coursename = newCouseName;
    }
    
    /** 
     * setter method for AU
     * @param newAU as the new AU
     */
    public void setAU(int newAU){
        this.au = newAU;
    }
    
    /** 
     * setter method for Day
     * @param newDay as the new day value
     */
    public void setDay(String newDay){
        this.day = newDay;
    }
    
    /** 
     * setter method for start time
     * @param newStartTime as the new start time 
     */
    public void setStartTime(int newStartTime){
        this.starttime = newStartTime;
    }
    
    /** 
     * setter method for end time
     * @param newEndTime as the new end time
     */
    public void setEndTime(int newEndTime){
        this.endtime = newEndTime;
    }
    
    /**
     * setter method for class type 
     * @param newClassType as the new class type
     */
    public void setClassType(String newClassType){
        this.classtype = newClassType;
    }
    /** 
     * stter method for the venue
     * @param newVenue as the new venue
     */
    public void setVenue(String newVenue){
        this.venue = newVenue;
    }
    
}
