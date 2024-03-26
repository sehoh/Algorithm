import java.util.Scanner;

public class Main {
    static int answer;
    static int[] fare, time;
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        fare = new int[3];
        time = new int[100];
        for(int i=0; i<3; i++) {
            int k = kb.nextInt();
            fare[i] = k;
        }
        for(int i=0; i<3; i++){
            int s = kb.nextInt();
            int e = kb.nextInt();
            for (int j = s; j < e; j++) {
                time[j] ++;
            }
        }
        for(int x : time){
            if(x == 1) answer += fare[0];
            else if(x == 2) answer += fare[1] * 2;
            else if(x == 3) answer += fare[2] * 3;
        }
        System.out.println(answer);

    }
}
