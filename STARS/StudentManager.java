import java.util.List;
import java.util.stream.Collectors;

public class StudentManager {
    interface StudentFiltering{
        public List<Student> Invoke(List<Student> list, String value);
    }

    StudentFiltering byMatricNo = (fullStudentList, matricNo) ->{
        return fullStudentList.stream().filter(x -> matricNo.equals(x.getMatid())).collect(Collectors.toList());
    };
    StudentFiltering byUsername = (fullStudentList, username) ->{
        return fullStudentList.stream().filter(x -> username.equals(x.getUsername())).collect(Collectors.toList());
    };
}
