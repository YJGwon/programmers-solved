package main.level1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;

// 공원 산책
// https://school.programmers.co.kr/learn/courses/30/lessons/172928
class Prg172928 {
    private static final Map<String, int[]> DIRECTIONS = new HashMap<>();

    static {
        DIRECTIONS.put("N", new int[]{-1, 0});
        DIRECTIONS.put("S", new int[]{1, 0});
        DIRECTIONS.put("W", new int[]{0, -1});
        DIRECTIONS.put("E", new int[]{0, 1});
    }

    private int w;
    private int h;

    public int[] solution(String[] park, String[] routes) {
        w = park[0].length();
        h = park.length;

        int[] current = new int[2];
        Set<Position> blocks = new HashSet<>();
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                if (park[y].charAt(x) == 'S') {
                    current[0] = y;
                    current[1] = x;
                } else if (park[y].charAt(x) == 'X') {
                    blocks.add(new Position(y, x));
                }
            }
        }

        for (int i = 0; i < routes.length; i++) {
            StringTokenizer route = new StringTokenizer(routes[i]);
            int[] direction = DIRECTIONS.get(route.nextToken());
            int step = Integer.parseInt(route.nextToken());
            if (canMove(current, blocks, direction, step)) {
                current[0] = current[0] + (direction[0] * step);
                current[1] = current[1] + (direction[1] * step);
            }
        }
        return current;
    }

    private boolean canMove(int[] current, Set<Position> blocks, int[] direction, int step) {
        for (int i = 1; i <= step; i++) {
            int nextY = current[0] + (direction[0] * i);
            int nextX = current[1] + (direction[1] * i);
            if (nextY < 0 || nextY >= h) {
                return false;
            }
            if (nextX < 0 || nextX >= w) {
                return false;
            }
            Position p = new Position(nextY, nextX);
            if (blocks.contains(p)) {
                return false;
            }
        }
        return true;
    }

    private static class Position {
        int y;
        int x;

        Position(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public boolean equals(Object o) {
            Position p = (Position) o;
            return this.x == p.x && this.y == p.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(y, x);
        }
    }
}
