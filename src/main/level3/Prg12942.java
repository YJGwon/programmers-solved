package main.level3;

// 최적의 행렬 곱셈
// https://school.programmers.co.kr/learn/courses/30/lessons/12942
class Prg12942 {

    public int solution(int[][] matrix_sizes) {
        int size = matrix_sizes.length;
        int[][] dp = new int[size][size];

        for (int offset = 1; offset < size; offset++) {
            for (int start = 0; start < size - offset; start++) {
                int end = start + offset;
                dp[start][end] = Integer.MAX_VALUE;

                for (int mid = start; mid < end; mid++) {
                    int now = matrix_sizes[start][0] * matrix_sizes[mid][1] * matrix_sizes[end][1];
                    dp[start][end] = Math.min(dp[start][end], dp[start][mid] + now + dp[mid + 1][end]);
                }
            }
        }

        return dp[0][matrix_sizes.length - 1];
    }
}
