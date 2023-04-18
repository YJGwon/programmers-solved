package main.level2;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

// 테이블 해시 함수
// https://school.programmers.co.kr/learn/courses/30/lessons/147354
class Prg147354 {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                compareCol(col).thenComparing(comparePkReversed())
        );

        Collections.addAll(pq, data);

        int answer = 0;
        for (int idx = 0; idx < row_end; idx++) {
            int[] current = pq.poll();
            int i = idx + 1;
            if (i >= row_begin) {
                answer ^= getS(current, i);
            }
        }
        return answer;
    }

    private int getS(int[] ints, int i) {
        int s = 0;
        for (int idx = 0; idx < ints.length; idx++) {
            s += ints[idx] % i;
        }
        return s;
    }

    private Comparator<int[]> compareCol(int col) {
        return Comparator.comparingInt(
                it -> ((int[]) it)[col - 1]
        );
    }

    private Comparator<Object> comparePkReversed() {
        return Comparator.comparingInt(it -> {
            int[] ints = (int[]) it;
            return ints[0];
        }).reversed();
    }
}
