import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        List<Integer> answer = new ArrayList<>();
        for (int[] command : commands) {
            int[] sub = Arrays.copyOfRange(array, command[0]-1, command[1]);
            Arrays.sort(sub);
            answer.add(sub[command[2]-1]);
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();

    }
}