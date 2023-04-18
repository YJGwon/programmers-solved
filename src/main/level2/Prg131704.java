package main.level2;

import java.util.Stack;

// 택배상자
// https://school.programmers.co.kr/learn/courses/30/lessons/131704
class Prg131704 {
    public int solution(int[] order) {
        Stack<Integer> s = new Stack<>();
        int answer = 0;
        int current = 1;
        for (int i = 0; i < order.length; i++) {
            if (order[i] < current) {
                if (s.isEmpty() || order[i] != s.peek()) {
                    break;
                }
                s.pop();
            } else if (order[i] > current) {
                for (int j = current; j < order[i]; j++) {
                    s.push(j);
                }
                current = order[i] + 1;
            } else {
                current++;
            }
            answer++;
        }
        return answer;
    }
}
