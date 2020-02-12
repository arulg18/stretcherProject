public class Room {

    public String roomid;
    public int x,y;
    public boolean[] schedule = new boolean[288];
    public String roomtype;

    public Room(String roomid, int x, int y, String roomtype) {
        this.roomid = roomid;
        this.x = x;
        this.y = y;
        this.roomtype = roomtype;
    }
}
