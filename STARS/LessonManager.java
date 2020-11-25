import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Comparator;

/**
 * This is the lesson manager class.
 * it handles the filtering for lesson list and timetables
 * @author Team Stars
 * @version 1.0
 * @since 2020
 */
public class LessonManager {
     /**
     * a Functional interface is not other than a abstract method, which we are able polymorph into different methods
     * This method only works on Java 8 onwards
     * interface for flitering
     */
    @FunctionalInterface
    interface LessonFiltering{
        public List<Lesson> Invoke(String value);
    }
     /**
     * a Functional interface is not other than a abstract method, which we are able polymorph into different methods
     * This method only works on Java 8 onwards
     * interface for printing timetable
     */
    @FunctionalInterface
    interface TimeTableFiltering{
        public List<Lesson> Invoke(List<Integer> indexlist);
    }
    /**
     * using the database class
     */
    Database db = new Database();
    /**
     * using the database class to take all the lesson list
     */
    List<Lesson> fullLessonList = db.LessonListDatabase();
    
    /**
     * filter by index
     * @param stringIndex as the course index to filter
      */
    LessonFiltering byIndex = (stringIndex) -> {
        int value = Integer.parseInt(stringIndex);
        return fullLessonList.stream().filter(x -> value == x.getIndex()).collect(Collectors.toList());
    };
    /**
     * to get lessons for Monday only
     * @param indexList is the list of indexs to filter
     */
    TimeTableFiltering getMondayClasses = (indexList) ->{
        List<Lesson> result = new ArrayList<Lesson>();
        for(Integer i: indexList){
            List<Lesson> mondayClass = fullLessonList.stream().filter(x -> i == x.getIndex() && x.getDay().equals("Monday")).collect(Collectors.toList());
            result.addAll(mondayClass);
        }
        List<Lesson> sortedTime = result.stream().sorted(Comparator.comparingInt(Lesson::getStartTime)).collect(Collectors.toList());
        return sortedTime;
    };
    /**
     * to get lessons for Tuesday only
     * @param indexList is the list of indexs to filter
     */
    TimeTableFiltering getTuesdayClasses = (indexList) ->{
        List<Lesson> result = new ArrayList<Lesson>();
        for(Integer i: indexList){
            List<Lesson> mondayClass = fullLessonList.stream().filter(x -> i == x.getIndex() && x.getDay().equals("Tuesday")).collect(Collectors.toList());
            result.addAll(mondayClass);
        }
        List<Lesson> sortedTime = result.stream().sorted(Comparator.comparingInt(Lesson::getStartTime)).collect(Collectors.toList());
        return sortedTime;
    };
    /**
     * to get lessons for Wednessday only
     * @param indexList is the list of indexs to filter
     */
    TimeTableFiltering getWednesdayClasses = (indexList) ->{
        List<Lesson> result = new ArrayList<Lesson>();
        for(Integer i: indexList){
            List<Lesson> mondayClass = fullLessonList.stream().filter(x -> i == x.getIndex() && x.getDay().equals("Wednesday")).collect(Collectors.toList());
            result.addAll(mondayClass);
        }
        List<Lesson> sortedTime = result.stream().sorted(Comparator.comparingInt(Lesson::getStartTime)).collect(Collectors.toList());
        return sortedTime;
    };
    /**
     * to get lessons for Thursday only
     * @param indexList is the list of indexs to filter
     */
    TimeTableFiltering getThursdayClasses = (indexList) ->{
        List<Lesson> result = new ArrayList<Lesson>();
        for(Integer i: indexList){
            List<Lesson> mondayClass = fullLessonList.stream().filter(x -> i == x.getIndex() && x.getDay().equals("Thursday")).collect(Collectors.toList());
            result.addAll(mondayClass);
        }
        List<Lesson> sortedTime = result.stream().sorted(Comparator.comparingInt(Lesson::getStartTime)).collect(Collectors.toList());
        return sortedTime;
    };
    /**
     * to get lessons for Friday only
     * @param indexList is the list of indexs to filter
     */ 
    TimeTableFiltering getFridayClasses = (indexList) ->{
        List<Lesson> result = new ArrayList<Lesson>();
        for(Integer i: indexList){
            List<Lesson> mondayClass = fullLessonList.stream().filter(x -> i == x.getIndex() && x.getDay().equals("Friday")).collect(Collectors.toList());
            result.addAll(mondayClass);
        }
        List<Lesson> sortedTime = result.stream().sorted(Comparator.comparingInt(Lesson::getStartTime)).collect(Collectors.toList());
        return sortedTime;
    };
}
