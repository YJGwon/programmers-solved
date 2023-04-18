package main.level2;

import java.util.Arrays;
import java.util.Comparator;

// 요격 시스템
// https://school.programmers.co.kr/learn/courses/30/lessons/181188
class Prg181188 {
    public int solution(int[][] targets) {
        Arrays.sort(targets, Comparator.comparingInt(a -> a[0]));

        int answer = 0;
        int start;
        int end = 0;
        for (int i = 0; i < targets.length; i++) {
            // System.out.println(Arrays.toString(targets[i]));
            start = targets[i][0];
            if (start < end) {
                end = Math.min(end, targets[i][1]);
                continue;
            }
            answer++;
            end = targets[i][1];
        }
        return answer;
    }
}
