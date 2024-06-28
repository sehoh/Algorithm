import java.util.*;

class Solution {
    static ArrayList<ArrayList<Integer>> graph;

    private void BFS(int v, boolean[] isVisited) {
        Queue<Integer> Q = new LinkedList<>();
        Q.offer(v);
        while (!Q.isEmpty()) {
            int tmp = Q.poll();
            isVisited[tmp] = true;
            for (int u : graph.get(tmp)) {
                if(isVisited[u]) continue;
                Q.offer(u);
            }
        }
    }

    public int solution(int n, int[][] computers) {
        graph = new ArrayList<>();
        boolean[] isVisited = new boolean[n];

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && computers[i][j] == 1) {
                    graph.get(i).add(j);
                }
            }
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            if(isVisited[i]) continue;
            BFS(i, isVisited);
            count ++;
        }

        return count;
    }
}