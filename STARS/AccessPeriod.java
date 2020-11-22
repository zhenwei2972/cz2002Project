import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
public class AccessPeriod {
    
    String year;
    Date startDate;
    Date endDate;
    // this is a white list method
    SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy HH:mm");
    public AccessPeriod(String year) throws ParseException {
      
        String tempStart = "Jan 1, 2020 12:10";
        Date defaultStart = formatter.parse(tempStart);
        String tempEnd = "Jan 1, 2021 12:10";
        Date defaultEnd = formatter.parse(tempEnd);
        this.year = year;
        startDate =defaultStart;
        endDate =defaultEnd;
    }
    boolean AccessPeriodCheck(Date checkDate) {
        return !(checkDate.before(startDate) || checkDate.after(endDate));
     }
    public Date getStartDate(){
        return startDate;
    }
    public Date GetEndDate(){
        return endDate;
    }
    public void setStartDate(Date startDate){ System.out.println(formatter.format(startDate));}
    public void setEndDate(Date endDate){ System.out.println(formatter.format(endDate));}

}
