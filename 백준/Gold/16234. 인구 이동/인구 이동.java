import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, L, R;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] board, visited;
    static int level = 0;
    static List<Node> list;

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        board = new int[n][n];
        visited = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            boolean moved = false; // 매일마다 인구이동을 했는지 체크
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (visited[i][j] == 0) { // 방문을 안한 곳만 방문
                        T.BFS(i, j);
                        if(list.size() > 1){
                            moved = true;
                        }
                    }
                }
            }
            if(!moved) break; // 인구이동을 안했다면 프로그램 종료
            level ++;
            visited = new int[n][n];
        }

        System.out.println(level);
    }

    public void BFS(int x, int y) {
        Queue<Node> Q = new LinkedList<>();
        list = new ArrayList<>();

        Q.offer(new Node(x, y, board[x][y]));
        list.add(new Node(x, y, board[x][y]));
        int totalUnionPopulation = board[x][y];
        visited[x][y] = 1;

        while (!Q.isEmpty()) {
            Node tmp = Q.poll();
            for (int d = 0; d < 4; d++) {
                int nx = tmp.x + dx[d];
                int ny = tmp.y + dy[d];
                if(nx >=0 && nx < n && ny >=0 && ny <n && visited[nx][ny] == 0){ 
                    int diff = Math.abs(board[nx][ny] - tmp.population);
                    if(diff >= L && diff <= R){ // 인구수 차이가 L이상 R이하인 국가 순회
                        Q.offer(new Node(nx, ny, board[nx][ny]));
                        list.add(new Node(nx, ny, board[nx][ny]));
                        totalUnionPopulation += board[nx][ny];
                        visited[nx][ny] = 1;
                    }
                }
            }
        }

        for (Node node : list) { // 인구이동
            int avg = totalUnionPopulation / list.size();
            board[node.x][node.y] = avg;
        }
    }

    private static class Node {
        int x, y;
        int population;

        public Node(int x, int y, int population) {
            this.x = x;
            this.y = y;
            this.population = population;
        }
    }
}
