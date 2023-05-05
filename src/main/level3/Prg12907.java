package main.level3;

// 거스름돈
// https://school.programmers.co.kr/learn/courses/30/lessons/12907
class Prg12907 {

    private static final int MOD = 1_000_000_007;

    public int solution(int n, int[] money) {
        final int[] dp = new int[n + 1];
        dp[0] = 1;

        for (int i = 0; i < money.length; i++) {
            for (int j = money[i]; j <= n; j++) {
                dp[j] = (dp[j] + dp[j - money[i]]) % MOD;
            }
        }
        return dp[n];
    }
}
