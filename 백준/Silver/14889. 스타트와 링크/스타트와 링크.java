import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static int n, min = Integer.MAX_VALUE;
    static int[] combi, ch;
    static int[][] graph;
    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        n = kb.nextInt();
        combi = new int[n/2];
        ch = new int[n];
        graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int k = kb.nextInt();
                graph[i][j] = k;
            }
        }
        T.DFS(0, 0);
        System.out.println(min);
    }

    private void DFS(int L, int s) {
        if(L == (n/2)){
            int startSum = 0;
            int linkSum = 0;
            ArrayList<Integer> complement = new ArrayList<>();
            for(int i=0; i<n; i++) if(ch[i] == 0) complement.add(i);

            for(int i : combi){
                for(int j : combi){
                    if(i != j) startSum += graph[i][j];
                }
            }
            for(int i : complement){
                for(int j : complement){
                    if(i != j) linkSum += graph[i][j];
                }
            }
            int dif = Math.abs(startSum - linkSum);
            min = Math.min(min, dif);
        }
        else {
            for (int i = s; i <n; i++) {
                combi[L] = i;
                ch[i] = 1;
                DFS(L+1, i+1);
                ch[i] = 0;
            }
        }
    }
}
