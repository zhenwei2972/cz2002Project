import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
public class AccessPeriod {
    
    String year;
    int[] intArray; 
    Date startDate;
    Date endDate;
    // this is a white list method, setting default startdate/enddate in the constructor
    SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy HH:mm");
    public AccessPeriod(String startDate, String endDate, String Year)
    {
        try {
            //temporarily dont use the startdate/endate yet
            String tempStart = "Jan 1, 2020 12:10";
           System.out.println(startDate);
            Date defaultStart = formatter.parse(tempStart);
            String tempEnd = "Jan 1, 2021 12:10";
            Date defaultEnd = formatter.parse(tempEnd);
            this.year = Year;
            this.startDate =defaultStart;
            this.endDate =defaultEnd;

        } catch (ParseException e) {
            System.out.println("Incorrect Entry");
            e.printStackTrace();
        }
    }
    public AccessPeriod(String year) {
        try {
            String tempStart = "Jan 1, 2020 12:10";
            Date defaultStart = formatter.parse(tempStart);
            String tempEnd = "Jan 1, 2021 12:10";
            Date defaultEnd = formatter.parse(tempEnd);
            this.year = year;
            this.startDate =defaultStart;
            this.endDate =defaultEnd;

        } catch (ParseException e) {
            System.out.println("Incorrect Entry");
            e.printStackTrace();
        }
    }
    public boolean AccessPeriodCheck() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy HH:mm");
        Date now = new Date();
        String strDate = sdf.format(now);
        boolean validDateTime =false;
        try{
            Date currentDateTimeAfterFormatting = formatter.parse(strDate);
            System.out.println(currentDateTimeAfterFormatting);
            if(currentDateTimeAfterFormatting.before(endDate) && currentDateTimeAfterFormatting.after(startDate))
            {
                validDateTime=true;
            }
        } catch (ParseException e) {
            System.out.println("Incorrect Entry");
            e.printStackTrace();
        }
        
        return validDateTime;
    }
    public String getYear(){
        return this.year;
    }
    public Date getStartDate(){
        return startDate;
    }
    public Date getEndDate(){
        return endDate;
    }
    public void setStartDate(Date startDate){ 
        this.startDate=startDate;
        System.out.println(formatter.format(startDate));
    }
    public void setEndDate(Date endDate){
        this.endDate = endDate;
        System.out.println(formatter.format(endDate));
    }

}
