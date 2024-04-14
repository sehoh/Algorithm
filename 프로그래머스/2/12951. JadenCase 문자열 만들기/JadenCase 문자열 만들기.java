import java.util.*;

class Solution {
    public String solution(String s) {
        String tmp = "";
        for(char c : s.toCharArray()){
            if(Character.isAlphabetic(c)){
                tmp += Character.toLowerCase(c);
            }
            else{
                tmp += c;
            }
        }
        
        for(int i =1; i< tmp.length(); i++){
            if(Character.isAlphabetic(tmp.charAt(i)) && tmp.charAt(i-1) == ' '){ // 알파벳이고, 직전이 공백이라면
                tmp = replaceStr(tmp, i);
            }
        }
        // 맨 앞에 공백이 없다면
        if(tmp.charAt(0) != ' '){
            tmp = replaceStr(tmp, 0);
        }

        return tmp;
    }
    public static String replaceStr(String str, int idx){
        StringBuilder sb = new StringBuilder(str);
        sb.setCharAt(idx, Character.toUpperCase(str.charAt(idx)));
        str = sb.toString();
        return str;
    }
}