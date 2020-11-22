import java.util.stream.Collectors;
import java.util.List;

public class CourseManager {
    //Functional interface is not other than a abstract method, which we are able polymorph into different methods
    //This method only works on Java 8 onwards 
    @FunctionalInterface
    interface CourseFiltering {
        public List<Course> Invoke(List<Course> list, String value);
    }
    @FunctionalInterface
    interface ResultDisplay {
        public void Display(List<Course> list);
    } 
    // index, coursecode, coursename, AU, day, starttime, endtime, classtype, vacancy
    ResultDisplay toDisplayResult = (result) -> result.forEach(x -> System.out.println("Index: " + x.getIndex() + " Course Code: " + x.getCourseCode() + " Vacancy: " + x.getVacancy() + " Waiting List: " + x.getWaitlist()));

    CourseFiltering byModuleCode = (fullModuleList, value) -> {
        return fullModuleList.stream().filter(x -> value.equals(x.getCourseCode())).collect(Collectors.toList());
    };

    CourseFiltering byIndex = (fullModuleList, value) -> {
        return fullModuleList.stream().filter(x -> Integer.parseInt(value) == x.getIndex()).collect(Collectors.toList());
    };

}
