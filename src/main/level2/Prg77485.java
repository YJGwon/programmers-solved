package main.level2;

// 행렬 테두리 회전하기
// https://school.programmers.co.kr/learn/courses/30/lessons/77485
class Prg77485 {
    private int[][] sequence;

    public int[] solution(int rows, int columns, int[][] queries) {
        sequence = new int[rows][columns];
        int element = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                sequence[i][j] = element;
                element++;
            }
        }

        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            answer[i] = spin(queries[i]);
        }
        return answer;
    }

    private int spin(int[] query) {
        for (int i = 0; i < 4; i++) {
            query[i] = query[i] - 1;
        }

        int x = query[0];
        int y = query[1];
        int before = sequence[x + 1][y];
        int min = 10001;
        while (true) {
            int temp = sequence[x][y];
            min = Math.min(min, temp);
            sequence[x][y] = before;
            before = temp;
            if (x == query[0] && y < query[3]) {
                y++;
            } else if (y == query[3] && x < query[2]) {
                x++;
            } else if (x == query[2] && y > query[1]) {
                y--;
            } else if (y == query[1] && x > query[0]) {
                x--;
            }
            if (x == query[0] && y == query[1]) {
                break;
            }
        }
        return min;
    }
}
