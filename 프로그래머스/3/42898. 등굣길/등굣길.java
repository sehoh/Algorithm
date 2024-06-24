import java.util.*;

class Solution {
    private final int[][] memo = new int[101][101];

    private int count(int x, int y, int w, int h, boolean[][] isPuddle) {
        // 범위밖이니?
        if(x > w || y > h) return 0;
        // 웅덩이니?
        if(isPuddle[y][x]) return 0;
        // 이미 구한 값이니?
        if(memo[x][y]  != -1) return memo[x][y];
        // 학교에 도달했니?
        if(x == w && y == h) return 1;

        int total = count(x + 1, y, w, h, isPuddle) +
                count(x, y + 1, w, h, isPuddle);

        return memo[x][y] = total % 1000000007;
    }
    
    public int solution(int m, int n, int[][] puddles) {
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        boolean[][] isPuddle = new boolean[n + 1][m + 1];
        for (int[] p : puddles) {
            isPuddle[p[1]][p[0]] = true;
        }

        return count(1, 1, m, n, isPuddle);
    }
}