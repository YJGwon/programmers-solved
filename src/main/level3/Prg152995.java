package main.level3;

import java.util.Comparator;
import java.util.PriorityQueue;

// 인사고과
// https://school.programmers.co.kr/learn/courses/30/lessons/152995
class Prg152995 {
    public int solution(int[][] scores) {
        PriorityQueue<Score> queue = new PriorityQueue<>();
        Score wanho = new Score(scores[0][0], scores[0][1]);
        queue.offer(wanho);
        for (int i = 1; i < scores.length; i++) {
            if (scores[i][0] > wanho.attitude && scores[i][1] > wanho.coworker) {
                return -1;
            }
            queue.offer(new Score(scores[i][0], scores[i][1]));
        }
        return getRank(queue, wanho);
    }

    private int getRank(PriorityQueue<Score> scores, Score score) {
        int rank = 1;
        int maxCoworker = 0;
        while (!scores.isEmpty()) {
            int currentAttitude = scores.peek().attitude;
            int currentMaxCoworker = scores.peek().coworker;
            while (!scores.isEmpty() && scores.peek().attitude == currentAttitude) {
                Score current = scores.poll();
                if (current.coworker >= maxCoworker && current.sum > score.sum) {
                    rank++;
                }
            }
            maxCoworker = Math.max(maxCoworker, currentMaxCoworker);
        }
        return rank;
    }

    private static class Score implements Comparable<Score> {
        int attitude;
        int coworker;
        int sum;

        Score(int attitude, int coworker) {
            this.attitude = attitude;
            this.coworker = coworker;
            this.sum = attitude + coworker;
        }

        @Override
        public int compareTo(Score o) {
            return Comparator.comparingInt(
                    (it) -> {
                        Score s = (Score) it;
                        return s.attitude;
                    }
            ).thenComparingInt(
                    (it) -> {
                        Score s = (Score) it;
                        return s.coworker;
                    }
            ).compare(o, this);
        }
    }
}
