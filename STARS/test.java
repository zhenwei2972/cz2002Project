import java.io.File;
import java.util.List;

public class test {
    

    public static void main(String args[]) {
        Database ctrl = new Database();
        List<AccessPeriod> fullAccessPeriodList = ctrl.AccessPeriodDatabase();

        fullAccessPeriodList.forEach(x -> System.out.println(x.getYear()));

    }
}
