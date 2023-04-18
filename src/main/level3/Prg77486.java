package main.level3;

import java.util.HashMap;
import java.util.Map;

// 다단계 칫솔 판매
// https://school.programmers.co.kr/learn/courses/30/lessons/77486
class Prg77486 {
    int[] answer;

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        Map<String, Integer> indexes = new HashMap<>();
        for (int i = 0; i < enroll.length; i++) {
            indexes.put(enroll[i], i);
        }

        answer = new int[enroll.length];
        for (int i = 0; i < seller.length; i++) {
            getIncome(seller[i], amount[i] * 100, indexes, referral);
        }
        return answer;
    }

    private void getIncome(String seller, int amount, Map<String, Integer> indexes, String[] refferal) {
        if (seller.equals("-")) {
            return;
        }
        int index = indexes.get(seller);
        int distribution = amount / 10;
        if (distribution == 0) {
            answer[index] += amount;
            return;
        }
        answer[index] += amount - distribution;
        getIncome(refferal[index], distribution, indexes, refferal);
    }
}
