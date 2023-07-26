package main.level3;

// 순위
// https://school.programmers.co.kr/learn/courses/30/lessons/49191
class Prg49191 {

    public int solution(int n, int[][] results) {
        int[][] resultTable = new int[n + 1][n + 1];
        for (int[] result : results) {
            resultTable[result[0]][result[1]] = 1;
            resultTable[result[1]][result[0]] = -1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= n; k++) {
                    if (resultTable[i][j] == 1 && resultTable[j][k] == 1) {
                        resultTable[i][k] = 1;
                        resultTable[k][i] = -1;
                    } else if (resultTable[i][j] == -1 && resultTable[j][k] == -1) {
                        resultTable[i][k] = -1;
                        resultTable[k][i] = 1;
                    }
                }
            }
        }

        int answer = 0;
        for (int[] resultRow : resultTable) {
            int countZero = 0;
            for (int i = 1; i <= n; i++) {
                if (resultRow[i] == 0) {
                    countZero++;
                }
            }

            if (countZero == 1) {
                answer++;
            }
        }
        return answer;
    }
}
