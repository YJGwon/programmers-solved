package main.level2;

// 3 x n 타일링
// https://school.programmers.co.kr/learn/courses/30/lessons/12902
class Prg12902 {
    // 홀수일 때는 경우 없음 (타일 면적 2 -> 홀수는 채울 수 없음)

    // 단위블럭 = 더 이상 세로로 쪼갤 수 없는 블럭이라고 할 때
    // n = 2 일 때 단위블럭 3개, 4 이상 짝수일 때 2개

    // 길이 n일 때 모든 경우의 수 Sn
    // 3 * Sn-2 + 2 * Sn-4 + ... + 2 * S2 + 2(<-길이 n인 단위블럭)
    // 3 * Sn-2 + 2 * (Sn-4 + ... + S2) + 2

    // 짝수만 가능하므로 n을 2로 나누어 연산
    // 길이 i * 2일 때 모든 경우의 수 Si
    // 3 * Si-1 + 2 * (S1 + ... + Si-2) + 2

    private static final int MOD = 1_000_000_007;

    public int solution(int n) {
        if (n == 2) {
            return 3;
        }
        if (n % 2 == 1) {
            return 0;
        }

        int seq = n / 2;
        int[] cache = new int[seq + 1]; // 길이 i * 2일 때의 경우의 수 Si
        cache[1] = 3;
        cache[2] = 11;

        long prefix = 3L; // S1 + ... + Si-2 누적합
        for (int i = 3; i <= seq; i++) {
            long answer = 3L * cache[i - 1] % MOD; // 3 * Si-1
            answer += prefix * 2L % MOD; // 2 * (S1 + ... + Si-2)
            answer %= MOD;
            answer += 2; // 길이 i * 2인 단위블럭
            answer %= MOD;

            prefix += cache[i - 1]; // 다음 i에 대한 Si-2 누적
            prefix %= MOD;

            cache[i] = (int) answer;
        }
        return cache[seq];
    }
}
