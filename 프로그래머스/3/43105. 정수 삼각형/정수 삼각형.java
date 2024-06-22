class Solution {
    private int[][] memo = new int[501][501];

    private int max(int x, int y, int[][] triangle) {
        if(memo[x][y] > 0) return memo[x][y];
        if(x == triangle.length) return 0;
        return memo[x][y] = triangle[x][y] + Math.max(
                max(x + 1, y, triangle),
                max(x + 1, y + 1, triangle)
        );

    }

    public int solution(int[][] triangle) {
        return max(0, 0, triangle);
    }
}