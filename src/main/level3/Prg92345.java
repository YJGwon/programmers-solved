package main.level3;

// 사라지는 발판
// https://school.programmers.co.kr/learn/courses/30/lessons/92345
class Prg92345 {
    private static final int[][] DIRECTIONS = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1}
    };

    private int xSize;
    private int ySize;

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        xSize = board.length;
        ySize = board[0].length;
        return Math.abs(play(board, aloc, bloc));
    }

    private int play(int[][] board, int[] now, int[] target) {
        // 이번 턴에 패배 확정 -> 0 return
        // 이길 수 있다 -> 이기는 경우 중 가장 적은 횟수 양수로 return
        // 질 수밖에 없다 -> 지는 경우 중 가장 큰 횟수 음수로 return
        if (board[now[0]][now[1]] == 0) { // 발판 사라짐
            return 0; // 패배 확정
        }

        boolean loose = true; // 질 수 밖에 없는가
        boolean hasNoWay = true; // 인접 발판이 없었는가
        int minWin = Integer.MAX_VALUE;
        int maxLoose = 0;

        board[now[0]][now[1]] = 0; // 밟은 발판 없앤 뒤 이동

        for (int i = 0; i < 4; i++) {
            final int[] next = getNext(now, i);
            if (notReachable(board, next)) {
                continue;
            }
            hasNoWay = false;

            final int res = play(board, target, next);
            if (res > 0) { // 다음 턴이 승리 -> 이번 턴은 패배
                maxLoose = Math.max(maxLoose, res);
            } else { // 다음 턴이 패배 -> 이번 턴은 승리
                loose = false; // 한 번이라도 이길 가능성 있다
                minWin = Math.min(minWin, -res);
            }
        }

        board[now[0]][now[1]] = 1; // 다음 경우의 수 탐색 전에 발판 원상복구

        if (hasNoWay) {
            return 0; // 패배 확정
        }
        if (loose) {
            return -(maxLoose + 1);
        }
        return minWin + 1;
    }

    private int[] getNext(int[] loc, int i) {
        int nextX = loc[0] + DIRECTIONS[i][0];
        int nextY = loc[1] + DIRECTIONS[i][1];
        return new int[]{nextX, nextY};
    }

    private boolean notReachable(int[][] board, int[] loc) {
        if (loc[0] < 0 || loc[0] >= xSize || loc[1] < 0 || loc[1] >= ySize) {
            return true;
        }
        return board[loc[0]][loc[1]] == 0;
    }
}
