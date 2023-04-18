package main.level1;

import java.util.ArrayList;
import java.util.List;

// 로또의 최고 순위와 최저 순위
// https://school.programmers.co.kr/learn/courses/30/lessons/77484
class Prg77484 {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] winsAndZeros = countWinsAndZeros(lottos, convertToList(win_nums));
        int[] answer = new int[2];
        answer[0] = getPrize(winsAndZeros[0] + winsAndZeros[1]);
        answer[1] = getPrize(winsAndZeros[0]);
        return answer;
    }

    private int[] countWinsAndZeros(int[] lottos, List<Integer> win_nums) {
        int winCount = 0;
        int zeroCount = 0;
        for (int i = 0; i < 6; i++) {
            if (lottos[i] == 0) {
                zeroCount++;
            } else if (win_nums.contains(lottos[i])) {
                winCount++;
            }
        }
        return new int[]{winCount, zeroCount};
    }

    private int getPrize(int winCount) {
        switch (winCount) {
            case 6:
                return 1;
            case 5:
                return 2;
            case 4:
                return 3;
            case 3:
                return 4;
            case 2:
                return 5;
            default:
                return 6;
        }
    }

    private List<Integer> convertToList(int[] array) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }
}
