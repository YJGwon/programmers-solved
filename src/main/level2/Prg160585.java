package main.level2;

// 혼자서 하는 틱택토
// https://school.programmers.co.kr/learn/courses/30/lessons/160585
class Prg160585 {
    public int solution(String[] board) {
        int countO = 0;
        int countX = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i].charAt(j) == 'O') {
                    countO++;
                } else if (board[i].charAt(j) == 'X') {
                    countX++;
                }
            }
        }
        if (countO < countX || countO > countX + 1) {
            return 0;
        }

        if (countO >= 3) {
            boolean oWins = hasWin(board, 'O');
            if (oWins && countO <= countX) {
                return 0;
            }
            if (countX >= 3 && hasWin(board, 'X')) {
                if (oWins || countO > countX) {
                    return 0;
                }
            }
        }
        return 1;
    }

    private boolean hasWin(String[] board, char sign) {
        boolean hasDiagonalFromLeft = true;
        boolean hasDiagonalFromRight = true;
        for (int i = 0; i < 3; i++) {
            String line = board[i];
            if (line.equals(Character.toString(sign).repeat(3))) {
                return true;
            }
            for (int j = 0; j < 3; j++) {
                if (board[j].charAt(i) != sign) {
                    break;
                }
                if (j == 2) {
                    return true;
                }
            }
            hasDiagonalFromLeft &= line.charAt(i) == sign;
            hasDiagonalFromRight &= line.charAt(2 - i) == sign;
        }
        return hasDiagonalFromLeft || hasDiagonalFromRight;
    }
}
