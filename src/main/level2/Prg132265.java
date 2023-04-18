package main.level2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// 롤케이크 자르기
// https://school.programmers.co.kr/learn/courses/30/lessons/132265
class Prg132265 {
    public int solution(int[] topping) {
        Map<Integer, Integer> counts = new HashMap<>(); //남은거(동생거)
        Set<Integer> cheol = new HashSet<>(); //철수거

        for (int i = 0; i < topping.length; i++) {
            // 토핑별 총 개수 세기
            counts.put(topping[i], counts.getOrDefault(topping[i], 0) + 1);
        }

        int answer = 0;
        int i = 0;
        while (counts.size() >= cheol.size()) { // 철수가 더 많이 가지면 종료
            cheol.add(topping[i]);
            counts.put(topping[i], counts.get(topping[i]) - 1);
            if (counts.get(topping[i]) == 0) { // 철수가 다 먹은 토핑
                counts.remove(topping[i]);
            }
            if (cheol.size() == counts.size()) { // 철수 토핑 == 동생 토핑
                answer++;
            }
            i++;
        }
        return answer;
    }
}
