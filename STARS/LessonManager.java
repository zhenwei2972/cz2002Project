import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Comparator;

public class LessonManager {
    @FunctionalInterface
    interface LessonFiltering{
        public List<Lesson> Invoke(String value);
    }

    @FunctionalInterface
    interface TimeTableFiltering{
        public List<Lesson> Invoke(List<Integer> indexlist);
    }
    Database db = new Database();
    List<Lesson> fullLessonList = db.LessonListDatabase();
 
    LessonFiltering byIndex = (stringIndex) -> {
        int value = Integer.parseInt(stringIndex);
        return fullLessonList.stream().filter(x -> value == x.getIndex()).collect(Collectors.toList());
    };

    TimeTableFiltering getMondayClasses = (indexList) ->{
        List<Lesson> result = new ArrayList<Lesson>();
        for(Integer i: indexList){
            List<Lesson> mondayClass = fullLessonList.stream().filter(x -> i == x.getIndex() && x.getDay().equals("Monday")).collect(Collectors.toList());
            result.addAll(mondayClass);
        }
        List<Lesson> sortedTime = result.stream().sorted(Comparator.comparingInt(Lesson::getStartTime)).collect(Collectors.toList());
        return sortedTime;
    };

    TimeTableFiltering getTuesdayClasses = (indexList) ->{
        List<Lesson> result = new ArrayList<Lesson>();
        for(Integer i: indexList){
            List<Lesson> mondayClass = fullLessonList.stream().filter(x -> i == x.getIndex() && x.getDay().equals("Tuesday")).collect(Collectors.toList());
            result.addAll(mondayClass);
        }
        List<Lesson> sortedTime = result.stream().sorted(Comparator.comparingInt(Lesson::getStartTime)).collect(Collectors.toList());
        return sortedTime;
    };

    TimeTableFiltering getWednesdayClasses = (indexList) ->{
        List<Lesson> result = new ArrayList<Lesson>();
        for(Integer i: indexList){
            List<Lesson> mondayClass = fullLessonList.stream().filter(x -> i == x.getIndex() && x.getDay().equals("Wednesday")).collect(Collectors.toList());
            result.addAll(mondayClass);
        }
        List<Lesson> sortedTime = result.stream().sorted(Comparator.comparingInt(Lesson::getStartTime)).collect(Collectors.toList());
        return sortedTime;
    };

    TimeTableFiltering getThursdayClasses = (indexList) ->{
        List<Lesson> result = new ArrayList<Lesson>();
        for(Integer i: indexList){
            List<Lesson> mondayClass = fullLessonList.stream().filter(x -> i == x.getIndex() && x.getDay().equals("Thursday")).collect(Collectors.toList());
            result.addAll(mondayClass);
        }
        List<Lesson> sortedTime = result.stream().sorted(Comparator.comparingInt(Lesson::getStartTime)).collect(Collectors.toList());
        return sortedTime;
    };
    
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
