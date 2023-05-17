package main.level2;

import java.util.ArrayList;
import java.util.List;

// 하노이의 탑
// https://school.programmers.co.kr/learn/courses/30/lessons/12946
class Prg12946 {
    // 가장 최소로 옮기려면 1번의 가장 아래 판이 바로 3번으로 옮겨져야 함
    // 이를 위해서는 2번에 n - 1개의 판을 옮겨야 함
    // 1 -> 2번으로 n - 1개의 판을 최소로 옮기는 방법도 같은 방식으로 구할 수 있다

    // -> 일반화
    // 세 기둥 a, b, c가 있을 때
    // a기둥에서 c기둥으로 최소로 옮기는 방법
    // a -> b n-1개
    // a -> c n번째 판
    // b -> c n-1개

    private List<int[]> history;

    public int[][] solution(int n) {
        history = new ArrayList<>();
        move(n, 1, 3, 2);

        return history.toArray(new int[history.size()][2]);
    }

    private void move(final int n, final int start, final int end, final int stopover) {
        if (n == 1) { // start에 1개가 있을 때 바로 옮길 수 있음
            history.add(new int[]{start, end});
            return;
        }

        move(n - 1, start, stopover, end); // a -> b n-1개
        history.add(new int[]{start, end}); // a -> c n번째 판
        move(n - 1, stopover, end, start); // b -> c n-1개
    }
}
