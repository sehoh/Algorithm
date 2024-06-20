import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length; j++) {
                if(i == j) continue;
                list.add(numbers[i] + numbers[j]);
            }
        }
        return list.stream().mapToInt(Integer::intValue).distinct().sorted().toArray();
    }
}