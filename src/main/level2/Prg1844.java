package main.level2;

import java.util.LinkedList;
import java.util.Queue;

// 게임 맵 최단거리
// https://school.programmers.co.kr/learn/courses/30/lessons/1844
class Prg1844 {

    private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private int n;
    private int m;

    public int solution(int[][] maps) {
        n = maps.length - 1;
        m = maps[0].length - 1;

        boolean[][] visited = new boolean[n + 1][m + 1];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 1});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            if (now[0] == n && now[1] == m) {
                return now[2];
            }

            for (int[] direction : DIRECTIONS) {
                int nextX = now[0] + direction[0];
                int nextY = now[1] + direction[1];
                if (isOutOfBound(nextX, nextY)) {
                    continue;
                }
                if (maps[nextX][nextY] == 0) {
                    continue;
                }
                if (visited[nextX][nextY]) {
                    continue;
                }
                queue.offer(new int[]{nextX, nextY, now[2] + 1});
                visited[nextX][nextY] = true;
            }
        }
        return -1;
    }

    private boolean isOutOfBound(int x, int y) {
        return x < 0 || y < 0 || x > n || y > m;
    }
}
