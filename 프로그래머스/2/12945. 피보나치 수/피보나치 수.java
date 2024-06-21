class Solution {
    static int[] memo = new int[100001];
    static int divider = 1234567;
    public int fibo(int n) {
        if(memo[n] > 0) return memo[n];
        if(n == 1) return memo[1] = 1;
        else if(n == 2) return memo[2] = 1;
        else return memo[n] = (fibo(n - 1) % divider + fibo(n - 2) % divider) % divider;
    }

    public int solution(int n) {
        return fibo(n);
    }
}