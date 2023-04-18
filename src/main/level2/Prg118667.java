package main.level2;

import java.util.LinkedList;
import java.util.Queue;

// 두 큐 합 같게 만들기
// https://school.programmers.co.kr/learn/courses/30/lessons/118667
class Prg118667 {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        long sum1 = 0;
        long sum2 = 0;
        for (int i = 0; i < queue1.length; i++) {
            sum1 += queue1[i];
            q1.offer(queue1[i]);
            sum2 += queue2[i];
            q2.offer(queue2[i]);
        }

        int max = queue1.length * 3;
        int answer = 0;
        while (!q1.isEmpty() && !q2.isEmpty()) {
            if (answer >= max) {
                break;
            }
            if (sum1 > sum2) {
                int n = q1.poll();
                q2.offer(n);
                sum1 -= n;
                sum2 += n;
                answer++;
            } else if (sum1 < sum2) {
                int n = q2.poll();
                q1.offer(n);
                sum1 += n;
                sum2 -= n;
                answer++;
            } else {
                return answer;
            }
        }

        return -1;
    }
}
