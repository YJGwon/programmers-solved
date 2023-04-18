package main.level3;

import java.util.LinkedList;
import java.util.Queue;

// 아이템 줍기
// https://school.programmers.co.kr/learn/courses/30/lessons/87694
class Prg87694 {
    private static final int[][] DIRECTIONS = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    private boolean[][] board;

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        board = new boolean[101][101];
        for (int i = 0; i < rectangle.length; i++) {
            drawBorder(rectangle[i]);
        }

        for (int i = 0; i < rectangle.length; i++) {
            eraseInner(rectangle[i]);
        }

        return bfs(characterX * 2, characterY * 2, itemX * 2, itemY * 2) / 2;
    }

    private int bfs(int characterX, int characterY, int itemX, int itemY) {
        int answer = Integer.MAX_VALUE;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{characterX, characterY, 0});
        board[characterX][characterY] = false;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            if (current[0] == itemX && current[1] == itemY) {
                return current[2];
            }

            for (int i = 0; i < 4; i++) {
                int[] direction = DIRECTIONS[i];
                int nextX = current[0] + direction[0];
                int nextY = current[1] + direction[1];
                if (nextX < 0 || nextX > 100 || nextY < 0 || nextY > 100) {
                    continue;
                }
                if (board[nextX][nextY]) {
                    queue.offer(new int[]{nextX, nextY, current[2] + 1});
                    board[nextX][nextY] = false;
                }
            }
        }
        return answer;
    }

    private void drawBorder(int[] rectangle) {
        for (int i = rectangle[0] * 2; i <= rectangle[2] * 2; i++) {
            board[i][rectangle[1] * 2] = true;
            board[i][rectangle[3] * 2] = true;
        }
        for (int i = rectangle[1] * 2; i <= rectangle[3] * 2; i++) {
            board[rectangle[0] * 2][i] = true;
            board[rectangle[2] * 2][i] = true;
        }
    }

    private void eraseInner(int[] rectangle) {
        for (int i = rectangle[0] * 2 + 1; i < rectangle[2] * 2; i++) {
            for (int j = rectangle[1] * 2 + 1; j < rectangle[3] * 2; j++) {
                board[i][j] = false;
            }
        }
    }
}
