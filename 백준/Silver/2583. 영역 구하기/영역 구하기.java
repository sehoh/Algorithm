import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    static int n, m, k, L;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] graph;
    static ArrayList<Integer> size = new ArrayList<>();
    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        m = kb.nextInt();
        n = kb.nextInt();
        k = kb.nextInt();
        graph = new int[n][m];
        for (int l = 0; l < k; l++) {
            int a = kb.nextInt();
            int b = kb.nextInt();
            int c = kb.nextInt();
            int d = kb.nextInt();
            for (int i = a; i < c; i++) {
                for (int j = b; j < d; j++) {
                    graph[i][j] = 1;
                }
            }
        }
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(graph[i][j] == 0) {
                    cnt ++;
                    L = 0;
                    graph[i][j] = 1;
                    T.DFS(i, j);
                    size.add(L);
                }
            }
        }
        Collections.sort(size);
        StringBuilder sb = new StringBuilder();
        sb.append(cnt);
        sb.append('\n');
        for(int x : size) sb.append(x + " ");
        System.out.print(sb);
    }

    private void DFS(int i, int j) {
        L ++;
        for (int d = 0; d < 4; d++) {
            int nx = i + dx[d];
            int ny = j + dy[d];
            if(nx >= 0 && nx <n && ny >=0 && ny <m && graph[nx][ny] == 0){
                graph[nx][ny] = 1;
                DFS(nx, ny);
            }
        }
    }
}
