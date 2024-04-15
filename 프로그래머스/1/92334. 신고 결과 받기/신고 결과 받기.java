import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        Map<String, Set<String>> history = new HashMap<>();
            Map<String, Integer> ban_list = new HashMap<>();
            Map<String, Integer> mail = new HashMap<>();
            for (String s : report) {
                String a = s.split(" ")[0];
                String b = s.split(" ")[1];
                history.putIfAbsent(a, new HashSet<String>());
                history.get(a).add(b);
            }

            for (String user : history.keySet()) {
                for(String reportee : history.get(user)){
                    ban_list.put(reportee, ban_list.getOrDefault(reportee, 0) + 1);
//                    int cnt = ban_list.get(reportee);
//                    if(cnt >= k){
//                        mail.put(user, mail.getOrDefault(user, 0) + 1);
//                    }
                }
            }
            for(String user : history.keySet()){
                for(String reportee : history.get(user)){
                    if(ban_list.get(reportee) >= k){
                        mail.put(user, mail.getOrDefault(user, 0) + 1);
                    }
                }
            }

            int[] answer = new int[id_list.length];
            for(int i =0; i< id_list.length; i++){
                answer[i] = mail.getOrDefault(id_list[i], 0);
            }
            return answer;
    }
}