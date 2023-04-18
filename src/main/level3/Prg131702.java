package main.level3;

import java.util.Arrays;

// 고고학 최고의 발견
// https://school.programmers.co.kr/learn/courses/30/lessons/131702
class Prg131702 {

    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private int xSize;
    private int ySize;
    private int[][] tobe;
    private int answer;

    public int solution(int[][] clockHands) {
        // 원소 n을 돌려서 0 만드려면 4 - n번
        // 원소 n을 a번 돌리면 (n + a) % 4
        xSize = clockHands.length;
        ySize = clockHands[0].length;
        tobe = new int[xSize][ySize];

        answer = Integer.MAX_VALUE;

        dfs(0, new int[ySize], clockHands);
        return answer;
    }

    private void dfs(int depth, int[] counts, int[][] clockHands) {
        if (depth == ySize) { // 첫째줄 돌리는 하나의 경우 완성
            getAnswer(counts, clockHands);
            return;
        }

        for (int count = 0; count < 4; count++) {
            counts[depth] = count;
            dfs(depth + 1, counts, clockHands);
        }
    }

    private void getAnswer(int[] counts, int[][] clockHands) {
        int[][] cp = copy(clockHands); // 깊은 복사
        int count = turnFirstRow(counts, cp); // 첫째줄 counts대로 돌렸을 때 돌려야 하는 횟수 count
        for (int x = 1; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                if (cp[x - 1][y] == 0) {
                    continue;
                }
                int required = 4 - cp[x - 1][y];
                turn(x, y, cp, required);
                count += required;
            }
        }
        if (!Arrays.deepEquals(cp, tobe)) { // 12시로 만들어지지 않은 경우
            return;
        }
        answer = Math.min(answer, count);
    }

    private int turnFirstRow(int[] counts, int[][] clockHands) {
        int count = 0;
        for (int y = 0; y < ySize; y++) {
            count += counts[y];
            turn(0, y, clockHands, counts[y]);
        }
        return count;
    }

    private void turn(int x, int y, int[][] clockHands, int count) {
        if (count % 4 == 0) {
            return;
        }

        clockHands[x][y] = turn(clockHands[x][y], count);

        for (int i = 0; i < 4; i++) {
            int nextX = x + DIRECTIONS[i][0];
            int nextY = y + DIRECTIONS[i][1];
            if (nextX < 0 || xSize <= nextX || nextY < 0 || ySize <= nextY) {
                continue;
            }
            clockHands[nextX][nextY] = turn(clockHands[nextX][nextY], count);
        }
    }

    private int turn(int n, int count) {
        return (n + count) % 4;
    }

    private int[][] copy(int[][] clockHands) {
        int[][] clone = new int[xSize][ySize];
        for (int i = 0; i < xSize; i++) {
            clone[i] = clockHands[i].clone();
        }
        return clone;
    }
}
