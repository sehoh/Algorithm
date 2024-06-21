import java.util.*;

class Solution {
    public int solution(String[] user_id, String[] banned_id) {
        
        // 불량 사용자별 가능한 응모자 아이디
        String[][] bans = Arrays.stream(banned_id)
                .map(banned -> banned.replace('*', '.'))
                .map(banned -> Arrays.stream(user_id)
                        .filter(id -> id.matches(banned))
                        .toArray(String[]::new))
                .toArray(String[][]::new);

        Set<Set<String>> banSet = new HashSet<>();
        count(0, new HashSet<>(), bans, banSet);

        return banSet.size();

    }
    
    // 재귀로 불량 사용자 아이디 목록이 담긴 Set 생성
    private void count(int index, Set<String> banned,
                       String[][] bans, Set<Set<String>> banSet) {
        if (index == bans.length) {
            banSet.add(new HashSet<>(banned));
            return;
        }
        for (String id : bans[index]) {
            if(banned.contains(id)) continue;
            banned.add(id);
            count(index+1, banned, bans, banSet);
            banned.remove(id);
        }
    }
}