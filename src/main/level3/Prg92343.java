package main.level3;

import java.util.*;

// 양과 늑대
// https://school.programmers.co.kr/learn/courses/30/lessons/92343
class Prg92343 {

    private static final int SHEEP = 0;
    private static final int WOLF = 1;

    private int[] info;
    private Map<Integer, List<Integer>> graph;
    private int answer;

    public int solution(int[] info, int[][] edges) {
        this.info = info;
        graph = new HashMap<>();
        answer = 0;

        for (int i = 0; i < info.length; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int i = 0; i < edges.length; i++) {
            graph.get(edges[i][0]).add(edges[i][1]);
        }

        dfs(0, 0, 0, graph.get(0));
        return answer;
    }

    private void dfs(int sheep, int wolf, int now, List<Integer> nexts) {
        if (info[now] == SHEEP) {
            sheep++;
        } else {
            wolf++;
        }

        if (wolf >= sheep || nexts.isEmpty()) {
            answer = Math.max(answer, sheep);
            return;
        }

        for (Integer next : nexts) {
            List<Integer> newNexts = new ArrayList<>(nexts);
            newNexts.remove(next);
            newNexts.addAll(graph.get(next));
            dfs(sheep, wolf, next, newNexts);
        }
    }
}
