import java.util.*;

class Solution {
    private static class Point {
        private final int x, y;
        private final int step;

        public Point(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }

    
    private static final int[] dy = {-1, 0, 1, 0};
    private static final int[] dx = {0, 1, 0, -1};
    
    
    public int solution(int[][] maps) {
        int m = maps.length;
        int n = maps[0].length;

        boolean[][] isVisited = new boolean[m][n];

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0, 0, 1));
        isVisited[0][0] = true;

        while (!queue.isEmpty()) {
            Point now = queue.poll();
            // 범위 검사
            if(now.x == n-1 && now.y == m-1) return now.step;
            for (int d = 0; d < 4; d++) {
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m &&
                        maps[ny][nx] == 1 && !isVisited[ny][nx]) {
                    isVisited[ny][nx] = true;
                    queue.add(new Point(nx, ny, now.step + 1));
                }
            }
        }
        
        return -1;
    }
}