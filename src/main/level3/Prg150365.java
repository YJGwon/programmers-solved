package main.level3;

import java.util.Map;
import java.util.TreeMap;

// 미로 탈출 명령어
// https://school.programmers.co.kr/learn/courses/30/lessons/150365
class Prg150365 {

    private static final Map<Character, int[]> DIRECTIONS = new TreeMap<>();

    static {
        DIRECTIONS.put('d', new int[]{1, 0});
        DIRECTIONS.put('l', new int[]{0, -1});
        DIRECTIONS.put('r', new int[]{0, 1});
        DIRECTIONS.put('u', new int[]{-1, 0});
    }

    private int xSize;
    private int ySize;
    private int endX;
    private int endY;
    private boolean arrived;
    private String answer;

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        endX = r;
        endY = c;
        if (isReachable(x, y, k)) {
            xSize = n;
            ySize = m;
            dfs(x, y, new char[k], 0);
        }

        if (arrived) {
            return answer;
        }
        return "impossible";
    }

    private void dfs(int x, int y, char[] path, int length) {
        if (arrived) { // 이미 도착
            return;
        }

        if (length == path.length) { // 이동 끝
            if (x == endX && y == endY) { // 도착
                arrived = true;
                answer = String.valueOf(path);
            }
            return;
        }

        if (!isReachable(x, y, path.length - length)) { // 도달 할 수 없음
            return;
        }

        for (Character movement : DIRECTIONS.keySet()) {
            int[] direction = DIRECTIONS.get(movement);
            int nextX = x + direction[0];
            int nextY = y + direction[1];
            if (nextX < 1 || nextX > xSize) { // 격자 바깥
                continue;
            }
            if (nextY < 1 || nextY > ySize) { // 격자 바깥
                continue;
            }
            path[length] = movement;
            dfs(nextX, nextY, path, length + 1);
        }
    }

    private boolean isReachable(int x, int y, int moveLeft) {
        int distance = Math.abs(endX - x) + Math.abs(endY - y);
        return distance <= moveLeft && (moveLeft - distance) % 2 == 0;
    }
}
