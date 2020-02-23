public class Patient {
    public int personID;
    public int currentRoomID;
    public String patientName;
    public int stretcherID;
    public int priorityLevel;

    public Patient(int personID, int currentRoomID, String patientName, int stretcherID, int priorityLevel) {
        this.personID = personID;
        this.currentRoomID = currentRoomID;
        this.patientName = patientName;
        this.stretcherID = stretcherID;
        this.priorityLevel = priorityLevel;
    }



}
