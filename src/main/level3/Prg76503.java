package main.level3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

// 모두 0으로 만들기
// https://school.programmers.co.kr/learn/courses/30/lessons/76503
class Prg76503 {

    private static long[] arr;
    private static Map<Integer, List<Integer>> map;
    private static int[] inDegree;
    private static boolean[] visited;
    private static long answer;

    public long solution(int[] a, int[][] edges) {
        arr = new long[a.length];
        map = new HashMap<>();
        answer = 0;

        long sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
            arr[i] = a[i];
            map.put(i, new ArrayList<>());
        }

        if (sum != 0) {
            return -1;
        }

        inDegree = new int[a.length];
        for (int i = 0; i < edges.length; i++) {
            map.get(edges[i][0]).add(edges[i][1]);
            map.get(edges[i][1]).add(edges[i][0]);
            inDegree[edges[i][0]]++;
            inDegree[edges[i][1]]++;
        }
        visited = new boolean[a.length];
        bfs();
        return answer;
    }

    private void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 1) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int now = queue.poll();
            visited[now] = true;
            answer += Math.abs(arr[now]);
            for (int i = 0; i < map.get(now).size(); i++) {
                int next = map.get(now).get(i);
                if (visited[next]) {
                    continue;
                }
                arr[next] += arr[now];
                if (--inDegree[next] == 1) {
                    queue.offer(next);
                }
            }
        }
    }
}
