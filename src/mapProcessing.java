import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Random;

public class mapProcessing {

    public int field[][] = new int[51][24];

    String[] roomtypes = {"Room", ""};// find room types

    public ArrayList<Room> rooms = new ArrayList<>();

    public mapProcessing() throws IOException, URISyntaxException {
        File file = new File(getClass().getResource("StretcherMap.csv").toURI());
        BufferedReader f = new BufferedReader(new FileReader(file));

        Random random = new Random();
        int g = 0;
        for (int i = 0; i < 24; i++) {
            String[] line = f.readLine().split(",");
            for (int j = 0; j < 51; j++) {

                field[j][i] = Integer.parseInt(line[j]);

                if (field[j][i] == 2){
                    rooms.add(new Room(String.valueOf(g++), j, i, random.nextInt(10)));
                }

            }
        }
    }
}
