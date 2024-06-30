class Solution {
    private int countForward(int v, boolean[][] graph, boolean[] isVisited) {
        int count = 1;
        // v가 이긴 u에 대한 전이
        for (int u = 0; u < graph.length; u++) {
            if(!graph[u][v] || isVisited[u]) continue;
            isVisited[u] = true;
            count += countForward(u, graph, isVisited);
        }

        return count;
    }

    private int countBackward(int u, boolean[][] graph, boolean[] isVisited) {
        int count = 1;
        for (int v = 0; v < graph[u].length; v++) {
            if(!graph[u][v] || isVisited[v]) continue;
            isVisited[v] = true;
            count += countBackward(v, graph, isVisited);
        }

        return count;
    }

    public int solution(int n, int[][] results) {
        boolean[][] graph = new boolean[n][n];
        int answer = 0;
        for (int[] p : results) {
            int v = p[0] - 1;
            int u = p[1] - 1;
            graph[v][u] = true;
        }
        for (int i = 0; i < n; i++) {
            int win = countForward(i, graph, new boolean[n]) - 1;
            int lose = countBackward(i, graph, new boolean[n]) - 1;
            if(win + lose + 1 == n) answer ++;
        }
        return answer;
    }
}