package main.level3;

import java.util.Arrays;
import java.util.Comparator;

// 단속카메라
// https://school.programmers.co.kr/learn/courses/30/lessons/42884
class Prg42884 {
    public int solution(int[][] routes) {
        Arrays.sort(routes, Comparator.comparingInt(arr -> arr[1]));

        int lastCamera = -30001;
        int answer = 0;
        for (int[] route : routes) {
            if (lastCamera < route[0]) {
                answer++;
                lastCamera = route[1];
            }
        }
        return answer;
    }
}
