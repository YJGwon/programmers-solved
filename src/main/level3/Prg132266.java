package main.level3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

// 부대복귀
// https://school.programmers.co.kr/learn/courses/30/lessons/132266
class Prg132266 {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            map.put(i, new HashSet<>());
        }

        for (int i = 0; i < roads.length; i++) {
            map.get(roads[i][0]).add(roads[i][1]);
            map.get(roads[i][1]).add(roads[i][0]);
        }

        int[] min = dijkstra(n, destination, map);

        int[] answer = new int[sources.length];
        for (int i = 0; i < sources.length; i++) {
            if (min[sources[i]] == Integer.MAX_VALUE) {
                answer[i] = -1;
            } else {
                answer[i] = min[sources[i]];
            }
        }
        return answer;
    }

    private int[] dijkstra(int n, int destination, Map<Integer, Set<Integer>> map) {
        int[] min = new int[n + 1];
        Arrays.fill(min, Integer.MAX_VALUE);

        Queue<Integer> queue = new LinkedList<>();
        queue.add(destination);
        min[destination] = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (Integer next : map.get(current)) {
                if (min[next] <= min[current] + 1) {
                    continue;
                }
                min[next] = min[current] + 1;
                queue.offer(next);
            }
        }
        return min;
    }
}
