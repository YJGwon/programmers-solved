package main.level4;

import java.util.Arrays;

// 징검다리
// https://school.programmers.co.kr/learn/courses/30/lessons/43236
class Prg43236 {
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        int left = 0;
        int right = distance;
        while (left < right) {
            int mid = (left + right) / 2;
            if (isMinGreaterThen(mid, rocks, n)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left - 1;
    }

    private boolean isMinGreaterThen(int min, int[] rocks, int n) {
        int prev = 0;
        int count = 0;
        for (int i = 0; i < rocks.length; i++) {
            if (rocks[i] - prev < min) {
                if (++count > n) {
                    return false;
                }
            } else {
                prev = rocks[i];
            }
        }
        return true;
    }
}
