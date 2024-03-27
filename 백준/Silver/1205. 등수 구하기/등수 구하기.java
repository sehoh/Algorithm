import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        int score = kb.nextInt();
        int p = kb.nextInt();
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) arr.add(kb.nextInt());
        System.out.println(T.solution(n, score, p, arr));
    }

    private int solution(int n, int score, int p, ArrayList<Integer> arr) {
        if(n == 0) return 1;
        int rank = 1;
        if(arr.size() == p && score <= arr.get(arr.size()-1)) return -1;
        for (int i = 0; i < n; i++) {
            if(score >= arr.get(i)){
                rank = i+1;
                break;
            }
            else rank ++;
        }

        if(rank <= p) return rank;
        else return -1;
    }
}
