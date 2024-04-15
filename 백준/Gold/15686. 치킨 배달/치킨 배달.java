import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static int n, m, min = Integer.MAX_VALUE;
    static int[] combi;
    static int[][] graph;
    static ArrayList<Point> houses = new ArrayList<>();
    static ArrayList<Point> chickens = new ArrayList<>();
    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        n = kb.nextInt();
        m = kb.nextInt();
        combi = new int[m];

        graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int k = kb.nextInt();
                graph[i][j] = k;
                if(k == 2){
                    chickens.add(new Point(i, j));
                } else if(k == 1){
                    houses.add(new Point(i, j));
                }
            }
        }

        T.DFS(0,0);
        System.out.println(min);
    }
    private void DFS(int L, int s) {
        if(L == m){
            int sum = 0;
            for (Point h : houses) {
                int tmp = Integer.MAX_VALUE;
                for (int i : combi) {
                    tmp = Math.min(tmp, Math.abs(h.px - chickens.get(i).px) + Math.abs(h.py - chickens.get(i).py));
                }
                sum += tmp;
            }
            min = Math.min(min, sum);
        }
        else{
            for (int i = s; i < chickens.size(); i++) {
                combi[L] = i;
                DFS(L + 1, i + 1);
            }
        }
    }

    private static class Point{
        public int px, py;

        public Point(int px, int py) {
            this.px = px;
            this.py = py;
        }
    }
}
