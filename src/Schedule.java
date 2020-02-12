import java.io.*;
import java.net.URISyntaxException;
import java.util.LinkedList;

public class Schedule {

    public LinkedList<Appointment> appointments = new LinkedList<>();

    public Schedule() throws IOException, URISyntaxException {
        File file = new File(getClass().getResource("StretcherMap.csv").toURI());
        BufferedReader f = new BufferedReader(new FileReader(file));

        boolean endReached = false;
        f.readLine();
        int i = 1;
        while (true){
            String line[] = f.readLine().split(",");
            if (line.length < 4){
                break;
            }

            int intervalStart = Appointment.hoursToIntervals(Double.parseDouble(line[2])); // maybe change to interval input from CSV (process in Javascript)
            int intervalEnd = Appointment.hoursToIntervals(Double.parseDouble(line[3]));
            appointments.add(new Appointment(new Appointment.Period(intervalStart, intervalEnd), Integer.parseInt(line[0]), Integer.parseInt(line[1])));

        }

    }
}
