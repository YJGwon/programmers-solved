package main.level3;

import java.util.ArrayDeque;
import java.util.Queue;

// 네트워크
// https://school.programmers.co.kr/learn/courses/30/lessons/43162
class Prg43162 {

    private int[][] computers;
    private boolean[] visited;

    public int solution(int n, int[][] computers) {
        this.computers = computers;
        visited = new boolean[n];

        int answer = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            visitConnectedTo(i);
            answer++;
        }

        return answer;
    }

    private void visitConnectedTo(int computer) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(computer);
        visited[computer] = true;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int i = 0; i < computers.length; i++) {
                if (computers[now][i] == 0 || visited[i]) {
                    continue;
                }
                queue.offer(i);
                visited[i] = true;
            }
        }
    }
}
