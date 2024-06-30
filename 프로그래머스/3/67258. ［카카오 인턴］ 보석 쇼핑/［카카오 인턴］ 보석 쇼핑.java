import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int start = 0, end = gems.length - 1;
        Set<String> gemSet = new HashSet<>(List.of(gems));

        int s = 0, e = s;
        Map<String, Integer> includes = new HashMap<>();
        includes.put(gems[0], 1);

        while (s < gems.length) {
            // 모든 보석을 하나 이상 포함
            if (includes.keySet().size() == gemSet.size()) {
                // 가장 짧은 구간
                if (e - s < end - start) {
                    start = s;
                    end = e;
                }
                // s 전진
                includes.put(gems[s], includes.get(gems[s]) - 1);
                if (includes.get(gems[s]) == 0) {
                    includes.remove(gems[s]);
                }
                s++;

            } else if (e < gems.length - 1) { // 모든 보석 포함시키기 위해 e 전진
                e++;
                includes.put(gems[e], includes.getOrDefault(gems[e], 0) + 1);

            } else  break;
        }

        return new int[]{start + 1, end + 1};
    }
}