import java.util.*;

class Solution {
    
    private boolean isInRange(int nx, int ny) {
        return (ny >= 0 && ny < 5 && nx >= 0 && nx < 5);
    }

    private boolean isWall(int nx, int ny, char[][] board) {
        return board[ny][nx] == 'X';
    }

    public int[] solution(String[][] places) {
        int n = places.length;
        int[] answer = new int[n];

        for (int i = 0; i < n; i++) {
            char[][] board = genBoard(places[i]);
            answer[i] = check(board);
        }
        return answer;
    }

    private int check(char[][] board) {
        // 한 번이라도 P가 나온다면 0 return하고 종료
        final int[] dy = {-1, 0, 1, 0};
        final int[] dx = {0, 1, 0, -1};
        boolean[][] visited = new boolean[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[i][j] == 'P') {
                    // BFS 실행
                    Point start = new Point(j, i);
                    visited[i][j] = true;

                    Queue<Point> queue = new ArrayDeque<>();
                    queue.add(start);

                    int L = 0;

                    while (!queue.isEmpty() && L < 2) {
                        int len = queue.size();

                        for (int k = 0; k < len; k++) {
                            Point curr = queue.poll();

                            for (int d = 0; d < 4; d++) {
                                int nx = curr.x + dx[d];
                                int ny = curr.y + dy[d];

                                // 범위, 벽검사
                                if (isInRange(nx, ny) && !isWall(nx, ny, board)) {
                                    // 방문했는지 검사
                                    if (!visited[ny][nx]) {
                                        // (nx, ny)가 P 라면 거리두기를 지키지 않는것임!
                                        if(board[ny][nx] == 'P') return 0;
                                        visited[ny][nx] = true;
                                        queue.add(new Point(nx, ny));
                                    }
                                }
                            }
                        }
                        L++;
                    }
                }
            }
        }

        return 1;
    }

    private char[][] genBoard(String[] place) {
        char[][] board = new char[5][5];

        for (int i = 0; i < 5; i++) {
            String str = place[i];
            for (int j = 0; j < 5; j++) {
                board[i][j] = str.charAt(j);
            }
        }

        return board;
    }

    private class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}