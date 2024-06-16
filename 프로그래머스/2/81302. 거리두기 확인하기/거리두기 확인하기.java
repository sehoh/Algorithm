import java.util.*;

class Solution {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static List<char[][]> list;
    
    public int[] solution(String[][] places) {
        int[] result = new int[5];

        list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new char[5][5]);
        }

        for (int i = 0; i < 5; i++) {
            String[] place = places[i];
            for (int j = 0; j < 5; j++) {
                String row = place[j];
                for (int k = 0; k < 5; k++) {
                    list.get(i)[j][k] = row.charAt(k);
                }
            }
        }

        for (int i =0; i< 5; i++) {
            if(!check(i)){
                result[i] = 0;
            } else {
                result[i] = 1;
            }
        }


        return result;
    }
    private boolean check(int i) {
        char[][] place = list.get(i);
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                if (place[x][y] == 'P') {
                    if (!isDistancing(i, x, y)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
public boolean isDistancing(int i, int x, int y) {
        char[][] place = list.get(i);
        // 맨해튼 거리 1에 대해 탐색
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx >= 0 && nx < 5 && ny >= 0 && ny < 5 && place[nx][ny] != 'X') {
                if(place[nx][ny] == 'P') return false;

                // 맨해튼 거리 2에 대해 탐색
                for (int d2 = 0; d2 < 4; d2++) {
                    // 역방향이면 continue
                    if(d2 == ((d+2) % 4)) continue;
                    int n2x = nx + dx[d2];
                    int n2y = ny + dy[d2];
                    if (n2x >= 0 && n2x < 5 && n2y >= 0 && n2y < 5 && place[n2x][n2y] != 'X') {
                        if(place[n2x][n2y] == 'P') return false;
                    }
                }

            }
        }

        return true;
    }
    
}