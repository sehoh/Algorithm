import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static char[][] graph;
    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true){
            String str = br.readLine();
            if(str.equals("end")) break;
            graph = new char[3][3];
            int k = 0, xCnt = 0, oCnt = 0;
            for(int i =0; i < 3; i++){
                for(int j =0; j<3; j++){
                    graph[i][j] = str.charAt(k++);
                    if(graph[i][j] == 'X') xCnt++;
                    else if(graph[i][j] == 'O') oCnt++;
                }
            }

            if(xCnt == oCnt + 1){
                if(xCnt + oCnt == 9 && !T.isBingo('O')){
                    sb.append("valid").append("\n");
                }
                else if (!T.isBingo('O') && T.isBingo('X')) {
                    sb.append("valid").append("\n");
                }
                else {
                    sb.append("invalid").append("\n");
                }
            }
            else if(xCnt == oCnt){
                if (!T.isBingo('X') && T.isBingo('O')) {
                    sb.append("valid").append("\n");
                }
                else {
                    sb.append("invalid").append("\n");
                }
            }
            else {
                sb.append("invalid").append("\n");
            }
        }
        System.out.println(sb);

    }
    private boolean isBingo(char c){
        for (int i = 0; i < 3; i++) {
            if(graph[i][0] == c && graph[i][1] == c && graph[i][2] == c) return true;
        }

        for (int j = 0; j < 3; j++) {
            if(graph[0][j] == c && graph[1][j] == c && graph[2][j] == c) return true;

        }

        if(graph[0][0] == c && graph[1][1] == c && graph[2][2] == c) return true;
        if(graph[0][2] == c && graph[1][1] == c && graph[2][0] == c) return true;

        return false;
    }

}
