package main.level3;

// 스티커 모으기(2)
// https://school.programmers.co.kr/learn/courses/30/lessons/12971
class Prg12971 {
    public int solution(int sticker[]) {
        if (sticker.length == 1) {
            return sticker[0];
        }

        int[][] maxSumWithoutLast = new int[sticker.length - 1][2]; // i 번째 스티커 {뗄 떄 최대, 안뗄 때 최대}
        maxSumWithoutLast[0][0] = sticker[0];

        int[][] maxSumWithoutFrist = new int[sticker.length - 1][2]; // i + 1 번째 스티커 {뗄 떄 최대, 안뗄 때 최대}
        maxSumWithoutFrist[0][0] = sticker[1];

        return Math.max(dp(maxSumWithoutLast, sticker, 0), dp(maxSumWithoutFrist, sticker, 1));
    }

    private int dp(int[][] maxSum, int[] sticker, int firstIndex) {
        for (int i = 1; i < sticker.length - 1; i++) {
            maxSum[i][0] = maxSum[i - 1][1] + sticker[i + firstIndex];
            maxSum[i][1] = Math.max(maxSum[i - 1][0], maxSum[i - 1][1]);
        }
        return Math.max(maxSum[maxSum.length - 1][0], maxSum[maxSum.length - 1][1]);
    }
}
