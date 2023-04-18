package main.level2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.function.Function;

// 빛의 경로 사이클
// https://school.programmers.co.kr/learn/courses/30/lessons/86052
class Prg86052 {

    // 우회전 - DIRECTIONS[(i - 1 + 4) % 4]
    // 좌회전 - DIRECTIONS[i + 1 % 4]
    private static final int[][] DIRECTIONS = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    private static final Map<Character, Function<Integer, Integer>> TURN = new HashMap<>();

    static {
        TURN.put('S', i -> i);
        TURN.put('L', i -> (i + 1) % 4);
        TURN.put('R', i -> (i - 1 + 4) % 4);
    }

    private int xSize;
    private int ySize;
    private boolean[][][] visited;

    public int[] solution(String[] grid) {
        xSize = grid.length;
        ySize = grid[0].length();
        visited = new boolean[xSize][ySize][4];

        PriorityQueue<Integer> sortedQueue = new PriorityQueue<>();
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                for (int k = 0; k < 4; k++) {
                    if (visited[i][j][k]) {
                        continue;
                    }
                    visited[i][j][k] = true;
                    sortedQueue.offer(findCycle(grid, new Point(i, j, k)));
                }
            }
        }

        int[] answer = new int[sortedQueue.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = sortedQueue.poll();
        }
        return answer;
    }

    private int findCycle(String[] grid, Point start) {
        Queue<Path> queue = new LinkedList<>();
        queue.offer(new Path(start, 0));

        while (!queue.isEmpty()) {
            Path path = queue.poll();
            // System.out.println(path.toString());
            Point now = path.now;
            if (now.equals(start) && path.length > 0) { // 사이클 찾음
                return path.length;
            }

            Point next = now.next(grid[now.x].charAt(now.y));
            visited[next.x][next.y][next.direction] = true;
            queue.offer(new Path(next, path.length + 1));
        }
        return -1;
    }

    private class Path {
        Point now;
        int length;

        Path(Point now, int length) {
            this.now = now;
            this.length = length;
        }

        @Override
        public String toString() {
            return "now : " + now.toString() + "\n"
                    + "length : " + length + "\n";
        }
    }

    private class Point {
        int x;
        int y;
        int direction;

        Point(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

        Point next(char c) {
            int nextDirection = TURN.get(c).apply(this.direction);
            int nextX = getIndex(this.x + DIRECTIONS[nextDirection][0], xSize - 1);
            int nextY = getIndex(this.y + DIRECTIONS[nextDirection][1], ySize - 1);
            return new Point(nextX, nextY, nextDirection);
        }

        private int getIndex(int i, int max) {
            if (i < 0) {
                return max;
            }
            if (i > max) {
                return 0;
            }
            return i;
        }

        @Override
        public boolean equals(Object o) {
            Point p = (Point) o;
            return this.x == p.x
                    && this.y == p.y
                    && this.direction == p.direction;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, direction);
        }

        @Override
        public String toString() {
            return "x : " + x + "\n"
                    + "y : " + y + "\n"
                    + "direction" + Arrays.toString(DIRECTIONS[direction]);
        }
    }
}
