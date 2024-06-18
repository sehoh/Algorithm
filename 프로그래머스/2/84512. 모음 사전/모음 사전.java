import java.util.*;

class Solution {
    private final static char[] chars = "AEIOU".toCharArray();
    static List<String> list = new ArrayList<>();
    
    private void generate(String word){
        list.add(word);
        
        if(word.length() == 5) return;
        
        for(char c : chars){
            generate(word + c);
        }
    }

    public int solution(String word) {
        generate("");
        return list.indexOf(word);
    }
}