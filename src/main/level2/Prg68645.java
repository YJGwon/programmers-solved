package main.level2;

import java.util.*;

// 삼각 달팽이
// https://school.programmers.co.kr/learn/courses/30/lessons/68645
class Prg68645 {
    public int[] solution(int n) {
        final int maxNumber = (1 + n) * n / 2;

        List<int[]> triangle = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            triangle.add(new int[i]);
        }

        int minX = 0;
        int maxX = n - 1;
        int minY = 0;
        int maxY = n - 1;

        int number = 0;
        while (number < maxNumber) {
            for (int y = minY; y <= maxY; y++) {
                triangle.get(y)[minX] = ++number;
            }
            minX++;
            minY++;

            int[] bottom = triangle.get(maxY);
            for (int x = minX; x <= maxX; x++) {
                bottom[x] = ++number;
            }
            maxX--;
            maxY--;

            for (int i = 0; i <= maxY - minY; i++) {
                triangle.get(maxY - i)[maxX - i] = ++number;
            }
            minY++;
            maxX--;
        }

        int[] answer = new int[maxNumber];
        int i = 0;
        for (int[] row : triangle) {
            for (final int j : row) {
                answer[i++] = j;
            }
        }
        return answer;
    }
}
