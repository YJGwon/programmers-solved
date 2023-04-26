package main.level2;

import java.util.*;

// 교점에 별 만들기
// https://school.programmers.co.kr/learn/courses/30/lessons/87377
class Prg87377 {

    private static final char STAR = '*';
    private static final char BLANK = '.';

    public String[] solution(int[][] line) {
        long minX = Long.MAX_VALUE;
        long maxX = Long.MIN_VALUE;
        long minY = Long.MAX_VALUE;
        long maxY = Long.MIN_VALUE;

        final List<long[]> stars = new ArrayList<>();

        for (int i = 0; i < line.length; i++) {
            final long a = line[i][0];
            final long b = line[i][1];
            final long e = line[i][2];

            for (int j = i + 1; j < line.length; j++) {
                final long c = line[j][0];
                final long d = line[j][1];
                final long f = line[j][2];
                final long divisor = a * d - b * c;
                if (divisor == 0) { // 평행
                    continue;
                }

                final long xDividend = b * f - e * d;
                final long yDividend = e * c - a * f;
                if (xDividend % divisor != 0 || yDividend % divisor != 0) { // 정수아님
                    continue;
                }

                final long x = xDividend / divisor;
                final long y = yDividend / divisor;
                minX = Math.min(minX, x);
                maxX = Math.max(maxX, x);
                minY = Math.min(minY, y);
                maxY = Math.max(maxY, y);

                stars.add(new long[]{x, y});
            }
        }

        final int xSize = (int) (maxX - minX + 1);
        final int ySize = (int) (maxY - minY + 1);
        char[][] result = new char[ySize][xSize];
        for (char[] arr : result) {
            Arrays.fill(arr, BLANK);
        }
        for (long[] star : stars) {
            final int x = (int) (star[0] - minX);
            final int y = (int) (star[1] - minY);
            result[y][x] = STAR;
        }

        String[] answer = new String[ySize];
        for (int i = 0; i < ySize; i++) {
            answer[i] = String.valueOf(result[ySize - 1 - i]);
        }
        return answer;
    }
}
