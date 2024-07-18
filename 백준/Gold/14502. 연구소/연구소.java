import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int min = Integer.MAX_VALUE;

    private static boolean isInRange(int nx, int ny) {
        return (ny >= 0 && ny < n && nx >= 0 && nx < m);
    }

    private static boolean isZero(int nx, int ny, int[][] board) {
        return board[ny][nx] == 0;
    }

    private static void BFS(List<Point> comb, Queue<Point> poisons, int[][] board) {
        int[] dy = {-1, 0, 1, 0};
        int[] dx = {0, 1, 0, -1};

        // 벽을 세운다
        for (Point p : comb) {
            board[p.y][p.x] = 1;
        }

        while (!poisons.isEmpty()) {
            Point cv = poisons.poll();
            for (int d = 0; d < 4; d++) {
                int nx = cv.x + dx[d];
                int ny = cv.y + dy[d];

                if (isInRange(nx, ny) && isZero(nx, ny, board)) {
                    poisons.add(new Point(nx, ny));
                    board[ny][nx] = 2;
                }
            }
        }
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(board[i][j] == 2) cnt++;
            }
        }
        // 바이러스 전염 영역의 최솟값을 update
        min = Math.min(min, cnt);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        List<List<Point>> combs = new ArrayList<>();    // 벽3개 조합
        Queue<Point> poisons = new LinkedList<>();  // 바이러스 (2)
        List<Point> empty = new ArrayList<>();  // 빈공간 (0)
        List<Point> wall = new ArrayList<>();   // 벽 (1)

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int[][] board = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] == 2) poisons.add(new Point(j, i));
                else if(board[i][j] == 1) wall.add(new Point(j, i));
                else if(board[i][j] == 0) empty.add(new Point(j, i));
            }
        }
        // 조합 생성 - 3개를 고름
        genComb(0, new ArrayList<>(), empty, combs);

        // 각각 3개를 고른 조합의 수에 대해 BFS적용
        for (List<Point> comb : combs) {
            int[][] copied = new int[n][m];
            for (int i = 0; i < n; i++) {
                copied[i] = Arrays.copyOf(board[i], board[0].length);
            }
            BFS(comb, new LinkedList<>(poisons), copied);
        }

        System.out.println(m * n - min - wall.size() - 3);
    }

    private static void genComb(int start, List<Point> curr, List<Point> empty, List<List<Point>> result) {
        if (curr.size() == 3) {
            result.add(new ArrayList<>(curr));
            return;
        }
        for (int i = start; i < empty.size(); i++) {
            curr.add(empty.get(i));
            genComb(i+1, curr, empty, result);
            curr.remove(curr.size() - 1);
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
