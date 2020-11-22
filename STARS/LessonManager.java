import java.util.List;
import java.util.stream.Collectors;

public class LessonManager {
    @FunctionalInterface
    interface LessonFiltering{
        public List<Lesson> Invoke(String value);
    }
 
    LessonFiltering byIndex = (stringIndex) -> {
        int value = Integer.parseInt(stringIndex);
        Database db = new Database();
        List<Lesson> fullLessonList = db.LessonListDatabase();
        return fullLessonList.stream().filter(x -> value == x.getIndex()).collect(Collectors.toList());
    };
}
