package main.level3;

// 카운트 다운
// https://school.programmers.co.kr/learn/courses/30/lessons/131129
class Prg131129 {
    private static final int MAX_VALUE = 100_001;
    private static final int BULL = 50;
    int[][] cache; // [n][0]최소 다트 수 [n][1] 최대 불,싱글 수

    public int[] solution(int target) {
        cache = new int[target + 1][2];
        for (int i = 1; i <= target; i++) {
            cache[i][0] = MAX_VALUE;
        }

        return dp(target);
    }

    private int[] dp(int target) {
        if (target < 0) {
            return new int[]{MAX_VALUE, 0};
        }

        if (target == 0) {
            return new int[]{0, 0};
        }

        if (cache[target][0] != MAX_VALUE) {
            return cache[target];
        }

        int[] result = {MAX_VALUE, 0};

        // 불 한 번 던질 경우
        int[] temp = dp(target - BULL);
        setBetterScore(result, new int[]{temp[0] + 1, temp[1] + 1});

        for (int i = 1; i <= 20; i++) {
            // 싱글 한 번 던질 경우
            temp = dp(target - i);
            setBetterScore(result, new int[]{temp[0] + 1, temp[1] + 1});
            for (int j = 2; j <= 3; j++) {
                if (i * j <= 20) {
                    continue;
                }
                // 더블 또는 트리플 한 번 던질 경우
                temp = dp(target - (i * j));
                setBetterScore(result, new int[]{temp[0] + 1, temp[1]});
            }
        }
        cache[target][0] = result[0];
        cache[target][1] = result[1];
        return cache[target];
    }

    private void setBetterScore(int[] result, int[] temp) {
        if (temp[0] < result[0]) {
            result[0] = temp[0];
            result[1] = temp[1];
        } else if (temp[0] == result[0]) {
            result[1] = Math.max(temp[1], result[1]);
        }
    }
}
