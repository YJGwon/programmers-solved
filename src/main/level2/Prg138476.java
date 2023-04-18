package main.level2;

import java.util.*;

// 귤 고르기
// https://school.programmers.co.kr/learn/courses/30/lessons/138476
class Prg138476 {
    public int solution(int k, int[] tangerine) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int i = 0; i < tangerine.length; i++) {
            counts.put(tangerine[i],
                    counts.getOrDefault(tangerine[i], 0) + 1
            );
        }

        int remove = tangerine.length - k;
        PriorityQueue<Integer> pq = new PriorityQueue<>(counts.values());
        while (remove >= pq.peek()) {
            remove -= pq.poll();
        }
        return pq.size();
    }
}
