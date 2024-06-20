import java.util.*;

class Solution {
    public String[] solution(String[] strings, int n) {
        return Arrays.stream(strings).sorted((s1, s2) -> {
            char first = s1.charAt(n);
            char second = s2.charAt(n);
            if (first == second) {
                return s1.compareTo(s2);
            } else {
                return first - second;
            }
        }).toArray(String[]::new);
    }
}