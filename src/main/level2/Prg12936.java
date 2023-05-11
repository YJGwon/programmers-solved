package main.level2;

import java.util.ArrayList;
import java.util.List;

// 줄 서는 방법
// https://school.programmers.co.kr/learn/courses/30/lessons/12936
class Prg12936 {
    // 진수 변환과 유사하게 처리
    // n번째 자리수는 (n-1)!

    public int[] solution(int n, long k) {
        int[] answer = new int[n];

        List<Integer> list = new ArrayList<>();
        long cases = 1;
        for (int i = 1; i <= n; i++) {
            list.add(i);
            cases *= i;
        }

        k--; // offset 연산 시 나누어 떨어지는 경우에 index 밀림 -> k를 -1해서 보정

        for (int i = 0; i < n; i++) {
            cases /= (n - i);
            int offset = (int) (k / cases);
            answer[i] = list.remove(offset);
            k %= cases;
        }
        return answer;
    }
}
