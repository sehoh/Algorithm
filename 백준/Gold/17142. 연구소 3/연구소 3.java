import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] line = reader.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        int[][] board = new int[N][N];

        // 최소시간
        // 모든 빈칸에 퍼뜨리지 못할경우 -1 return
        List<Point> poisons = new ArrayList<>();
        int empty = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                switch (board[i][j]) {
                    case 2: poisons.add(new Point(j, i)); break;
                    case 0: empty++;
                }
            }
        }

        // empty가 0이라면 바로 0 출력하고 종료
        if (empty == 0) {
            System.out.println(0);
            
        } else {
            int min = Integer.MAX_VALUE;
            List<List<Point>> combs = new ArrayList<>();
            genComb(0, new ArrayList<>(), poisons, combs);

            for (List<Point> comb : combs) {
                // bfs의 return값이 -1이 아닐때만 answer에 update
                int[][] copy = copyBoard(board);
                int result = bfs(comb, empty, copy);

                if (result != -1) {
                    min = Math.min(min, result);
                }
            }
            // bfs의 결과가 항상 -1이라면 모든 빈칸에 도달하지 못한것이므로 -1 출력
            if(min == Integer.MAX_VALUE) System.out.println(-1);
            else System.out.println(min);
        }
    }
    private static int bfs(List<Point> active, int empty, int[][] board) {
        final int[] dy = {-1, 0, 1, 0};
        final int[] dx = {0, 1, 0, -1};

        int result = -1, infected = 0;
        boolean[][] visited = new boolean[N][N];
        List<Entry> entry = new ArrayList<>();
        for (Point p : active) {
            visited[p.y][p.x] = true;
            entry.add(new Entry(p, 0));
        }
        // infected 값이 빈칸의 크기와 같아진다면 result를 Entry의 time으로 바꿔낀다
        Queue<Entry> queue = new ArrayDeque<>(entry);

        while (!queue.isEmpty()) {
            int len = queue.size();

            for (int i = 0; i < len; i++) {
                Entry curr = queue.poll();

                // 종료 조건 : infected == 빈칸의 크기
//                if (infected == empty) {
//                    return curr.t;
//                }
                for (int d = 0; d < 4; d++) {
                    int nx = curr.p.x + dx[d];
                    int ny = curr.p.y + dy[d];
                    if (isInRange(nx, ny) && !isWall(nx, ny, board) && !visited[ny][nx]) {
                        if(board[ny][nx] == 0) infected++;

                        visited[ny][nx] = true;
                        queue.add(new Entry(new Point(nx, ny), curr.t + 1));

                        // 종료 조건
                        if (infected == empty) {
                            return curr.t + 1;
                        }
                    }
                }
            }
        }
        return result;
    }

    private static boolean isInRange(int nx, int ny) {
        return (ny >= 0 && ny < N && nx >= 0 && nx < N);
    }

    private static boolean isWall(int nx, int ny, int[][] board) {
        return board[ny][nx] == 1;
    }

    private static int[][] copyBoard(int[][] board) {
        int[][] result = new int[N][N];
        for (int i = 0; i < N; i++) {
            result[i] = Arrays.copyOf(board[i], board[i].length);
        }
        return result;
    }


    private static void genComb(int start, List<Point> curr, List<Point> poisons, List<List<Point>> result){
        if (curr.size() == M) {
            result.add(new ArrayList<>(curr));
            return;
        }
        for (int i = start; i < poisons.size(); i++) {
            curr.add(poisons.get(i));
            genComb(i + 1, curr, poisons, result);
            curr.remove(curr.size() - 1);
        }
    }

    private static class Entry {
        Point p;
        int t;

        public Entry(Point p, int t) {
            this.p = p;
            this.t = t;
        }
    }

    private static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
