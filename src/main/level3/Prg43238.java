package main.level3;

// 입국심사
// https://school.programmers.co.kr/learn/courses/30/lessons/43238
class Prg43238 {
    public long solution(int n, int[] times) {
        long minTime = 1_000_000_000;
        for (int i = 0; i < times.length; i++) {
            minTime = Math.min(minTime, times[i]);
        }

        long left = 0L;
        long right = minTime * (long) n;
        while (left < right) {
            long mid = (left + right) / 2;
            if (countPassed(mid, times) < n) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right;
    }

    private long countPassed(long time, int[] times) {
        long sum = 0;
        for (int i = 0; i < times.length; i++) {
            sum += time / (long) times[i];
        }
        return sum;
    }
}
