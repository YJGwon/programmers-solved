package main.level3;

import java.util.Arrays;

// 2차원 동전 뒤집기
// https://school.programmers.co.kr/learn/courses/30/lessons/131703
class Prg131703 {
    public int solution(int[][] beginning, int[][] target) {
        // 각 가로, 세로에 대해서 2번 이상 뒤집는 것은 의미가 없음 (안뒤집거나, 뒤집거나)
        // 한 번 가로로 뒤집은 줄이 정답과 같아지려면 세로로 뒤집는 것 밖에 남지 않는다
        // 첫 줄을 뒤집을지 말지 결정하면 나머지는 종속적
        // 세로 뒤집어 본 후, 가로로 뒤집어 볼 수 있는 애들만 뒤집으면 됨 -> 정답 안나오면 방법 없음

        int[][] cp = copyOf(beginning);

        //첫째 줄 안 뒤집는 경우
        int count = flip(cp, target);

        if (!Arrays.deepEquals(cp, target)) {
            // 방법 없음
            return -1;
        }

        //첫째 줄 뒤집는 경우
        flipRow(beginning, 0);
        count = Math.min(count, flip(beginning, target) + 1);
        return count;
    }

    private int flip(int[][] beginning, int[][] target) {
        int count = 0;
        // 세로 뒤집기
        for (int y = 0; y < beginning[0].length; y++) {
            if (beginning[0][y] == target[0][y]) {
                continue;
            }
            count++;
            flipCol(beginning, y);
        }
        // 가로 뒤집기
        for (int x = 0; x < beginning.length; x++) {
            if (beginning[x][0] == target[x][0]) {
                continue;
            }
            count++;
            flipRow(beginning, x);
        }
        return count;
    }

    private void flipCol(int[][] beginning, int idx) {
        for (int x = 0; x < beginning.length; x++) {
            flip(beginning, x, idx);
        }
    }

    private void flipRow(int[][] beginning, int idx) {
        for (int y = 0; y < beginning[0].length; y++) {
            flip(beginning, idx, y);
        }
    }

    private void flip(int[][] beginning, int x, int y) {
        beginning[x][y] = (beginning[x][y] + 1) % 2;
    }

    private int[][] copyOf(int[][] original) {
        int[][] copy = new int[original.length][original[0].length];
        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i].clone();
        }
        return copy;
    }
}
