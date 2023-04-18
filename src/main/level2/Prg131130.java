package main.level2;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

// 혼자 놀기의 달인
// https://school.programmers.co.kr/learn/courses/30/lessons/131130
class Prg131130 {
    boolean[] visited;

    public int solution(int[] cards) {
        visited = new boolean[cards.length];
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < cards.length; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            pq.add(open(cards[i], cards));
        }

        if (pq.size() < 2) {
            return 0;
        }
        return pq.poll() * pq.poll();
    }

    private int open(int card, int[] cards) {
        int score = 0;
        Queue<Integer> q = new LinkedList<>();
        q.offer(card);
        while (!q.isEmpty()) {
            int current = q.poll();
            score++;

            int i = current - 1;
            if (!visited[i]) {
                visited[i] = true;
                q.offer(cards[i]);
            }
        }
        return score;
    }
}

