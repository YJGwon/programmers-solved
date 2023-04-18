package main.level2;

import java.util.LinkedList;
import java.util.Queue;

// 리코쳇 로봇
// https://school.programmers.co.kr/learn/courses/30/lessons/169199
class Prg169199 {
    private static final int[][] directions = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public int solution(String[] board) {
        Position robot = find('R', board);
        Position goal = find('G', board);

        boolean[][] visited = new boolean[board.length][board[0].length()];
        Queue<Position> queue = new LinkedList<>();
        queue.offer(robot);
        visited[robot.x][robot.y] = true;
        int answer = -1;
        int min = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            Position current = queue.poll();
            if (current.x == goal.x && current.y == goal.y) {
                min = Math.min(min, current.moved);
                answer = min;
                continue;
            }
            for (int i = 0; i < 4; i++) {
                int x = current.x + directions[i][0];
                int y = current.y + directions[i][1];
                if (x < 0 || x == board.length || y < 0 || y == board[0].length()) {
                    continue;
                }
                if (board[x].charAt(y) != 'D') {
                    Position next = current.move(board, directions[i]);
                    if (visited[next.x][next.y]) {
                        continue;
                    }
                    visited[next.x][next.y] = true;
                    queue.offer(next);
                }
            }
        }

        return answer;
    }

    private Position find(char sign, String[] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length(); j++) {
                if (board[i].charAt(j) == sign) {
                    return new Position(i, j, 0);
                }
            }
        }
        return new Position(-1, -1, 0);
    }

    private static class Position {
        int x;
        int y;
        int moved;

        Position(int x, int y, int moved) {
            this.x = x;
            this.y = y;
            this.moved = moved;
        }

        Position move(String[] board, int[] direction) {
            int nextX = x;
            int nextY = y;
            while (board[nextX].charAt(nextY) != 'D') {
                nextX += direction[0];
                nextY += direction[1];
                if (nextX < 0 || nextX >= board.length) {
                    break;
                }
                if (nextY < 0 || nextY >= board[0].length()) {
                    break;
                }
            }
            nextX -= direction[0];
            nextY -= direction[1];
            return new Position(nextX, nextY, moved + 1);
        }
    }
}
