package main.level3;

// 아방가르드 타일링
// https://school.programmers.co.kr/learn/courses/30/lessons/181186
class Prg181186 {
    // 길이가 i고 더 이상 세로로 쪼개지지 않는 블럭(단위블럭)의 개수 Bi
    // B1 = 1, B2 = 2, B3 = 5
    // B4 ~ : i%3이 1 또는 2면 2, 0이면 4

    // 길이가 i인 공간에 타일을 채우는 모든 경우의 수 Si
    // Si = B1*S(i-1) + B2*S(i-2) + ... + B(i-1)*S1 + Bi
    //    = 1*S(i-1) + 2*S(i-2) + 5*S(i-3) + 2*S(i-4) + 2*S(i-5) + 4*S(i-6) + ... + Bi
    //    = 2*(S1 ~ Si-1 누적합) - S(i-1) + 3*S(i-3) + 2*(i-6) + 2*(i-9) + ... + Bi
    //    = 2*(S1 ~ Si-1 누적합) + 2*S(i-3) + 2*(i-6) + 2*(i-9) + ... - S(i-1) + S(i-3) + Bi
    //    = 2*(S1 ~ Si-1 누적합) + 2*(Si-3, Si-9 ... 누적합) - S(i-1) + S(i-3) + Bi

    private static final long MOD = 1_000_000_007;
    private static final long[] UNITS = {4, 2, 2}; // B(3*n + i)

    public int solution(int n) {
        int[] cache = new int[Math.max(4, n + 1)];
        cache[1] = 1;
        cache[2] = 3;
        cache[3] = 10;

        long sum = 14; // Si의 누적합
        long[] prefixMod3 = {cache[3], cache[1], cache[2]}; // S(3*n + i)의 누적합

        for (int i = 4; i <= n; i++) {
            long now = MOD - cache[i - 1]; // - S(i-1)
            now += cache[i - 3] % MOD; // + S(i-3)
            now += sum * 2 % MOD; // + 2*(S1 ~ Si-1 누적합)
            now += prefixMod3[i % 3] * 2 % MOD; // + 2*(Si-3, Si-9 ... 누적합)
            now += UNITS[i % 3] % MOD; // Bi
            now %= MOD;

            prefixMod3[i % 3] = (prefixMod3[i % 3] + now) % MOD;
            sum = (sum + now) % MOD;
            cache[i] = (int) now;
        }
        return cache[n];
    }
}
