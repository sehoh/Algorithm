import java.util.*;
import java.util.stream.IntStream;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        // 투 포인터
        int len = queue1.length;

        long sum1 = Arrays.stream(queue1).sum();
        long sum2 = Arrays.stream(queue2).sum();
        int p1 = 0, p2 = len;

        int[] merged = IntStream.concat(IntStream.of(queue1), IntStream.of(queue2))
                .toArray();
        int cnt =0;
        int answer = -1;

        while (cnt < 4 * len) {
            if (sum1 == sum2) {
                answer = cnt++;
                break;
            }
            if (sum1 > sum2) {
                int rid = merged[p1++ % (2 * len)];
                sum1 -= rid;
                sum2 += rid;
            } else if (sum1 < sum2) {
                int rid = merged[p2++ % (2 * len)];
                sum1 += rid;
                sum2 -= rid;
            }
            cnt++;
        }

        return answer;
    }
}