import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        Set<String> set = Arrays.stream(report).collect(Collectors.toSet());

        // key , value => 신고당한 사람, 신고자
        Map<String, List<String>> reportHash = new HashMap<>();
        List<String> banned = new ArrayList<>();
        for (String str : set) {
            String[] tokens = str.split(" ");
            reportHash.putIfAbsent(tokens[1], new ArrayList<>());
            reportHash.get(tokens[1]).add(tokens[0]);
        }

        for (String key : reportHash.keySet()) {
            if (reportHash.get(key).size() >= k) {
                banned.add(key);
            }
        }
        Map<String, Integer> count = new HashMap<>();
        // banned for문을 돌면서 reportHash의 value값 들에 대한 Hash를 추가해서 count

        for (String banned_id : banned) {
            for (String reporter : reportHash.get(banned_id)) {
                count.put(reporter, count.getOrDefault(reporter, 0) + 1);
            }
        }

        int[] answer = new int[id_list.length];
        for (int i = 0; i < id_list.length; i++) {
            if (count.containsKey(id_list[i])) {
                answer[i] = count.get(id_list[i]);
            } else answer[i] = 0;
        }
        return answer;
    }
}