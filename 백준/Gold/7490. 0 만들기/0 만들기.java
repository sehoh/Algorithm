import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import static java.util.stream.Collectors.toList;

public class Main {
    static String [] operators = {" ", "+", "-"};

    public static void main(String[] args){
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        int t = Integer.parseInt(kb.next());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(kb.next());
            T.solution(n);
        }
    }

    private void solution(int n) {
        String equals = "1";
        DFS(1, n, equals);
        System.out.println();
    }

    private static void DFS(int L, int n, String eqs){
        if(L == n){
            if(calculate(eqs) == 0) System.out.println(eqs);
        }
        else {
            for (int i = 0; i < 3; i++) {
                DFS(L+1, n, eqs + operators[i] + (L+1));
            }
        }
    }
    private static int calculate(String eqs){
        eqs = eqs.replaceAll(" ", "");
        Iterator<Integer> it = Arrays.stream(eqs.split("[+, -]"))
                .map(Integer::parseInt)
                .collect(toList()).iterator();
        int result = it.next();
        for (int i = 0; i < eqs.length(); i++) {
            if(eqs.charAt(i) == '+'){
                result += it.next();
            }
            else if (eqs.charAt(i) == '-') {
                result -= it.next();
            }
        }

        return result;
    }
}
