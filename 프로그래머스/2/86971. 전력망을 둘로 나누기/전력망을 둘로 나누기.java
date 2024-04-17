import java.util.*;

class Solution {
    static int min = Integer.MAX_VALUE;
    public int solution(int n, int[][] wires) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for(int[] edge : wires){
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        System.out.println();
        for(int[] edge : wires){
            int[] ch = new int[n + 1];
            int tmp = BFS(edge[0], edge[1], graph, ch);
            min = Math.min(min, Math.abs((n - tmp) - tmp));
        }

        return min;
    }
    private int BFS(int start, int end, ArrayList<ArrayList<Integer>> graph, int[] ch){ch[start] = 1;
        ch[end] = 1;
        Queue<Integer> Q = new LinkedList<>();
        Q.offer(start);
        int L = 0;

        while (!Q.isEmpty()) {
            int v = Q.poll();
            for(int nv : graph.get(v)){
                if(ch[nv] == 0) {
                    ch[nv] = 1;
                    Q.offer(nv);
                }
            }
            L++;
        }
        return L;
    }
}