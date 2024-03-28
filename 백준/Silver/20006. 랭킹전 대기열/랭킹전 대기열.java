import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Main {
    static int p, m;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        p = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        ArrayList<Player> playerList = new ArrayList<>();
        Rooms rooms = new Rooms();

        for(int i=0; i<p; i++){
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            String str = st.nextToken();
            playerList.add(new Player(l, str));
        }

        for(Player p : playerList){
            if(rooms.isAvailable(p) == null) {
                rooms.addRoom(new Room(p));
            }
            else{
                Room room = rooms.isAvailable(p);
                room.addPlayer(p);
            }
        }

        for(Room r : rooms.getRooms()) r.printPlayers();
    }

    private static class Player implements Comparable<Player> {
        public int level;
        public String nickName;
        Player(int level, String nickName){
            this.level = level;
            this.nickName = nickName;
        }
        @Override
        public int compareTo(Player ob){
            return nickName.compareTo(ob.nickName);
        }
    }
    private static class Room {
        private int lvMin, lvMax;
        private boolean isFull = false;
        private ArrayList<Player> players;
        public Room(Player firstPlayer){
            this.players = new ArrayList<>();
            this.lvMin = firstPlayer.level - 10;
            this.lvMax = firstPlayer.level + 10;
            addPlayer(firstPlayer);
        }
        public void printPlayers(){
            Collections.sort(this.players);
            if(isFull) {
                System.out.println("Started!");
            }
            else System.out.println("Waiting!");

            for(Player p : players) {
                System.out.println(p.level + " "+ p.nickName);
            }
        }
        public ArrayList<Player> getPlayers(){
            return this.players;
        }
        public void addPlayer(Player ob){
            if(isAddable(ob)){
                this.players.add(ob);
            }
            if(this.players.size() == m) {
                isFull = true;
            }
        }
        public boolean isAddable(Player ob){
            if(!isFull && ob.level >= this.lvMin && ob.level <= this.lvMax) return true;
            return false;
        }
    }
    private static class Rooms {
        private ArrayList<Room> rooms;
        public Rooms(){
            this.rooms = new ArrayList<>();
        }
        public void addRoom(Room room){
            rooms.add(room);
        }
        public ArrayList<Room> getRooms(){return rooms;}
        public Room isAvailable(Player p){
            for(Room room : this.rooms){
                if(room.isAddable(p)) return room;
            }
            return null;
        }
    }
}
