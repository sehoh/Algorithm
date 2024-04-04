import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        int h = kb.nextInt();
        int w = kb.nextInt();
        int[] arr = new int[w];
        for(int i=0; i<w; i++) arr[i] = kb.nextInt();
        System.out.println(T.solution(h, w, arr));
    }

    private int solution(int h, int w, int[] arr) {
        int cnt = 0;
        for(int i=1; i<arr.length-1; i++){
            int left = 0, right = 0;
            for (int l = 0; l < i; l++) {
                if (arr[l] > left) {
                    left = arr[l];
                }
            }

            for (int r = i + 1; r < arr.length; r++) {
                if (arr[r] > right) {
                    right = arr[r];
                }
            }

            if (arr[i] < left && arr[i] < right) {
                cnt += Math.min(right, left) - arr[i];
            }
        }

        return cnt;
    }
}
