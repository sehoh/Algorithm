import java.util.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int[] fare : fares) {
            graph.putIfAbsent(fare[0], new ArrayList<>());
            graph.putIfAbsent(fare[1], new ArrayList<>());
            graph.get(fare[0]).add(new Edge(fare[1], fare[2]));
            graph.get(fare[1]).add(new Edge(fare[0], fare[2]));
        }
        
        Queue<Edge> pqShare = new PriorityQueue<>();
        // 2차원
        int[][] distArray = new int[3][n + 1];
        for (int[] d : distArray) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }
        // (s, a, b) 3개의 노드에서 각각의 다익스트라 알고리즘 수행
        final int[] start = {s, a, b};
        Queue<Edge> pq = new PriorityQueue<>();
        for (int j = 0; j < 3; j++) {
            int[] d = distArray[j];
            pq.add(new Edge(start[j], 0));
            d[start[j]] = 0;
            // 최종적으로 다익스트라 알고리즘 3번 수행
            while (!pq.isEmpty()) {
                Edge cur = pq.poll();
                
                for (Edge edge : graph.get(cur.node)) {
                    if (d[edge.node] > cur.fare + edge.fare) {
                        d[edge.node] = cur.fare + edge.fare;
                        pq.add(new Edge(edge.node, cur.fare + edge.fare));
                    }
                }
            }
        }
        // 모든 노드를 순회하며 cost(s->x) + cost(a->x) + cost(b->x)의 최소비용을 반환
        int minFare = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            int sum =0;
            for (int[] d : distArray) {
                sum += d[i];
            }
            minFare = Math.min(minFare, sum);
        }
        
        return minFare;
    }


    private class Edge implements Comparable<Edge> {
        int node;
        int fare;

        public Edge(int node, int fare) {
            this.node = node;
            this.fare = fare;
        }

        @Override
        public int compareTo(Edge o) {
            return this.fare - o.fare;
        }
    }
}