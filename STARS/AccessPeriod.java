import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

/**
 * Represents the access period object, unique to the year variable, each year
 * will have a specific start date and end date which serves as the allowed
 * access period , for login into stars system.
 * This is based on the concept of white-listing, only access periods which are defined here are allowed, if undefined, access is forbidden.
 * @author Team Stars
 * @version 1.0
 * @since 2020
 */
public class AccessPeriod { 
    /**
     * Year is the key variable which has to be unique
     */
    String year;
    /**
     * Start Date of the access period for a particular Year 
     */
    Date startDate;
    /**
     * End Date of the access period for a particular Year 
     */
    Date endDate;
    /**
     * Formatter to define the format of our date and time objects. 
     */
    SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy HH:mm");

    /**
     * This is the constructor to construct AccessPeriod objects from the text file
     * @param startDate is this AccessPeriod's start date 
     * @param endDate is this AccessPeriod's end date
     * @param Year is this AccessPeriod's Year ( unique )
     * as it is constructing from text files, all paramters are first taken in as string then parsed into date objects
     */
    public AccessPeriod(String startDate, String endDate, String Year) {
        try {
            Date defaultStart = formatter.parse(startDate);
            Date defaultEnd = formatter.parse(endDate);
            this.year = Year;
            this.startDate = defaultStart;
            this.endDate = defaultEnd;

        } catch (ParseException e) {
            System.out.println("Incorrect Entry");
            e.printStackTrace();
        }
    }
    /**
     * This is a simplified constructor to build a default access period object with 
     * pre initialized values for a given year
     * @param year as the student's year
     */
    public AccessPeriod(String year) {
        try {
            String tempStart = "Jan 1, 2020 12:10";
            Date defaultStart = formatter.parse(tempStart);
            String tempEnd = "Jan 1, 2021 12:10";
            Date defaultEnd = formatter.parse(tempEnd);
            this.year = year;
            this.startDate = defaultStart;
            this.endDate = defaultEnd;

        } catch (ParseException e) {
            System.out.println("Incorrect Entry");
            e.printStackTrace();
        }
    }
    /**
     * Performs a check on whether the current system date time is within the range 
     * of the access period start date and end dates. 
     * returns a boolean value for login checking purposes
     * @return if accessPeriod is within range currently 
     */
    public boolean AccessPeriodCheck() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy HH:mm");
        Date now = new Date();
        String strDate = sdf.format(now);
        boolean validDateTime = false;
        try {
            Date currentDateTimeAfterFormatting = formatter.parse(strDate);
            // System.out.println(currentDateTimeAfterFormatting);
            if (currentDateTimeAfterFormatting.before(endDate) && currentDateTimeAfterFormatting.after(startDate)) {
                validDateTime = true;
            }
        } catch (ParseException e) {
            System.out.println("Incorrect Entry");
            e.printStackTrace();
        }

        return validDateTime;
    }
    /**
     * Get this AccessPeriod's Year
     * @return String year
     */
    public String getYear() {
        return this.year;
    }
    /**
     * Get this AccessPeriod's start date
     * @return Date start date
     */
    public Date getStartDate() {
        return startDate;
    }
    /**
     * Get this AccessPeriod's end date
     * @return Date end date
     */
    public Date getEndDate() {
        return endDate;
    }
    /**
     * Set this AccessPeriod's start date
     * @param startDate a Date start date
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    /**
     * Set this AccessPeriod's end date
     * @param endDate a Date end date
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

}
