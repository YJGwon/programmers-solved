package main.level3;

import java.util.Map;

// 숫자 타자 대회
// https://school.programmers.co.kr/learn/courses/30/lessons/136797
class Prg136797 {

    private static final Map<Integer, int[]> KEY_POSITIONS = Map.ofEntries(
            Map.entry(1, new int[]{0, 0}),
            Map.entry(2, new int[]{0, 1}),
            Map.entry(3, new int[]{0, 2}),
            Map.entry(4, new int[]{1, 0}),
            Map.entry(5, new int[]{1, 1}),
            Map.entry(6, new int[]{1, 2}),
            Map.entry(7, new int[]{2, 0}),
            Map.entry(8, new int[]{2, 1}),
            Map.entry(9, new int[]{2, 2}),
            Map.entry(0, new int[]{3, 1})
    );

    private static final int INITIAL_KEY_L = 4;
    private static final int INITIAL_KEY_R = 6;

    private final int[][] cachedWeight = new int[10][10]; // [i][j], i번에서 j번을 누를때의 weight

    private int[] targets;
    private int[][][] minWeight; // [i][j][k], i번째 글자부터 끝까지 j, k로 시작해서 누르는 가중치의 최소값

    public int solution(String numbers) {
        targets = new int[numbers.length()];
        minWeight = new int[targets.length][10][10];

        for (int i = 0; i < targets.length; i++) {
            targets[i] = Character.getNumericValue(numbers.charAt(i));
        }

        return dp(0, INITIAL_KEY_L, INITIAL_KEY_R);
    }

    private int dp(int depth, int l, int r) {
        if (depth == targets.length) {
            return 0;
        }

        if (minWeight[depth][l][r] != 0) { // 이미 계산되어 있는 경우
            return minWeight[depth][l][r];
        }

        int target = targets[depth];
        int weightLeft = r != target ? getWeightBetween(l, target) + dp(depth + 1, target, r) : Integer.MAX_VALUE;
        int weightRight = l != target ? getWeightBetween(r, target) + dp(depth + 1, l, target) : Integer.MAX_VALUE;

        int answer = Math.min(weightLeft, weightRight);
        // 양방향 정답 저장
        minWeight[depth][l][r] = answer;
        minWeight[depth][r][l] = answer;
        return answer;
    }

    private int getWeightBetween(int start, int end) {
        if (cachedWeight[start][end] != 0) { // 이미 계산되어 있을 경우
            return cachedWeight[start][end];
        }

        int[] startPosition = KEY_POSITIONS.get(start);
        int[] endPosition = KEY_POSITIONS.get(end);
        int xGap = Math.abs(startPosition[0] - endPosition[0]);
        int yGap = Math.abs(startPosition[1] - endPosition[1]);

        int diagonalMove = Math.min(xGap, yGap);
        int move = Math.max(xGap, yGap) - diagonalMove;
        int weight = (diagonalMove * 3) + (move * 2);
        if (xGap == 0 && yGap == 0) {
            weight = 1;
        }

        // 양방향 가중치 저장
        cachedWeight[start][end] = weight;
        cachedWeight[end][start] = weight;
        return weight;
    }
}
