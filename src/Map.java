import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class Map {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    boolean[][] visited;
    public Path pathway;
    public double total = 0;

    public int hospitalMap[][] = new int[51][24];
    public HashMap<Integer, ArrayList<Room>> roomTypeSorted;
    public HashMap<Integer, Room> roomIDMap;




    public Map() throws IOException, URISyntaxException {
        File file = new File(getClass().getResource("StretcherMap.csv").toURI());
        BufferedReader f = new BufferedReader(new FileReader(file));

        roomIDMap = new HashMap<>();
        roomTypeSorted = new HashMap<>();

        Random random = new Random();
        int g = 0;
        for (int i = 0; i < 24; i++) {
            String[] line = f.readLine().split(",");
            for (int j = 0; j < 51; j++) {

                hospitalMap[j][i] = Integer.parseInt(line[j]);

                if (hospitalMap[j][i] == 2){
                    int roomType = random.nextInt(5);
                    int roomID = g++;
                    Room room = new Room(roomID, j, i, roomType);
                    roomIDMap.put(roomID, room);

                    if (roomTypeSorted.containsKey(roomType)){
                        roomTypeSorted.get(roomType).add(room);
                    }
                    else {
                        roomTypeSorted.put(roomType, new ArrayList<Room>());
                        roomTypeSorted.get(roomType).add(room);
                    }
                }


            }
        }
    }

    public Node findHallway(Room room){

        int x = room.x;
        int y = room.y;

        int nx = -1;
        int ny = -1;
        for (int i = 3; i >= 0; i--) {
            nx = x + dx[i];
            ny = y + dy[i];
            if (hospitalMap[nx][ny] == 1){
                break;
            }
        }


        return new Node(nx, ny);

    }

    public Path pathway(int initialRoomID, int finalRoomID){
        visited = new boolean[hospitalMap.length][hospitalMap[0].length];
        pathway = new Path();




        Room initR = roomIDMap.get(initialRoomID);
        Room finalR = roomIDMap.get(finalRoomID);
        Node initRoomCoordinate = findHallway(initR);
        Node finalRoomCoordinate = findHallway(finalR);

        startR = new Node(initR.x, initR.y);
        endR = new Node(finalR.x, finalR.y);

        if (searchCheck(initRoomCoordinate, finalRoomCoordinate)){
            printPathwayCoordinates();
            displayPath();
        }else {
            System.out.println("Invalid Search");
        }



        return new Path();
    }

    public static class Path{
        int pathDuration;
        Stack<Node> pathway;

        public Path() {
            this.pathway = new Stack<>();
        }
    }

    public static class JunctionTree{
        LinkedList<JunctionPair> junctionPath;

    }

    public static class Vector{

        int xi, xf;
        int yi, yf;

        int direction;
        double length;

        public Vector(int xi, int xf, int yi, int yf) {
            this.xi = xi;
            this.xf = xf;
            this.yi = yi;
            this.yf = yf;

            direction = (int) Math.toDegrees(Math.atan2(yf-yi, xf-xi));
            length = Math.hypot(xf-xi, yf-yi);
        }
        public boolean add(int newxF, int newyF){
            if (direction != (int) Math.toDegrees(Math.atan2(newyF-yf, newxF-xf))){
                return false;
            }
            else {
                length += Math.hypot(newxF-xf, newyF -yf);
                xf = newxF;
                yf = newyF;

            }
            return true;
        }

        @Override
        public String toString() {
            return String.format("(%s, %s) --> (%s, %s); L: %s; Direction: %s", xi, yi, xf, yf, length, direction);
        }
    }
    public LinkedList<Vector> pathProcessor(Stack<Node> path) {
        LinkedList<Vector> instructions;
        instructions = new LinkedList<>();
        System.out.println(path);
        Node s = path.pop();
        Node s2 = path.pop();
        Vector u = new Vector(s.x, s2.x, s.y, s2.y);
        while (path.size() > 0){
            Node n = path.pop();
            if (!u.add(n.x, n.y)){
                instructions.add(u);
                u = new Vector(n.last.x, n.x, n.last.y, n.y);
            }
        }
        instructions.add(u);

        return instructions;
    }


    public static class JunctionPair{
        int x,y;
        int b1,b2;

        public JunctionPair(int x, int y, int b1, int b2) {
            this.x = x;
            this.y = y;
            this.b1 = b1;
            this.b2 = b2;
        }
    }


    public Node startP;
    public Node endP;

    public Node startR;
    public Node endR;

    public static void main(String[] args) throws IOException, URISyntaxException {
        Map map = new Map();

        map.pathway(3, 6);

        map.printPathwayCoordinates();
        map.displayPath();
        LinkedList<Vector> pathProcess = map.pathProcessor(map.pathway.pathway);

        for (Vector p: pathProcess) {
            System.out.println(p);
        }
    }
    public boolean searchCheck(Node start, Node end){
        startP = start;
        endP = end;
        if (isValid(start.x, start.y) && isValid(end.x, end.y)) {
            this.searchUnchecked(start, end);
            return true;
        }
        else {
            return false;
        }
    }


    public boolean[][] displayPath(){
        Stack<Node> nodeStack = (Stack<Node>) pathway.pathway.clone();
        boolean[][] path = new boolean[hospitalMap.length][hospitalMap[0].length];

        while (nodeStack.size() != 0){
            Node c = nodeStack.pop();
            path[c.x][c.y] =  true;
        }
        for (int i = 0; i < hospitalMap[0].length; i++) {
            for (int j = 0; j < hospitalMap.length; j++) {
                if (i == startP.y && j == startP.x){
                    System.out.print(ANSI_GREEN + hospitalMap[j][i] + ANSI_RESET + " ");
                }
                else if (i == endP.y && j == endP.x){
                    System.out.print(ANSI_CYAN + hospitalMap[j][i] + ANSI_RESET + " ");

                }
                else if (i == startR.y && j == startR.x){
                    System.out.print(ANSI_GREEN + hospitalMap[j][i] + ANSI_RESET + " ");
                }
                else if (i == endR.y && j == endR.x){
                    System.out.print(ANSI_CYAN + hospitalMap[j][i] + ANSI_RESET + " ");

                }
                else {
                    System.out.print((path[j][i] ? ANSI_BLUE + String.valueOf(hospitalMap[j][i]) + ANSI_RESET : hospitalMap[j][i]==1 ? ANSI_WHITE + String.valueOf(hospitalMap[j][i]) + ANSI_RESET : ANSI_RED +  String.valueOf(hospitalMap[j][i]) + ANSI_RESET) + " ");
                }
            }
            System.out.println();

        }
        return path;
    }
    public void printPathwayCoordinates(){
        Stack<Node> nodeStack = (Stack<Node>) pathway.pathway.clone();
        System.out.println("# of Steps: " + (pathway.pathway.size() - 1));
        double sum = 0;

        while (nodeStack.size() != 0){
            if (nodeStack.size() == 1) {
                sum = nodeStack.peek().k;
            }
            System.out.print(nodeStack.pop() + (nodeStack.size() != 0 ? ", " : ""));

        }
        System.out.println();
        System.out.println(sum);


    }

    public Path getPathway() {
        return pathway;
    }

    public void searchUnchecked(Node current, Node destination){
        pathway.pathway.clear();

        LinkedList<Node> queue = new LinkedList<>();

        visited[current.x][current.y] = true;
        current.setLast(new Node(-1, -1));

        queue.add(current);

        while (queue.size() != 0 && !found){
            current = queue.poll();
            if (current.x == destination.x && current.y == destination.y){
                found = true;
                destination = current;
                continue;
            }
            for (int i = 0; i < dx.length; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                //System.out.println(new Node(nx,ny).toString());

                double f = /*f_x(i)*/ 1 + current.k;
                double k = distance(new Node(nx, ny), destination) + f;

                if (isValid(nx, ny) && !isVisited(nx, ny)){
                    visited[nx][ny] = true;

                    add(queue, k, destination, new Node(nx, ny, f, current));
                }
            }

        }

        if (found) {

            Node path = destination;
            total = destination.k;
            while (path.x != -1 && path.y != -1) {
                pathway.pathway.add(path);
                path = path.last;
            }
        }
    }
    public static double f_x(int i){
        switch (i){
            case 0:
            case 1:
                return 10/3;
            case 2:
            case 3:
                return 10/7;
            case 4:
            case 5:
            case 6:
            case 7:
                return Math.sqrt(2) / (1.55984/4);
        }
        return 0;
    }
    public void add(LinkedList<Node> queue, double k, Node destination, Node adj){
        if (queue.size()==0){
            queue.add(adj);
            return;
        }
        int i = 0;
        while (i < queue.size() && distance(queue.get(i), destination) < k){
            i++;
        }
        if(i == queue.size()){
            queue.add(adj);
        }else {
            queue.add(i, adj);
        }
    }


    static int[] dx = new int[]{-1,1,0,0};
    static int[] dy = new int[]{0,0,-1,1};
    boolean found = false;

    public boolean isValid(int x, int y){
        return x >= 0 && x < hospitalMap.length && y >= 0 && y < hospitalMap[x].length && hospitalMap[x][y]==1;

    }
    public boolean isValid2(int x, int y){
        for (int i = x-9; i < x+9; i++) {
            for (int j = y-9; j < y+9; j++) {
                if(!(i >= 0 && i < hospitalMap.length && j >= 0 && j < hospitalMap[i].length && hospitalMap[i][j]!=0)){
                    return false;
                }
            }

        }
        return true;
    }

    public boolean isVisited(int x, int y) {
        return x >= 0 && x < hospitalMap.length && y >= 0 && y < hospitalMap[x].length && visited[x][y];
    }

    public double distance (Node c, Node d){
        return (Math.sqrt(  Math.pow(d.x - c.x, 2) + Math.pow(d.y - c.y, 2) ));

    }

    static class Node{
        int x,y;
        Node last;

        double k;

        public Node(int x, int y, double k, Node last) {
            this.x = x;
            this.y = y;
            this.k = k;
            this.last = last;
        }

        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }

        public Node(int x, int y, Node last){
            this.x = x;
            this.y = y;
            this.last = last;
        }

        public void setLast(Node last) {
            this.last = last;
        }

        @Override
        public String toString() {
            return "(" + this.x + ", " + this.y + ")";
        }
    }



}
