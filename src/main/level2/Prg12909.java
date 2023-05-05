package main.level2;

// 올바른 괄호
// https://school.programmers.co.kr/learn/courses/30/lessons/12909
class Prg12909 {
    boolean solution(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char now = s.charAt(i);
            if (now == '(') {
                count++;
            } else if (count == 0) {
                return false;
            } else {
                count--;
            }
        }
        return count == 0;
    }
}
