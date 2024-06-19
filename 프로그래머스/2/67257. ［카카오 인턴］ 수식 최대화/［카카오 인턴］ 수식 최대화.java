import java.util.*;

class Solution {
    private static String[][] precedences = { // 연산자의 우선순위 경우의 수 모두 생성
            "+-*".split(""),
            "+*-".split(""),
            "-*+".split(""),
            "-+*".split(""),
            "*-+".split(""),
            "*+-".split("")
    };
    
    
    private long calculate(long lhs, long rhs, String op) {
        return switch (op) {
            case "+" -> lhs + rhs;
            case "-" -> lhs - rhs;
            case "*" -> lhs * rhs;
            default -> 0;
        };
    }
    
    private long calculate(List<String> tokens, String[] precedences) {
        for (String op : precedences) { // 하나의 우선순위 중 하나의 연산자에 일치하는지 수식 전체 순회
            for (int i = 0; i < tokens.size(); i++) {
                if (tokens.get(i).equals(op)) {
                    long lhs = Long.parseLong(tokens.get(i - 1));
                    long rhs = Long.parseLong(tokens.get(i + 1));
                    long result = calculate(lhs, rhs, op);

                    tokens.remove(i - 1);
                    tokens.remove(i - 1);
                    tokens.remove(i - 1);
                    tokens.add(i - 1, String.valueOf(result));
                    i -= 2;
                }
            }
        }
        return Long.parseLong(tokens.get(0));
    }

    
    public long solution(String expression) {
        StringTokenizer st = new StringTokenizer(expression, "+-*", true); // String.split은 연산자를 제거해버리므로 StringTokenizer 사용
        List<String> tokens = new ArrayList<>(); // 원소의 추가, 제거 시간복잡도가 O(1)인 LinkedList로도 구현 가능
        List<Long> results = new ArrayList<>(); 
        while (st.hasMoreTokens()) {
            tokens.add(st.nextToken());
        }
        for (int i = 0; i < precedences.length; i++) {
            long value = Math.abs(calculate(new ArrayList<>(tokens), precedences[i]));
            results.add(value);
        }
        return Collections.max(results);

    }
}