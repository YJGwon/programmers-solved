package main.level2;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

// 무인도 여행
// https://school.programmers.co.kr/learn/courses/30/lessons/154540
class Prg154540 {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private boolean[][] visited;

    public int[] solution(String[] maps) {
        visited = new boolean[maps.length][maps[0].length()];
        PriorityQueue<Integer> answerQueue = new PriorityQueue<>();
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[0].length(); j++) {
                if (visited[i][j] || maps[i].charAt(j) == 'X') {
                    continue;
                }
                visited[i][j] = true;
                answerQueue.offer(findFoods(i, j, maps));
            }
        }
        if (answerQueue.isEmpty()) {
            return new int[]{-1};
        }
        int[] answer = new int[answerQueue.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = answerQueue.poll();
        }
        return answer;
    }

    private int findFoods(int x, int y, String[] maps) {
        int foods = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            foods += Character.getNumericValue(maps[current[0]].charAt(current[1]));
            for (int i = 0; i < 4; i++) {
                int nextX = current[0] + DIRECTIONS[i][0];
                int nextY = current[1] + DIRECTIONS[i][1];
                if (nextX < 0 || nextX >= maps.length) {
                    continue;
                }
                if (nextY < 0 || nextY >= maps[0].length()) {
                    continue;
                }
                if (visited[nextX][nextY] || maps[nextX].charAt(nextY) == 'X') {
                    continue;
                }
                visited[nextX][nextY] = true;
                queue.offer(new int[]{nextX, nextY});
            }
        }
        return foods;
    }
}
