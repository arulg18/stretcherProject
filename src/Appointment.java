import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Appointment {
    public Period appointment;
    public int personID;
    public int type;

    public Appointment(Period appointment, int personID, int type) {
        this.appointment = appointment;
        this.personID = personID;
        this.type = type;
    }



    public static class Period {
        int startTime, endTime;

        public Period(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public String toString() {
            return "" + intervalToHours2(startTime) + " - " + intervalToHours2(endTime);
        }
    }


    public static int hoursToIntervals(double hours) {
        return (int) Math.round(hours * 12);
    }
    public static double IntervalToHours(double intervals) {
        return (intervals / 12);
    }

    public static String intervalToHours2(double interval){
        interval/=12;
        if (interval < 0){
            interval = 24+interval;
        }
        long timeInMilliSeconds = (long) Math.round(interval * 3600 * 1000);
        Date date = new Date(timeInMilliSeconds);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        return sdf.format(date);
    }

}
