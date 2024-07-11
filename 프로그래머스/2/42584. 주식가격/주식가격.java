import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        Deque<Integer> stack = new ArrayDeque<>();
        int[] answer = new int[prices.length];

        for (int i = 0; i < prices.length; i++) {
            while (!stack.isEmpty() && prices[stack.peek()] > prices[i]) {
                int prev_day = stack.poll();
                answer[prev_day] = i - prev_day;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int prev_day = stack.poll();
            answer[prev_day] = prices.length - 1 - prev_day;
        }

        return answer;
    }
}