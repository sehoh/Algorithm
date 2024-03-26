import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        String s = kb.nextLine();
        System.out.println(T.solution(s));
    }

    private String solution(String s) {
        String answer = "";
        String upperS = s.toUpperCase();
        Map<Character, Integer> map = new HashMap<>();
        for(char c : upperS.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int max = Integer.MIN_VALUE;
        for(char key : map.keySet()){
            if(map.get(key) > max){
                max = map.get(key);
            }
        }
        int cnt = 0;
        for(char key : map.keySet()){
            if(map.get(key) == max) {
                cnt++;
                answer += key;
            }
            if(cnt >=2) return "?";
        }
        return answer;
    }
}
