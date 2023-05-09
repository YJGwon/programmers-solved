package main.level2;

import java.util.*;

// 모음 사전
// https://school.programmers.co.kr/learn/courses/30/lessons/84512
class Prg84512 {
    // 진수 변환과 유사하게 처리
    // 각 자리 수는 (아래 자리) * 5(= Alphabet 개수) + 1(= 공백)

    private static final int MAX_OFFSET = 781;
    private static final Map<Character, Integer> ALPHABETS = Map.of('A', 0,
            'E', 1,
            'I', 2,
            'O', 3,
            'U', 4);

    public int solution(String word) {
        int answer = 0;
        int offset = MAX_OFFSET;
        for (int i = 0; i < word.length(); i++) {
            final char now = word.charAt(i);
            if (now != ' ') {
                answer += offset * ALPHABETS.get(now) + 1;
            }
            offset = (offset - 1) / ALPHABETS.size();
        }
        return answer;
    }
}
