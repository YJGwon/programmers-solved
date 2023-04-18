package main.level2;

import java.util.Arrays;

// 숫자 변환하기
// https://school.programmers.co.kr/learn/courses/30/lessons/154538
class Prg154538 {
    public int solution(int x, int y, int n) {
        int[] cache = new int[y + 1];
        Arrays.fill(cache, Integer.MAX_VALUE);
        cache[y] = 0;
        for (int i = y; i > x; i--) {
            if (cache[i] == Integer.MAX_VALUE) {
                continue;
            }
            int count = cache[i] + 1;
            if (i % 2 == 0 && i / 2 >= x) {
                cache[i / 2] = Math.min(cache[i / 2], count);
            }
            if (i % 3 == 0 && i / 3 >= x) {
                cache[i / 3] = Math.min(cache[i / 3], count);
            }
            if (i - n >= x) {
                cache[i - n] = Math.min(cache[i - n], count);
            }
        }
        if (cache[x] == Integer.MAX_VALUE) {
            return -1;
        }
        return cache[x];
    }
}
