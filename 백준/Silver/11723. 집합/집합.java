import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static ArrayList<Integer> S;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        S = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            String[] split = line.split(" ");

            if(split[0].equals("all")) T.all();
            else if(split[0].equals("empty")) S.clear();
            else {
                int k = Integer.parseInt(split[1]);
                if(split[0].equals("add") && !S.contains(k)) S.add(k);
                else if(split[0].equals("remove") && S.contains(k)) S.remove(Integer.valueOf(k));
                else if(split[0].equals("check")) T.check(k);
                else if(split[0].equals("toggle")) T.toggle(k);
            }
        }
        System.out.print(sb);
    }

    private void all() {
        S.clear();
        for (int i = 1; i <= 20; i++) S.add(i);
    }

    private void toggle(int k) {
        if(S.contains(k)) S.remove(Integer.valueOf(k));
        else S.add(k);
    }

    private void check(int k) {
        if(S.contains(k)) sb.append(1 + "\n");
        else sb.append(0 + "\n");
    }

}
