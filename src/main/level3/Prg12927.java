package main.level3;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/12927
// 야근 지수
class Prg12927 {
    public long solution(int n, int[] works) {
        final PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int work : works) {
            pq.offer(work);
        }

        for (int i = 1; i <= n; i++) {
            int work = pq.poll();
            if (work == 0) {
                break;
            }
            pq.offer(--work);
        }

        long answer = 0;
        while (!pq.isEmpty()) {
            answer += Math.pow(pq.poll(), 2);
        }
        return answer;
    }
}
