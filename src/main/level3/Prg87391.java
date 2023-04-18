package main.level3;

// 공 이동 시뮬레이션
// https://school.programmers.co.kr/learn/courses/30/lessons/87391
class Prg87391 {

    private static final int[][] DIRECTIONS = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public long solution(int n, int m, int x, int y, int[][] queries) {
        int maxX = n - 1;
        int maxY = m - 1;

        int[] xRange = {x, x}; //가능한 x값의 {최소, 최대}
        int[] yRange = {y, y}; //가능한 y값의 {최소, 최대}
        for (int i = queries.length - 1; i >= 0; i--) {
            int[] query = queries[i];
            int[] direction = DIRECTIONS[query[0]];
            if (direction[0] == 0) { //y이동
                setRange(yRange, query[1] * direction[1], maxY);
            } else { //x이동
                setRange(xRange, query[1] * direction[0], maxX);
            }

            if (xRange[0] == -1 || yRange[0] == -1) {
                return 0;
            }
        }
        long xCount = xRange[1] - xRange[0] + 1;
        long yCount = yRange[1] - yRange[0] + 1;
        return xCount * yCount;
    }

    private void setRange(int[] range, int move, int max) {
        int start = range[0] - move;
        int end = range[1] - move;
        // 경계에 있는 경우
        if (range[0] == 0 && move < 0) {
            start = 0;
        }
        if (range[1] == max && move > 0) {
            end = max;
        }

        if (cannotReach(start, max) && cannotReach(end, max)) {
            range[0] = -1;
            range[1] = -1;
            return;
        }
        range[0] = Math.max(start, 0);
        range[1] = Math.min(end, max);
    }

    private boolean cannotReach(int i, int max) {
        return i < 0 || max < i;
    }
}
