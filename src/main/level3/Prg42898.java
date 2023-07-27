package main.level3;

// 등굣길
// https://school.programmers.co.kr/learn/courses/30/lessons/42898
class Prg42898 {

    public int solution(int m, int n, int[][] puddles) {
        int[][] map = new int[m][n];
        for (int[] puddle : puddles) {
            map[puddle[0] - 1][puddle[1] - 1] = -1;
        }

        map[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == -1) { // 웅덩이
                    continue;
                }

                if (i > 0 && map[i - 1][j] != -1) {
                    map[i][j] += map[i - 1][j];
                    map[i][j] %= 1_000_000_007;
                }
                if (j > 0 && map[i][j - 1] != -1) {
                    map[i][j] += map[i][j - 1];
                    map[i][j] %= 1_000_000_007;
                }
            }
        }
        return map[m - 1][n - 1];
    }
}
