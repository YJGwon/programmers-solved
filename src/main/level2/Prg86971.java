package main.level2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// 전력망을 둘로 나누기
// https://school.programmers.co.kr/learn/courses/30/lessons/86971
class Prg86971 {

    private int n;
    private int minGap;
    private Map<Integer, Set<Integer>> map;
    private boolean[] visited;

    public int solution(int n, int[][] wires) {
        this.n = n;
        map = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            map.put(i, new HashSet<>());
        }

        for (int i = 0; i < wires.length; i++) {
            map.get(wires[i][0]).add(wires[i][1]);
            map.get(wires[i][1]).add(wires[i][0]);
        }

        minGap = n;
        visited = new boolean[n + 1];
        dfs(1);
        return minGap;
    }

    private int dfs(int now) {
        visited[now] = true;

        int count = 1;
        for (int next : map.get(now)) {
            if (visited[next]) {
                continue;
            }
            count += dfs(next);
        }

        minGap = Math.min(Math.abs(n - (count * 2)), minGap);
        return count;
    }
}
