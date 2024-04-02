import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] record = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) record[i] = Integer.parseInt(st.nextToken());
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i=n; i>=1; i--) ans.add(record[i], i);
        for(int x : ans) System.out.print(x + " ");
    }
}