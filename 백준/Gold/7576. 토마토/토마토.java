import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
    static int n, m;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] board;
    static int[][] ch;
    static Queue<Point> Q = new LinkedList<>();

    private int BFS() {
        int ret =0;
        while(!Q.isEmpty()){
            int len = Q.size();
            for (int i = 0; i < len; i++) {
                Point tmp = Q.poll();
                for (int d = 0; d < 4; d++) {
                    int nx = tmp.x + dx[d];
                    int ny = tmp.y + dy[d];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && board[nx][ny] == 0 && ch[nx][ny] == 0) {
                        ch[nx][ny] = 1;
                        Q.offer(new Point(nx, ny));
                        board[nx][ny] = 1;
                    }
                }
            }
            ret ++;
        }
        ret --;
        return ret;
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        n = kb.nextInt();
        m = kb.nextInt();
        board = new int[m][n];
        ch = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = kb.nextInt();
                if(board[i][j] == 1) {
                    ch[i][j] = 1;
                    Q.offer(new Point(i, j));
                }
            }
        }
        int cnt = Q.size();

        int answer = T.BFS();
        boolean flag = true;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(board[i][j] == 0) flag = false;
            }
        }

        if(flag){
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if(board[i][j] == 1) cnt--;
                }
            }
            if(cnt == 0) answer = 0;

            System.out.println(answer);
        } else System.out.println(-1);
    }

}
class Point{
    public int x,y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
