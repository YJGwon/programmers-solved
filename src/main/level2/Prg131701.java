package main.level2;

import java.util.HashSet;
import java.util.Set;

// 연속 부분 수열 합의 개수
// https://school.programmers.co.kr/learn/courses/30/lessons/131701
class Prg131701 {
    public int solution(int[] elements) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < elements.length; i++) {
            int sum = elements[i];
            set.add(sum);
            for (int j = 1; j < elements.length; j++) {
                sum += elements[(i + j) % elements.length];
                set.add(sum);
            }
        }
        return set.size();
    }
}
