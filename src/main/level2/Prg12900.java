package main.level2;

// 2 x n 타일링
// https://school.programmers.co.kr/learn/courses/30/lessons/12900
class Prg12900 {

    private static final int MOD = 1_000_000_007;

    public int solution(int n) {
        if (n < 3) {
            return n;
        }

        int[] cache = new int[n + 1];
        cache[1] = 1;
        cache[2] = 2;

        for (int i = 3; i <= n; i++) {
            long count = cache[i - 2] + cache[i - 1];
            count %= MOD;
            cache[i] = (int) count;
        }

        return cache[n];
    }
}
