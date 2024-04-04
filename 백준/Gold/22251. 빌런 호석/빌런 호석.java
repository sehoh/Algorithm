import java.util.Scanner;

public class Main {
    static int n, k, p, x, cnt = 0;
    static int[] xDigit;
    static int[][] display = {{1, 1, 1, 0, 1, 1, 1}, //0
                            {0, 0, 1, 0, 0, 0, 1}, //1
                            {0, 1, 1, 1, 1, 1, 0}, //2
                            {0, 1, 1, 1, 0, 1, 1}, //3
                            {1, 0, 1, 1, 0, 0, 1}, //4
                            {1, 1, 0, 1, 0, 1, 1}, //5
                            {1, 1, 0, 1, 1, 1, 1}, //6
                            {0, 1, 1, 0, 0, 0, 1}, //7
                            {1, 1, 1, 1, 1, 1, 1}, //8
                            {1, 1, 1, 1, 0, 1, 1}}; //9

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        n = kb.nextInt();
        k = kb.nextInt();
        p = kb.nextInt();
        x = kb.nextInt();
        xDigit = getDigit(x);
        T.solution();
        System.out.println(cnt);
    }
    private void solution(){
        for (int i = 1; i <= n; i++) {
            if(i == x) continue;
            if(isChangable(i)) cnt++;
        }
    }

    private static boolean isChangable(int n) {
        int[] nDigit = getDigit(n);
        int tmp =0;
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < 7; j++) {
                if(display[xDigit[i]][j] != display[nDigit[i]][j]){
                    tmp ++;
                    if(tmp > p) return false;
                }
            }
        }
        return true;
    }

    private static int[] getDigit(int num) {
        int[] result = new int[k];
        for(int i=0; i<k; i++){
            result[i] = num % 10;
            num /= 10;
        }
        return result;
    }
}
