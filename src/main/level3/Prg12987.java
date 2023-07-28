package main.level3;

import java.util.Arrays;

// 숫자 게임
// https://school.programmers.co.kr/learn/courses/30/lessons/12987
class Prg12987 {
    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);

        int answer = 0;
        int bIdx = A.length - 1;
        for (int aIdx = bIdx; aIdx >= 0; aIdx--) {
            if (A[aIdx] >= B[bIdx]) {
                continue;
            }
            answer++;
            bIdx--;
        }

        return answer;
    }
}
