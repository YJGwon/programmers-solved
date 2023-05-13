package main.level3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

// 보석 쇼핑
// https://school.programmers.co.kr/learn/courses/30/lessons/67258
class Prg67258 {
    public int[] solution(String[] gems) {
        final int types = new HashSet<>(Arrays.asList(gems)).size();

        final int[] answer = {1, gems.length};

        final Map<String, Integer> buying = new HashMap<>();

        int minLength = gems.length;
        int left = 0;
        int right = 0;
        while (right < gems.length) {
            buying.put(gems[right], buying.getOrDefault(gems[right], 0) + 1);
            if (buying.size() == types) {
                while (buying.size() == types) {
                    int count = buying.get(gems[left]);
                    if (count == 1) {
                        break;
                    } else {
                        buying.put(gems[left], count - 1);
                        left++;
                    }
                }
                final int length = (right - left) + 1;
                if (minLength > length) {
                    minLength = length;
                    answer[0] = left + 1;
                    answer[1] = right + 1;
                }
            }
            right++;
        }

        return answer;
    }
}
