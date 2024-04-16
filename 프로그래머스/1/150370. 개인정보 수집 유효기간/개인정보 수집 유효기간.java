import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        Map<String, Integer> term = new HashMap<>();
        for(String s : terms){
            String a = s.split(" ")[0];
            String b = s.split(" ")[1];
            term.put(a, Integer.parseInt(b));
        }
        int now = getDay(today);
        ArrayList<Integer> tmp = new ArrayList<>();

        for (int i =0; i< privacies.length; i++) {
            String s = privacies[i];
            String a = s.split(" ")[0];
            String b = s.split(" ")[1];
            int extended = term.get(b) * 28 + getDay(a) -1;

            if(extended < now){
                tmp.add(i+1);
            } 

        }
        int[] answer = new int[tmp.size()];
        for (int i = 0; i < tmp.size(); i++) {
            answer[i] = tmp.get(i);
        }

        return answer;
    }
    private static int getDay(String date){
        String[] arr = date.split("\\.");
        int Y = Integer.parseInt(arr[0]);
        int M = Integer.parseInt(arr[1]);
        int D = Integer.parseInt(arr[2]);
        return (Y-2000) * 12 * 28 + M * 28 + D;
    }

}