public class Stretcher {
    int stretcherID;
    int patientID;
    int currentRoomID;
    int x,y;
    int locale;

    public Stretcher(int stretcherID, int patientID, int currentRoomID, int locale) {
        this.stretcherID = stretcherID;
        this.patientID = patientID;
        this.currentRoomID = currentRoomID;
        this.locale = locale;
    }

    public Stretcher(int stretcherID, int patientID, int x, int y, int locale) {
        this.stretcherID = stretcherID;
        this.patientID = patientID;
        this.currentRoomID = -1;
        this.x = x;
        this.y = y;
        this.locale = locale;
    }
}
