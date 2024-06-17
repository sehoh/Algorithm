import java.util.*;

class Solution {
    List<int[]> process = new ArrayList<>();
    
    private void hanoi(int n, int from, int to) {
        if(n == 1){
            process.add(new int[]{from, to});
            return;
        }
        int empty = 6 - from - to;

        hanoi(n-1, from, empty);
        hanoi(1, from, to);
        hanoi(n-1, empty, to);
    }
    
    public int[][] solution(int n) {
        hanoi(n, 1, 3);
        return process.toArray(new int[0][]);
    }
}