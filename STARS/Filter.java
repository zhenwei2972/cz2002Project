import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Date;

public class Filter {
    //Functional interface is not other than a abstract method, which we are able polymorph into different methods
    //This method only works on Java 8 onwards 
    @FunctionalInterface
    interface DataFiltering {
        public List<Course> Invoke(List<Course> list, String value);
    }
    @FunctionalInterface
    interface ResultDisplay {
        public void Display(List<Course> list);
    } 
    // index, coursecode, coursename, AU, day, starttime, endtime, classtype, vacancy
    ResultDisplay toDisplayResult = (result) -> result.forEach(x -> System.out.println("Index: " + x.index + " Course Code: " + x.coursecode + " Day of Lesson: " + x.day + " Vacancy: " + x.vacancy));

    DataFiltering byModuleCode = (fullModuleList, value) -> {
        return fullModuleList.stream().filter(x -> value.equals(x.getCourseCode())).collect(Collectors.toList());
    };

    DataFiltering byIndex = (fullModuleList, value) -> {
        return fullModuleList.stream().filter(x -> Integer.parseInt(value) == x.getIndex()).collect(Collectors.toList());
    };

    DataFiltering byDays = (fullModuleList, value) -> {
        return fullModuleList.stream().filter(x -> value.equals(x.getDay())).collect(Collectors.toList());
    };


}
