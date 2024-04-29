import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        long size = Long.parseLong(br.readLine());
        long cnt = 0;

        for (int i = 0; i < n; i++) {
            long x = Long.parseLong(st.nextToken());
            if(x == 0) continue;
            long quotient = x / size;
            long remainder = x % size;
            if (remainder == 0) {
                cnt += quotient;
            } else cnt += quotient + 1;
        }

        long answer = cnt * size;
        System.out.println(answer);
    }
}
