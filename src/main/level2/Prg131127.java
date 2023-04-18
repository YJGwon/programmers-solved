package main.level2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 할인 행사
// https://school.programmers.co.kr/learn/courses/30/lessons/131127
class Prg131127 {
    public int solution(String[] want, int[] number, String[] discount) {
        Map<String, Integer> items = new HashMap<>();
        for (int i = 0; i < want.length; i++) {
            items.put(want[i], i);
        }

        int answer = 0;
        int[] cart = new int[want.length];
        for (int i = 0; i < discount.length; i++) {
            if (items.containsKey(discount[i])) {
                // 오늘 원하는 상품 할인 -> 담음
                cart[items.get(discount[i])]++;
            }

            if (i > 9) {
                // 처음 10일 이후 -> 범위 벗어난 상품 빼기
                int outdated = i - 10;
                if (items.containsKey(discount[outdated])) {
                    cart[items.get(discount[outdated])]--;
                }
            }

            if (Arrays.equals(cart, number)) {
                answer++;
            }
        }

        return answer;
    }
}
