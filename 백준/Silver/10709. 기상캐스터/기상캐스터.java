import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static char[][] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int I = Integer.parseInt(st.nextToken());
        int J = Integer.parseInt(st.nextToken());
        graph = new char[I][J];
        for (int i = 0; i < I; i ++){
            String line = br.readLine();
            for (int j = 0; j < J; j++) {
                graph[i][j] = line.charAt(j);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < I; i++) {
            int idx = 0;
            boolean flag = false;
            for (int j = 0; j < J; j++) {
                if(graph[i][j] == 'c') {
                    flag = true;
                    sb.append(0).append(' ');
                    idx = 0;
                }
                else if(flag) sb.append(++idx).append(' ');
                else if(!flag) sb.append(-1).append(' ');
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
}
