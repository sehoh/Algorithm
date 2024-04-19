import java.util.*;

class Solution {
    static int row, column, x, y;
    static char[][] graph;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static HashMap<String, Integer> dir = new HashMap<String, Integer>(){{
        put("N", 0);
        put("E", 1);
        put("S", 2);
        put("W", 3);
    }};
    public int[] solution(String[] park, String[] routes) {
        column = park[0].length();
        row = park.length;
        graph = new char[row][column];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                char k = park[i].charAt(j);
                graph[i][j] = k;
                if(k == 'S'){
                    x = i;
                    y = j;
                }
            }
        }
        
        for(String s : routes){
            String op = s.split(" ")[0];
            int n = Integer.parseInt(s.split(" ")[1]);
            if(isPossible(op, n)){
                int d = dir.get(op);
                for (int i = 0; i < n; i++) {
                    x = x + dx[d];
                    y = y + dy[d];
                }
            }
        }

        int[] answer = new int[]{x, y};
        return answer;
    }
    
    private boolean isPossible(String op, int n){
        int tmpX = x, tmpY = y;
        int d = dir.get(op);
        for(int i =0; i<n; i++){
            tmpX = tmpX + dx[d];
            tmpY = tmpY + dy[d];
            if(tmpX<0 || tmpX >=row || tmpY <0 || tmpY >=column || graph[tmpX][tmpY] == 'X'){
                return false;
            }
        }
        return true;
    }
}