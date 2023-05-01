package main.level3;

// 파괴되지 않은 건물
// https://school.programmers.co.kr/learn/courses/30/lessons/92344
class Prg92344 {

    private static final int FLAG_ATTACK = 1;
    private static final int FLAG_HEAL = 2;

    public int solution(int[][] board, int[][] skill) {
        final int N = board.length;
        final int M = board[0].length;
        final int[][] effect = new int[N + 1][M + 1];
        for (int i = 0; i < skill.length; i++) {
            final int r1 = skill[i][1];
            final int c1 = skill[i][2];
            final int r2 = skill[i][3];
            final int c2 = skill[i][4];
            int degree = skill[i][5];
            if (skill[i][0] == FLAG_ATTACK) {
                degree *= -1;
            }
            effect[r1][c1] += degree;
            effect[r2 + 1][c2 + 1] += degree;
            effect[r2 + 1][c1] -= degree;
            effect[r1][c2 + 1] -= degree;
        }

        for (int r = 0; r < N; r++) {
            for (int c = 1; c < M; c++) {
                effect[r][c] += effect[r][c - 1];
            }
        }

        for (int c = 0; c < M; c++) {
            for (int r = 1; r < N; r++) {
                effect[r][c] += effect[r - 1][c];
            }
        }

        int answer = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (board[r][c] + effect[r][c] > 0) {
                    answer++;
                }
            }
        }
        return answer;
    }
}
