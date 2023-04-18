package main.level3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// N으로 표현
// https://school.programmers.co.kr/learn/courses/30/lessons/42895
public class Prg42895 {
    public int solution(int N, int number) {
        if (N == number) {
            return 1;
        }

        List<Set<Integer>> mem = new ArrayList<>(); // mem(i) : N을 i번 사용한 결과의 집합
        mem.add(Collections.emptySet()); // 0번 (사용X)
        mem.add(Set.of(N)); // 1번
        for (int i = 2; i <= 8; i++) {
            Set<Integer> cur = new HashSet<>();
            for (int j = 1; j < i; j++) {
                for (Integer operand : mem.get(j)) {
                    for (Integer operator : mem.get(i - j)) {
                        cur.add(operand + operator);
                        cur.add(operand - operator);
                        cur.add(operand * operator);
                        if (operator != 0) {
                            cur.add(operand / operator);
                        }
                    }
                }
            }
            cur.add(Integer.parseInt(Integer.toString(N).repeat(i)));
            if (cur.contains(number)) {
                return i;
            }
            mem.add(cur);
        }
        return -1;
    }
}
