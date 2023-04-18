package main.level2;

import java.util.LinkedList;
import java.util.Queue;

// 미로 탈출
// https://school.programmers.co.kr/learn/courses/30/lessons/159993
class Prg159993 {
    private static final int[] DIRECTION_X = {-1, 1, 0, 0};
    private static final int[] DIRECTION_Y = {0, 0, -1, 1};

    public int solution(String[] maps) {
        Position startsAt = null;
        for (int i = 0; i < maps.length; i++) {
            if (maps[i].indexOf('S') >= 0) {
                startsAt = new Position(i, maps[i].indexOf('S'), 0);
            }
        }

        Position lever = findShortest(startsAt, 'L', maps);
        if (lever.x < 0) {
            return -1;
        }
        Position exit = findShortest(lever, 'E', maps);
        if (exit.x < 0) {
            return -1;
        }
        return exit.moved;
    }

    private Position findShortest(Position startsAt, char goal, String[] maps) {
        Position minMoved = new Position(-1, -1, Integer.MAX_VALUE);

        Queue<Position> queue = new LinkedList<>();
        queue.offer(startsAt);
        boolean[][] visited = new boolean[maps.length][maps[0].length()];
        visited[startsAt.x][startsAt.y] = true;

        while (!queue.isEmpty()) {
            Position current = queue.poll();
            if (maps[current.x].charAt(current.y) == goal) {
                if (current.hasLessMovedThen(minMoved)) {
                    minMoved = current;
                }
                continue;
            }
            for (int i = 0; i < 4; i++) {
                int nextX = current.x + DIRECTION_X[i];
                int nextY = current.y + DIRECTION_Y[i];
                if (nextX < 0 || nextX >= maps.length) {
                    continue;
                }
                if (nextY < 0 || nextY >= maps[0].length()) {
                    continue;
                }
                if (visited[nextX][nextY]) {
                    continue;
                }
                if (maps[nextX].charAt(nextY) != 'X') {
                    queue.offer(new Position(nextX, nextY, current.moved + 1));
                    visited[nextX][nextY] = true;
                }
            }
        }
        return minMoved;
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

        boolean hasLessMovedThen(Position other) {
            return this.moved < other.moved;
        }
    }
}
