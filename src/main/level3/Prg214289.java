package main.level3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 에어컨
// https://school.programmers.co.kr/learn/courses/30/lessons/214289
class Prg214289 {

    private int min;
    private int max;

    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        min = t1;
        max = t2;

        List<Map<Integer, Integer>> mem = new ArrayList<>(); // get(a).get(b) = a 시점에 b 온도를 만드는 최소비용
        mem.add(Map.of(temperature, 0));

        for (int i = 1; i < onboard.length; i++) {
            Map<Integer, Integer> now = new HashMap<>();

            Map<Integer, Integer> prev = mem.get(i - 1);
            for (int prevTemperature : prev.keySet()) {
                int prevCoast = prev.get(prevTemperature);

                if (onboard[i] != 1 || isInRange(prevTemperature - 1)) {
                    // 온도 1도 낮추기
                    int coast = temperature < prevTemperature ? prevCoast : prevCoast + a;
                    updateMinCoast(prevTemperature - 1, coast, now);
                }

                if (onboard[i] != 1 || isInRange(prevTemperature + 1)) {
                    // 온도 1도 높이기
                    int coast = temperature > prevTemperature ? prevCoast : prevCoast + a;
                    updateMinCoast(prevTemperature + 1, coast, now);
                }

                if (onboard[i] != 1 || isInRange(prevTemperature)) {
                    // 온도 유지
                    int coast = temperature == prevTemperature ? prevCoast : prevCoast + b;
                    updateMinCoast(prevTemperature, coast, now);
                }
            }
            mem.add(now);
        }

        return mem.get(onboard.length - 1).values()
                .stream()
                .mapToInt(i -> i)
                .min()
                .getAsInt();
    }

    private boolean isInRange(int temperature) {
        return min <= temperature && temperature <= max;
    }

    private void updateMinCoast(int temperature, int coast, Map<Integer, Integer> coasts) {
        int min = Math.min(coast, coasts.getOrDefault(temperature, Integer.MAX_VALUE));
        coasts.put(temperature, min);
    }
}
