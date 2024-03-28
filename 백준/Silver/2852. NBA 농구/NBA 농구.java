import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int prevTime = 0, isWinning = 0;
        int[] teamScore = new int[3];
        int[] teamWinningTime = new int[3];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int teamN = Integer.parseInt(st.nextToken());
            int curTime = T.getSeconds(st.nextToken());
            teamScore[teamN] ++;
            teamWinningTime[isWinning] += curTime - prevTime;
            prevTime = curTime;

            if(teamScore[2] > teamScore[1]) isWinning = 2;
            else if(teamScore[1] > teamScore[2]) isWinning = 1;
            else isWinning = 0;
        }
        teamWinningTime[isWinning] += (48 * 60) - prevTime;
        System.out.printf("%02d:%02d\n", teamWinningTime[1] /60, teamWinningTime[1] % 60);
        System.out.printf("%02d:%02d\n", teamWinningTime[2] /60, teamWinningTime[2] % 60);
    }
    private int getSeconds(String token){
        int ntime = (token.charAt(0) - '0') * 600;
        ntime += (token.charAt(1) - '0') * 60;
        ntime += (token.charAt(3) - '0') * 10;
        ntime += (token.charAt(4) - '0');
        return ntime;
    }
}
