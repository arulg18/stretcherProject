import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class operations {
    public static void main(String[] args) throws IOException, URISyntaxException {

        operations operation = new operations();

    }

    public static class returnPackage{
        int patientInitialRoomID, stretcherInitialRoomID;
        int patientDestinationRoomID;

        int patientID;
        int stretcherID;

        Appointment appointment;

        Map.Path patientPath;
        Map.Path stretcherPath;

        public returnPackage(int patientInitialRoomID, int stretcherInitialRoomID, int patientDestinationRoomID, int patientID, int stretcherID, Appointment appointment, Map.Path patientPath, Map.Path stretcherPath) {
            this.patientInitialRoomID = patientInitialRoomID;
            this.stretcherInitialRoomID = stretcherInitialRoomID;
            this.patientDestinationRoomID = patientDestinationRoomID;
            this.patientID = patientID;
            this.stretcherID = stretcherID;
            this.appointment = appointment;
            this.patientPath = patientPath;
            this.stretcherPath = stretcherPath;
        }
    }

    public operations() throws IOException, URISyntaxException {
        Map map = new Map();
        HashMap<Integer, Patient> patientHashMap = generatePatients(map.roomTypeSorted.get(0));
        Schedule schedule = new Schedule();
        for (Appointment appointment: schedule.appointments) {
            LinkedList<Room> eligibleRooms = assignmentAlgorithm.assignRooms(map.roomTypeSorted, appointment);
            int currentRoomID = patientHashMap.get(appointment.personID).currentRoomID;

            int min = Integer.MAX_VALUE;
            int finalRoomID = -1;


            Map.Path shortestPath = null;
            for(int i = 0; i < eligibleRooms.size(); i++){
                Map.Path path = map.pathway(currentRoomID, eligibleRooms.get(i).roomID);
                if (path.pathDuration < min){
                    shortestPath = path;
                     min = path.pathDuration;
                    finalRoomID = eligibleRooms.get(i).roomID;
                }

            }




            // Find the stretchers in the area
            // Find the closest Stretcher
            // Set stretcher to the room
            //  next line following






        }
    }


    // Finish name randomization and patient information
    public HashMap<Integer, Patient> generatePatients(ArrayList<Room> rooms) throws URISyntaxException, FileNotFoundException {
        /*File file = new File(getClass().getResource("babynames.csv").toURI());
        File file2 = new File(getClass().getResource("surnames.csv").toURI());
        BufferedReader f = new BufferedReader(new FileReader(file));
        BufferedReader f2 = new BufferedReader(new FileReader(file2));

        */

        Random random = new Random();
        HashMap<Integer, Patient> patients = new HashMap<>();
        for (int i = 0; i < 20; i++) {
            patients.put(i, new Patient(i, rooms.get(random.nextInt(rooms.size())).roomID, "john" + i, -1, 0));
        }
        return patients;


    }


    public HashMap<Integer, Stretcher> generateStretchers(ArrayList<Stretcher> stretchers, int roomSize) throws URISyntaxException, FileNotFoundException {


        Random random = new Random();
        HashMap<Integer, Stretcher> stretcherMap = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            stretcherMap.put(i, new Stretcher(i, -1, random.nextInt(roomSize), 5));
        }
        return stretcherMap;


    }

}
