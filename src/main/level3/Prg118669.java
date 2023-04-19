package main.level3;

import java.util.*;

// 등산코스 정하기
// https://school.programmers.co.kr/learn/courses/30/lessons/118669
class Prg118669 {

    private static final int FLAG_GATE = 1;
    private static final int FLAG_SUMMIT = 2;

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] flags = new int[n + 1]; // 해당 node가 출입구거나 정상인지 표시
        Queue<int[]> queue = new LinkedList<>(); // 탐색 queue {현재 node, 지금까지 max weight}
        for (int i = 0; i < gates.length; i++) {
            flags[gates[i]] = FLAG_GATE; // 출입구 표시
            queue.offer(new int[]{gates[i], 0}); // 모든 출입구 탐색 queue에 추가
        }
        for (int i = 0; i < summits.length; i++) {
            flags[summits[i]] = FLAG_SUMMIT; // 정상 표시
        }

        Map<Integer, List<int[]>> map = new HashMap<>(); // {도착지, weight}
        for (int i = 1; i <= n; i++) {
            map.put(i, new ArrayList<>());
        }

        for (int i = 0; i < paths.length; i++) {
            map.get(paths[i][0]).add(new int[]{paths[i][1], paths[i][2]});
            map.get(paths[i][1]).add(new int[]{paths[i][0], paths[i][2]});
        }
        return bfs(queue, map, flags);
    }

    private int[] bfs(Queue<int[]> queue, Map<Integer, List<int[]>> map, int[] flags) {
        PriorityQueue<int[]> pq = new PriorityQueue<>( // 정답 queue
                compareWeight().thenComparing(compareSummit()) // weight, 정상 오름차순 정렬
        );
        int[] min = new int[flags.length]; // 도달한 최소 weight
        Arrays.fill(min, Integer.MAX_VALUE);

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            if (now[1] > min[now[0]]) { // 최소 경로 아님
                continue;
            }
            if (flags[now[0]] == FLAG_SUMMIT) { // 정상 도착 -> 정답 queue에 추가
                pq.offer(now);
                continue;
            }

            for (int[] next : map.get(now[0])) {
                if (flags[next[0]] == FLAG_GATE) { // 출입구
                    continue;
                }
                int nextWeight = Math.max(next[1], now[1]);
                if (nextWeight >= min[next[0]]) { // 최소값 갱신할 수 있을때만 방문
                    continue;
                }
                min[next[0]] = nextWeight;
                queue.offer(new int[]{next[0], nextWeight});
            }
        }
        return pq.poll();
    }

    private static Comparator<int[]> compareSummit() {
        return Comparator.comparingInt(a -> a[0]);
    }

    private static Comparator<int[]> compareWeight() {
        return Comparator.comparingInt(a -> a[1]);
    }
}
