import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int sum = 0;
        Arrays.sort(jobs, (o1, o2) -> {
            return o1[0] - o2[0];
        });
        int n = jobs.length;
        Queue<Request> pQ = new PriorityQueue<>();


        for (int t = 0, i = 0, cnt = 0; ; ) {
            if (pQ.isEmpty() && i < n) {
                if (t < jobs[i][0]) {
                    // t 업데이트
                    t = jobs[i][0];
                }
            }
            // 현재 시간에 가능한 작업들을 우선순위 큐에 넣는다
            while (i < n && t >= jobs[i][0]) {
                pQ.add(new Request(jobs[i][0], jobs[i][1]));
                i++;
            }

            // 꺼내기
            if (!pQ.isEmpty()) {
                Request cur = pQ.poll();
                t += cur.required_t;
                sum += (t - cur.request_t);
            }

            cnt++;
            if(cnt == n) {
                break;
            }
        }

        return (int) Math.floor((double) sum / n);
    }

    private class Request implements Comparable<Request>{
        int request_t, required_t;

        public Request(int request_t, int required_t) {
            this.request_t = request_t;
            this.required_t = required_t;
        }

        @Override
        public int compareTo(Request o) {
            return this.required_t - o.required_t;
        }
    }
}