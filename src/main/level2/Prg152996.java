package main.level2;

import java.util.HashMap;
import java.util.Map;

// 시소 짝꿍
// https://school.programmers.co.kr/learn/courses/30/lessons/152996
class Prg152996 {
    private static final double[][] distances = {{2.0, 3.0}, {2.0, 4.0}, {3.0, 4.0}};

    public long solution(int[] weights) {
        Map<Double, Long> weightCount = new HashMap<>();
        for (int i = 0; i < weights.length; i++) {
            weightCount.put(weights[i] * 1.0, weightCount.getOrDefault(weights[i] * 1.0, 0L) + 1);
        }

        long answer = 0;
        for (Double weight : weightCount.keySet()) {
            long currentCount = weightCount.get(weight);
            answer += (currentCount - 1) * currentCount / 2;
            for (int i = 0; i < 3; i++) {
                Double pair = weight / distances[i][0] * distances[i][1];
                if (weightCount.containsKey(pair)) {
                    answer += currentCount * weightCount.get(pair);
                }
            }
        }
        return answer;
    }
}
