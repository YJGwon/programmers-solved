package main.level3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 등대
// https://school.programmers.co.kr/learn/courses/30/lessons/133500
class Prg133500 {
    int answer;
    Map<Integer, List<Integer>> map;

    public int solution(int n, int[][] lighthouse) {
        answer = 0;
        map = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            map.put(i, new ArrayList<>());
        }

        for (int i = 0; i < lighthouse.length; i++) {
            map.get(lighthouse[i][0]).add(lighthouse[i][1]);
            map.get(lighthouse[i][1]).add(lighthouse[i][0]);
        }

        turnOn(1, 0);
        return answer;
    }

    private boolean turnOn(int current, int before) {
        if (map.get(current).size() == 1 && map.get(current).get(0) == before) {
            return false;
        }

        boolean isChildTurnOn = true;
        for (Integer child : map.get(current)) {
            if (child == before) {
                continue;
            }
            isChildTurnOn &= turnOn(child, current);
        }

        if (!isChildTurnOn) {
            answer++;
        }
        return !isChildTurnOn;
    }
}
