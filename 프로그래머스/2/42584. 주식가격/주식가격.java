import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class Solution {
    public int[] solution(int[] prices) {
        int n = prices.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int[] answer = new int[n];
        for (int day = 0; day < n; day++) {
            // 가격이 떨어지지 않은 기간
            // 가격이 계속 올라가다가 떨어지기 까지 걸리는 시간
            while (!stack.isEmpty() && prices[stack.peek()] > prices[day]) {
                int prev_day = stack.pop();
                answer[prev_day] = day - prev_day;
            }
            stack.push(day);
        }
        while (!stack.isEmpty()) {
            int pop_element = stack.pop();
            answer[pop_element] = n - 1 - pop_element;
        }
        return answer;
    }
}