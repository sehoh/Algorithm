import java.util.*;

class Solution {
    
    static private int max = Integer.MIN_VALUE;
    private boolean isExhausted(int[][] dungeons, int k) {
        return Arrays.stream(dungeons)
                .filter(dungeon -> dungeon[0] <= k)
                .count() == 0;
    }

    private void explore(int k, int[][] dungeons, int L) {
        if(isExhausted(dungeons, k)) {
            max = Math.max(max, L);
            return;
        }
        for (int[] dungeon : dungeons) {
            if (k >= dungeon[0]) {
                int buffer = dungeon[0];
                dungeon[0] = 5001; // 방문했음
                explore(k - dungeon[1], dungeons, L + 1);
                dungeon[0] = buffer;
            }
        }
    }

    public int solution(int k, int[][] dungeons) {
        explore(k, dungeons, 0);
        return max;
    }
}