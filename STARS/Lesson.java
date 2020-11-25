public class Lesson {
    // Time Format 20:15:40 hh:mm:ss
    // data column
    // index,coursecode,coursename,AU,day,starttime,endtime,classtype
    private int index = 0, au = 0;
    private String coursecode = "", coursename = "", day = "", classtype = "";
    private int starttime = 0, endtime = 0;
    
    public Lesson(int index, String coursecode, String coursename, int au, String day, int starttime, int endtime,String classtype){
        this.index = index;
        this.coursecode = coursecode;
        this.coursename = coursename;
        this.au = au;
        this.day = day;
        this.starttime = starttime;
        this.endtime = endtime;
        this.classtype = classtype;
    }
    
    /** 
     * @return int
     */
    public int getIndex(){
        return this.index;
    }
    
    /** 
     * @return String
     */
    public String getCourseCode(){
        return this.coursecode;
    }
    
    /** 
     * @return String
     */
    public String getCourseName(){
        return this.coursename;
    }
    
    /** 
     * @return int
     */
    public int getAU(){
        return this.au;
    }
    
    /** 
     * @return String
     */
    public String getDay(){
        return this.day;
    }
    
    /** 
     * @return int
     */
    public int getStartTime(){
        return this.starttime;
    }
    
    /** 
     * @return int
     */
    public int getEndTime(){
        return this.endtime;
    }
    
    /** 
     * @return String
     */
    public String getClassType(){
        return this.classtype;
    }
    
    /** 
     * @param newIndex
     */
    public void setIndex(int newIndex){
        this.index = newIndex;
    }
    
    /** 
     * @param newCourseCode
     */
    public void setCourseCode(String newCourseCode){
        this.coursecode = newCourseCode;
    }
    
    /** 
     * @param newCouseName
     */
    public void setCourseName(String newCouseName){
        this.coursename = newCouseName;
    }
    
    /** 
     * @param newAU
     */
    public void setAU(int newAU){
        this.au = newAU;
    }
    
    /** 
     * @param newDay
     */
    public void setDay(String newDay){
        this.day = newDay;
    }
    
    /** 
     * @param newStartTime
     */
    public void setStartTime(int newStartTime){
        this.starttime = newStartTime;
    }
    
    /** 
     * @param newEndTime
     */
    public void setEndTime(int newEndTime){
        this.endtime = newEndTime;
    }
    
    /** 
     * @param newClassType
     */
    public void setClassType(String newClassType){
        this.classtype = newClassType;
    }
}
