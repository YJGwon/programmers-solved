package main.level2;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

// 디펜스 게임
// https://school.programmers.co.kr/learn/courses/30/lessons/142085
class Prg142085 {
    public int solution(int n, int k, int[] enemy) {
        Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int kill = 0;
        int skill = k;
        int answer = 0;
        for (int i = 0; i < enemy.length; i++) {
            kill += enemy[i];
            pq.offer(enemy[i]);
            if (kill > n) {
                if (skill == 0) {
                    break;
                }
                kill -= pq.poll();
                skill--;
            }
            answer++;
        }
        return answer;
    }
}
