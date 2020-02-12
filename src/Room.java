public class Room {

    String[] roomTypes = {"Room", "Radiology Room", "Toxicology Room", "Pulmonary Function Lab", "Recovery Room"};// find room types
    public int roomID;
    public int x,y;
    public int[] schedule = new int[288]; //Segment Tree
    public int type;

    public Room(int roomID, int x, int y, int type) {
        this.roomID = roomID;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public String getRoomName(){
        return roomTypes[roomID];
    }
}
