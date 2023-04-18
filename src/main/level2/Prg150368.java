package main.level2;

import java.util.PriorityQueue;
import java.util.Queue;

// 이모티콘 할인행사
// https://school.programmers.co.kr/learn/courses/30/lessons/150368
class Prg150368 {
    private static final int[] DISCOUNT_RATES = {10, 20, 30, 40};
    private Queue<Result> sortedQueue = new PriorityQueue<>();

    public int[] solution(int[][] users, int[] emoticons) {
        dfs(users, emoticons, new int[emoticons.length], 0);
        final Result result = sortedQueue.poll();
        return new int[]{result.joined, result.purchased};
    }

    private void dfs(final int[][] users, final int[] emoticons, final int[] discountedRates, final int depth) {
        if (depth == emoticons.length) {
            sortedQueue.offer(getResult(users, emoticons, discountedRates));
            return;
        }
        for (int i = 0; i < 4; i++) {
            discountedRates[depth] = DISCOUNT_RATES[i];
            dfs(users, emoticons, discountedRates, depth + 1);
        }
    }

    private Result getResult(final int[][] users, final int[] emoticons, final int[] discountedRates) {
        int joined = 0;
        int totalPurchased = 0;
        for (final int[] user : users) {
            int purchased = 0;
            for (int j = 0; j < emoticons.length; j++) {
                if (discountedRates[j] < user[0]) {
                    continue;
                }
                purchased += emoticons[j] / 100 * (100 - discountedRates[j]);
            }
            if (purchased >= user[1]) {
                joined++;
            } else {
                totalPurchased += purchased;
            }
        }
        return new Result(joined, totalPurchased);
    }

    private static class Result implements Comparable<Result> {
        int joined;
        int purchased;

        public Result(final int joined, final int purchased) {
            this.joined = joined;
            this.purchased = purchased;
        }

        @Override
        public int compareTo(final Result o) {
            if (o.joined != joined) {
                return o.joined - joined;
            }
            return o.purchased - purchased;
        }
    }
}
