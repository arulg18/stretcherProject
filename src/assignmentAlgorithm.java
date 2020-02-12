import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class assignmentAlgorithm {

    public static LinkedList<Room> assignRooms(HashMap<Integer, ArrayList<Room>> roomMap, Appointment appointment) { //takes hashmap with room type key and room array lists as objects
        ArrayList<Room> rooms = roomMap.get(appointment.type);
        LinkedList<Room> eligibleRooms = new LinkedList<>();

        for (Room room : rooms) { // not the fastest implementation: segment tree
            Appointment.Period check = appointment.appointment;

            int sum = 0;
            for (int i = check.startTime; i <= check.endTime; i++) {
                sum += room.schedule[i];
            }
            if (sum == 0){
                eligibleRooms.add(room);
            }
        }

        return eligibleRooms;
    }




}
