import java.util.*;

class Solution {
    static int[][] board;
    static int[] dx = {1, 0, -1};
    static int[] dy = {0, 1, -1};
    public int[] solution(int n) {
        int x = 0, y = 0, cnt =0, increasingNumber = 1, d =0;
        board = new int[n][n];

        // n*n 정사각형을 만들고, 삼각형에 해당하지 않는 부분은 -1
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                board[i][j] = -1;
            }
        }
        board[0][0] = 1;

        while (increasingNumber < (n * (n+1) / 2)) {
            cnt = 0;

            int nx = x + dx[d];
            int ny = y + dy[d];
            // if조건에 해당되면 방향회전
            if(nx < 0 || nx >=n || ny <0 || ny >=n || board[nx][ny] != 0){
                d = (d + 1) % 3;
                continue;
            }
            board[nx][ny] = ++increasingNumber;
            x = nx;
            y = ny;


        }
        System.out.println();
        int[] answer = new int[increasingNumber];
        List<Integer> arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != -1) {
                    arr.add(board[i][j]);
                }
            }
        }
        System.out.println();
        for (int i = 0; i < arr.size(); i++) {
            answer[i] = arr.get(i);
        }

        return answer;
    }
}