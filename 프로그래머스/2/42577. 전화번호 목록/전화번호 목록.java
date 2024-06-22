import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        Set<String> set = new HashSet<>();

        for (String phone : phone_book) {
            set.add(phone);
        }

        for (String phone : phone_book) {
            for (int end = 1; end < phone.length(); end++) {
                if (set.contains(phone.substring(0, end))) {
                    return false;
                }
            }
        }
        
        return true;
    }
}