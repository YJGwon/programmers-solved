package main.level3;

// 연속 펄스 부분 수열의 합
// https://school.programmers.co.kr/learn/courses/30/lessons/161988
class Prg161988 {
    public long solution(int[] sequence) {
        long[] sum_p = new long[sequence.length];
        sum_p[0] = sequence[0];
        long[] sum_n = new long[sequence.length];
        sum_n[0] = sequence[0] * -1;

        int pulse = -1;
        for (int i = 1; i < sequence.length; i++) {
            sum_p[i] = sum_p[i - 1] + ((long) sequence[i] * pulse);
            pulse *= -1;
            sum_n[i] = sum_n[i - 1] + ((long) sequence[i] * pulse);
        }

        return Math.max(findMaxSum(sum_p), findMaxSum(sum_n));
    }

    private long findMaxSum(long[] sum) {
        long max = 0;
        int startsAt = 0;
        int endsAt = 0;
        while (endsAt < sum.length) {
            long current = sum[endsAt];
            if (startsAt > 0) {
                current -= sum[startsAt - 1];
            }
            endsAt++;
            if (current < 0) {
                startsAt = endsAt;
            } else {
                max = Math.max(max, current);
            }
        }
        return max;
    }
}
