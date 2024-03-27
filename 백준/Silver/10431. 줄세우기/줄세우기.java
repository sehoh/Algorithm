import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        Main T = new Main ();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int p = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        int[] arr;
        for (int i = 0; i < p; i++) {
            String[] split = br.readLine().split(" ");
            arr = new int[20];
            for (int j = 0; j < 20; j++) arr[j] = Integer.parseInt(split[j+1]);
            sb.append(split[0]);
            sb.append(" ");
            sb.append(T.solution(arr));
            sb.append('\n');
        }
        System.out.print(sb);

    }

    private int solution(int[] arr) {
        int answer = 0;
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    answer ++;
                }
            }
        }
        return answer;
    }
}
