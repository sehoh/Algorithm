import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        Set<Integer> summitSet = Arrays.stream(summits)
                .boxed()
                .collect(Collectors.toSet());

        Set<Integer> gateSet = Arrays.stream(gates)
                .boxed()
                .collect(Collectors.toSet());

        for (int[] path : paths) {
            int from = path[0];
            int to = path[1];
            int time = path[2];
            graph.putIfAbsent(from, new ArrayList<>());
            graph.get(from).add(new int[]{to, time});
            graph.putIfAbsent(to, new ArrayList<>());
            graph.get(to).add(new int[]{from, time});
        }

        List<Entry> results = new ArrayList<>();
        Queue<int[]> queue = new PriorityQueue<>((e1, e2) -> {
            return e1[1] - e2[1];
        });

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        for (int start : gates) {
            queue.add(new int[]{start, 0});
            dist[start] = 0;
        }

        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            
            if(dist[cur[0]] < cur[1]) continue;

            if (summitSet.contains(cur[0])) {
                results.add(new Entry(cur[0], cur[1]));
                continue;
            }

            for (int[] next : graph.get(cur[0])) {
                int nextMaxIntensity = Math.max(cur[1], next[1]);
                if (gateSet.contains(next[0])) continue;
                if (nextMaxIntensity < dist[next[0]]) {
                    dist[next[0]] = nextMaxIntensity;
                    queue.add(new int[]{next[0], nextMaxIntensity});
                }
            }
        }

        Collections.sort(results);

        return new int[]{results.get(0).summitNumber, results.get(0).maxIntensity};
    }


    private class Entry implements Comparable<Entry>{
        int summitNumber;
        int maxIntensity;

        public Entry(int summitNumber, int maxIntensity) {
            this.summitNumber = summitNumber;
            this.maxIntensity = maxIntensity;
        }

        @Override
        public int compareTo(Entry o) {
            if (this.maxIntensity == o.maxIntensity) {
                return this.summitNumber - o.summitNumber;
            }
            else return this.maxIntensity - o.maxIntensity;
        }
    }
}