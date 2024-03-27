import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Country target;
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        int k = kb.nextInt();
        ArrayList<Country> arr = new ArrayList<>();
        for(int i=0; i<n; i++){
            int a = kb.nextInt();
            int b = kb.nextInt();
            int c = kb.nextInt();
            int d = kb.nextInt();
            arr.add(new Country(a, b, c, d));
            if(a == k) target = new Country(a, b, c, d);
        }
        int idx = 0;
        for(Country c : arr){
            if(c.idx == target.idx) continue;
            if(c.g > target.g) idx++;
            else if(c.s > target.s) idx++;
            else if(c.b > target.b) idx++;
        }
        System.out.println(idx);
    }
    private static class Country {
        public int idx, g, s, b;
        Country(int idx, int g, int s, int b){
            this.idx = idx;
            this.g = g;
            this.s = s;
            this.b = b;
        }
    }
}
