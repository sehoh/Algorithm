class Solution {
    public int[] solution(String s) {
        int[] answer = new int[2];
        while (s.length() >= 1) {
            if(s.length() == 1) break;
            int tmp = s.length();
            int cnt = removeZero(s);
            s = toBinaryNumber(tmp - cnt);
            answer[0] += 1;
            answer[1] += cnt;
        }

        return answer;
    }
    
    private int removeZero(String str) {
        return (int) str.chars().filter(c -> c == '0').count();
    }
    
    private String toBinaryNumber(int n) {
        String str = "";
        while (n >= 1) {
            if(n %2 == 0){
                str = "0" + str;
            } else {
                str = "1" + str;
            }
            n /= 2;
        }
        return str;
    }
}