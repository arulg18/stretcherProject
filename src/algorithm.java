import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class algorithm {
int hmapsize;
public ArrayList<Room> finalroomset;

    public algorithm (HashMap<String, ArrayList<Room>> roommap, Appointment sampleappointment) { //takes hashmap with room type key and room array lists as objects
        hmapsize = roommap.size();

        for(int i = 0; i < roommap.get(sampleappointment.type).size(); i++){  // loops through arraylist that matches requested room type to get each room
            int testvar = 0;
            if(!roommap.get(sampleappointment.type).get(i).schedule[sampleappointment.appointmenttime]){ // checks if open for start time of appointment of schedule array for each room in list
                for(int j =0; j <  sampleappointment.appointmentlength; j++){// checks if all times from start to end of appointment are open in selected room
                    if(roommap.get(sampleappointment.type).get(i).schedule[sampleappointment.appointmenttime + j]){
                        testvar = 1;
                        break;
                    }
                }

            }
            if(testvar == 1) {
                finalroomset.add(roommap.get(sampleappointment.type).get(i)); //adds room to cut down array if it has passed both type and open tests
            }
        }



    }




}
