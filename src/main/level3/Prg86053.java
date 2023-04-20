package main.level3;

// 금과 은 운반하기
// https://school.programmers.co.kr/learn/courses/30/lessons/86053
class Prg86053 {

    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long min = 0L;
        long max = 400_000_000_000_000L;
        while (min < max) {
            long mid = (min + max) / 2L;
            if (canCarry(a, b, g, s, w, t, mid)) {
                max = mid;
            } else {
                min = mid + 1;
            }
        }

        return max;
    }

    private boolean canCarry(int a, int b, int[] g, int[] s, int[] w, int[] t, long time) {
        long total = 0L;
        long gSum = 0L;
        long sSum = 0L;
        for (int i = 0; i < g.length; i++) {
            long count = time / t[i];
            if (count % 2L == 0L) {
                count /= 2L;
            } else {
                count /= 2L;
                count++;
            }

            long max = Math.min(w[i] * count, g[i] + s[i]);
            total += max;
            gSum += Math.min(max, g[i]);
            sSum += Math.min(max, s[i]);
            if (total >= a + b && gSum >= a && sSum >= b) {
                return true;
            }
        }
        return false;
    }
}
