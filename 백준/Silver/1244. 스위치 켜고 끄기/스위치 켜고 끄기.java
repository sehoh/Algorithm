import java.util.Scanner;

public class Main {
    static int n;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        n = kb.nextInt();
        arr = new int[n];
        for(int i=0; i<n; i++) arr[i] = kb.nextInt();
        int k = kb.nextInt();
        for (int i = 0; i < k; i++) {
            int a = kb.nextInt();
            int b = kb.nextInt();
            if(a == 1) T.boy(b);
            else if(a == 2) T.girl(b);
        }
        int cnt= 0;
        for(int x : arr) {
            sb.append(x).append(' ');
            cnt++;
            if(cnt % 20 == 0) sb.append('\n');
        }
        System.out.print(sb);
    }
    private void boy(int b) {
        for (int i = b - 1; i < n; i = i + b) toggle(i);
    }

    private void girl(int b) {
        int lt = b - 2, rt = b;
        while(lt >= 0 && rt < n ){
            if(arr[lt] == arr[rt]){
                lt --;
                rt ++;
            }
            else break;
        }
        lt++;
        for (int i = lt; i < rt; i++) toggle(i);
    }

    private void toggle(int i){
        if(arr[i] == 1) arr[i] = 0;
        else if(arr[i] == 0) arr[i] = 1;
    }
}
