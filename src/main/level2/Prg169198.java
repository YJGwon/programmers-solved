package main.level2;

// 당구 연습
// https://school.programmers.co.kr/learn/courses/30/lessons/169198
class Prg169198 {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        //상, 하 , 좌, 우 대칭점
        final int[][] opposites = {
                {-startX, startY},
                {2 * m - startX, startY},
                {startX, -startY},
                {startX, 2 * n - startY}
        };

        final int[] answer = new int[balls.length];
        for (int i = 0; i < balls.length; i++) {
            final int targetX = balls[i][0];
            final int targetY = balls[i][1];
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < 4; j++) {
                if (j == 0 && startY == targetY && startX > targetX) {
                    continue;
                }
                if (j == 1 && startY == targetY && startX < targetX) {
                    continue;
                }
                if (j == 2 && startX == targetX && startY > targetY) {
                    continue;
                }
                if (j == 3 && startX == targetX && startY < targetY) {
                    continue;
                }
                double result = Math.pow(opposites[j][0] - targetX, 2) + Math.pow(opposites[j][1] - targetY, 2);
                min = Math.min(min, (int) result);
            }
            answer[i] = min;
        }
        return answer;
    }
}
