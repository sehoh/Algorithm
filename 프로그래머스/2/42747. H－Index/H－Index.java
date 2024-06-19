import java.util.*;

class Solution {
    private int count(int h, int[] citations) {
        int len = citations.length;
        if(h == 0) return 0;
        if (h == len && citations[0] >= h) return h;
        if (citations[len - h] >= h && citations[len - h - 1] <= h) {
            return h;
        } else return count(h - 1, citations);
    }
    
    public int solution(int[] citations) {
        Arrays.sort(citations);
        int h = count(citations.length, citations);
        return h;
    }
    
}