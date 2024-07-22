import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    private static boolean isValid(int nx, int ny, int[][] board) {
        return (ny >= 0 && ny < n && nx >= 0 && nx < n && board[ny][nx] != 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        n = Integer.parseInt(line[0]);
        m = Integer.parseInt(line[1]);
        int fuel = Integer.parseInt(line[2]);

        int[][] board = new int[n][n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        line = br.readLine().split(" ");
        Coord start = new Coord(Integer.parseInt(line[1]) - 1, Integer.parseInt(line[0]) - 1);
        Taxi taxi = new Taxi(start, fuel);
        Map<Coord, Coord> passengers = new HashMap<>();

        for (int i = 0; i < m; i++) {
            line = br.readLine().split(" ");
            int a = Integer.parseInt(line[0]) - 1;
            int b = Integer.parseInt(line[1]) - 1;
            Coord src = new Coord(b, a);

            int c = Integer.parseInt(line[2]) - 1;
            int d = Integer.parseInt(line[3]) - 1;
            Coord dst = new Coord(d, c);
            passengers.put(src, dst);
        }
        boolean isPossible = true;
        int cnt = 0;
        while (!passengers.isEmpty() && taxi.fuel >= 0) {
            // Entry : 가장 가까운 승객의 좌표 정보 + 가는데 소모되는 연료량
            Entry entrySrc = choose(taxi, passengers, board);

            if(entrySrc == null){
                isPossible = false;
                break;
            }
            // 승객까지 이동
            taxi.fuel -= entrySrc.distance;
            taxi.coord = entrySrc.coord;
            
            // 목적지 까지 이동 
            Entry entryDst = moveToDst(taxi, passengers, board);
            if (entryDst == null) {
                isPossible = false;
                break;
            }
            taxi.fuel -= entryDst.distance;
            if(taxi.fuel < 0){
                isPossible = false;
                break;
            }

            taxi.fuel += 2 * entryDst.distance;
            taxi.coord = entryDst.coord;
            passengers.remove(entrySrc.coord);

            cnt++;
        }

        if (cnt < m || !isPossible) {
            System.out.println(-1);
        } else {
            System.out.println(taxi.fuel);
        }
    }

    private static Entry moveToDst(Taxi taxi, Map<Coord, Coord> passengers, int[][] board) {
        Coord src = new Coord(taxi.coord.x, taxi.coord.y);
        boolean[][] visited = new boolean[n][n];
        visited[src.y][src.x] = true;
        Queue<Coord> queue = new ArrayDeque<>();
        queue.add(src);
        int L = 0;
        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                Coord curr = queue.poll();

                // curr이 도착지점이라면
                if (passengers.get(src).equals(curr)) {
                    passengers.remove(src);
                    return new Entry(curr, L);
                }
                for (int d = 0; d < 4; d++) {
                    int nx = curr.x + dx[d];
                    int ny = curr.y + dy[d];

                    if (isValid(nx, ny, board) && !visited[ny][nx]) {
                        visited[ny][nx] = true;
                        queue.add(new Coord(nx, ny));
                    }
                }
            }
            L++;
        }
        return null;
    }


    private static Entry choose(Taxi taxi, Map<Coord, Coord> passengers, int[][] board) {
        Coord src = new Coord(taxi.coord.x, taxi.coord.y);
        boolean[][] visited = new boolean[n][n];
        Queue<Coord> queue = new ArrayDeque<>();
        queue.add(src);
        PriorityQueue<Coord> pQ = new PriorityQueue<>(
                Comparator.comparingInt((Coord coord) -> coord.y)
                        .thenComparingInt(coord -> coord.x)
        );

        int L_max = Integer.MAX_VALUE;
        int L = 0;
        while (!queue.isEmpty() && L <= L_max) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                Coord curr = queue.poll();

                if (passengers.containsKey(curr)) {
                    L_max = L;
                    pQ.add(curr);
                }

                for (int d = 0; d < 4; d++) {
                    int nx = curr.x + dx[d];
                    int ny = curr.y + dy[d];

                    if (isValid(nx, ny, board) && !visited[ny][nx]) {
                        visited[ny][nx] = true;
                        queue.add(new Coord(nx, ny));
                    }
                }
            }
            L++;
        }
        if (pQ.isEmpty()) {
            return null;
        } else return new Entry(pQ.poll(), L_max);
    }

    private static class Entry {
        Coord coord;
        int distance;

        public Entry(Coord coord, int distance) {
            this.coord = coord;
            this.distance = distance;
        }
    }
    
    private static class Taxi{
        Coord coord;
        int fuel;

        public Taxi(Coord coord, int fuel) {
            this.coord = coord;
            this.fuel = fuel;
        }
    }


    private static class Coord {
        int x;
        int y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Coord) {
                Coord c = (Coord) o;
                return this.y == c.y && this.x == c.x;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return (x + "," + y).hashCode();
        }
    }
}
