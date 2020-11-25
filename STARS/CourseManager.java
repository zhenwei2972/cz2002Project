import java.util.stream.Collectors;
import java.util.List;
/**
 * This is a CourseManger object
 * it handles the filering of course
 * @author Team Stars
 * @version 1.0
 * @since 2020
 */
public class CourseManager {
    
    /**
     * a Functional interface is not other than a abstract method, which we are able polymorph into different methods
     * This method only works on Java 8 onwards
     */
    @FunctionalInterface
    interface CourseFiltering {
        public List<Course> Invoke(List<Course> list, String value);
    }
    /**
     * a Functional interface is not other than a abstract method, which we are able polymorph into different methods
     * This method only works on Java 8 onwards
     */
    @FunctionalInterface
    interface ResultDisplay {
        public void Display(List<Course> list);
    } 
    /**
     * Print all courses
     */
    ResultDisplay AvailableCourse = (result) -> result.forEach(x -> System.out.println("Index: " + x.getIndex() + " Course Code: " + x.getCourseCode() + " Vacancy: " + x.getVacancy() + " Waiting List: " + x.getWaitlist()));
    /**
     * Print course index waiting qeueue numbers
     */
    ResultDisplay currentIndexWaitingList = (result) -> result.forEach(x -> System.out.println("Index: " + x.getIndex() + " Course Code: " + x.getCourseCode() + " Waiting List: " + x.getWaitlist()));
    /**
     * Fliter courses by course code
     * @param fullModuleList is the course list
     * @param value is the course code to search for
     * @return is a list of course that match
     */
    CourseFiltering byModuleCode = (fullModuleList, value) -> {
        return fullModuleList.stream().filter(x -> value.equals(x.getCourseCode())).collect(Collectors.toList());
    };
    /**
     * Fliter courses by course index
     * @param fullModuleList is the course list
     * @param value is the course index to search for
     * @return is a list of course that match
     */
    CourseFiltering byIndex = (fullModuleList, value) -> {
        return fullModuleList.stream().filter(x -> Integer.parseInt(value) == x.getIndex()).collect(Collectors.toList());
    };


}
