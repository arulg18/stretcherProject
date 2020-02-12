import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class Map {

    public int hospitalMap[][] = new int[51][24];
    public HashMap<Integer, ArrayList<Room>> roomTypeSorted;


    public Map() throws IOException, URISyntaxException {
        File file = new File(getClass().getResource("StretcherMap.csv").toURI());
        BufferedReader f = new BufferedReader(new FileReader(file));

        Random random = new Random();
        int g = 0;
        for (int i = 0; i < 24; i++) {
            String[] line = f.readLine().split(",");
            for (int j = 0; j < 51; j++) {

                hospitalMap[j][i] = Integer.parseInt(line[j]);

                if (hospitalMap[j][i] == 2){
                    int roomType = random.nextInt(5);
                    if (roomTypeSorted.containsKey(roomType)){
                        roomTypeSorted.get(roomType).add(new Room(g++, j, i, roomType));
                    }
                    else {
                        roomTypeSorted.put(roomType, new ArrayList<Room>());
                        roomTypeSorted.get(roomType).add(new Room(g++, j, i, roomType));
                    }
                }


            }
        }
    }

    public Path pathway(int initialRoomID, int finalRoomID){ // incomplete
        return new Path();
    }

    public static class Path{
        int pathDuration;
        Stack<Pair> pathway;


    }
    public static class Pair{
        int x,y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
