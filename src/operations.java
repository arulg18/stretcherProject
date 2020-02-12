import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.LinkedList;

public class operations {
    public static void main(String[] args) throws IOException, URISyntaxException {

        operations operation = new operations();

    }

    public operations() throws IOException, URISyntaxException {
        HashMap<Integer, Patient> patientHashMap = new HashMap<>(); // incomplete
        Map map = new Map();
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



        }
    }
}
