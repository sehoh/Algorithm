import java.util.*;

class Solution {
    static char[][] graph;
    static int L, column, row;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public int[] solution(String[] maps) {
        column = maps[0].length();
        row = maps.length;
        graph = new char[row][column];
        
        for(int i=0; i<row; i++){
            for(int j=0; j<column; j++){
                graph[i][j] = maps[i].charAt(j);
            }
        }
        ArrayList<Integer> arr = new ArrayList<>();
        for(int i=0; i<row; i++){
            for(int j =0; j<column; j++){
                L = 0;
                if(graph[i][j] != 'X'){
                    L += graph[i][j] - 48;
                    graph[i][j] = 'X';
                    DFS(i, j);
                    arr.add(L);
                }
            }
        }
        int[] answer = new int[arr.size()];
        Collections.sort(arr);
        for(int i=0; i<arr.size(); i++){
            answer[i] = arr.get(i);
        }
        if(arr.size() == 0) return new int[]{-1};
        return answer;
    }
    
    private void DFS(int x, int y){
        for(int d =0; d<4; d++){
            int nx = x + dx[d];
            int ny = y + dy[d];
            if(nx >=0 && nx <row && ny >=0 && ny < column && graph[nx][ny] != 'X'){
                L += graph[nx][ny] - 48;
                graph[nx][ny] = 'X';
                DFS(nx, ny);
            }
        }
    }
}