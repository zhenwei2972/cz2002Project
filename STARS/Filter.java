import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Date;

public class Filter {
    //Functional interface is not other than a abstract method, which we are able polymorph into different method
    //This method only works on Java 8 onwards 
    @FunctionalInterface
    interface FilteredData {
        public List<Module> Invoke(ArrayList<Module> list, String value);
    }
    @FunctionalInterface
    interface ResultDisplay {
        public void Display(List<Module> list);
    } 
    // index, coursecode, coursename, AU, day, starttime, endtime, classtype, vacancy
    ResultDisplay toDisplayResult = (result) -> result.forEach(x -> System.out.println("Index: " + x.index + " Course Code: " + x.coursecode + " Day of Lesson: " + x.day + " Vacancy: " + x.vacancy));

    FilteredData byModuleCode = (fullModuleList, value) -> {
        return fullModuleList.stream().filter(x -> value.equals(x.getCourseCode())).collect(Collectors.toList());
    };

    FilteredData byIndex = (fullModuleList, value) -> {
        return fullModuleList.stream().filter(x -> Integer.parseInt(value) == x.getIndex()).collect(Collectors.toList());
    };

    FilteredData byDays = (fullModuleList, value) -> {
        return fullModuleList.stream().filter(x -> value.equals(x.getDay())).collect(Collectors.toList());
    };

    FilteredData BeforeEndTime = (fullModuleList, value) -> {
        return fullModuleList.stream().filter(x -> value.equals(x.starttime)).collect(Collectors.toList());
    };

}
