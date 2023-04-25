package main.level3;

// 보행자 천국
// https://school.programmers.co.kr/learn/courses/30/lessons/1832
class Prg1832 {
    int MOD = 20170805;

    public int solution(int m, int n, int[][] cityMap) {
        int[][][] counts = new int[m][n][2]; //{옆에서 오는 경우, 위에서 오는 경우}
        counts[0][0] = new int[]{1, 0};
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                if (cityMap[x][y] == 1) {
                    continue;
                }
                int nextXCount;
                int nextYCount;
                if (cityMap[x][y] == 2) { // 같은 방향에서 왔을 때만 이동 가능
                    nextXCount = counts[x][y][0];
                    nextYCount = counts[x][y][1];
                } else {
                    nextXCount = nextYCount = (counts[x][y][0] + counts[x][y][1]) % MOD;
                }

                int nextX = x + 1;
                int nextY = y + 1;
                if (nextX < m) { // 오른쪽으로 이동
                    counts[nextX][y][0] = (counts[nextX][y][0] + nextXCount) % MOD;
                }
                if (nextY < n) { // 아래로 이동
                    counts[x][nextY][1] = (counts[x][nextY][1] + nextYCount) % MOD;
                }
            }
        }

        return (counts[m - 1][n - 1][0] + counts[m - 1][n - 1][1]) % MOD;
    }
}
