import java.util.*;

class Solution {
    private final int[] student1 = {1, 2, 3, 4, 5};
    private final int[] student2 = {2, 1, 2, 3, 2, 4, 2, 5};
    private final int[] student3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
    
    
    public int[] solution(int[] answers) {
        int n = answers.length;
        int[] scores = new int[3];

        for (int i = 0; i < n; i++) {
            if(answers[i] == student1[i % student1.length]) scores[0] ++;
            if(answers[i] == student2[i % student2.length]) scores[1] ++;
            if(answers[i] == student3[i % student3.length]) scores[2] ++;
        }

        int max = Arrays.stream(scores).max().getAsInt();
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < scores.length; i++) {
            if(scores[i] == max) result.add(i + 1); // i+1로 1번 학생, 2번 학생, 3번 학생
        }

        return result.stream().mapToInt(i -> i).toArray();
    }
}