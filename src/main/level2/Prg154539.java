package main.level2;

import java.util.Stack;

// 뒤에 있는 큰 수 찾기
// https://school.programmers.co.kr/learn/courses/30/lessons/154539
class Prg154539 {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = numbers.length - 1; i >= 0; i--) {
            int number = numbers[i];
            while (!stack.isEmpty() && stack.peek() <= number) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                answer[i] = -1;
            } else {
                answer[i] = stack.peek();
            }
            stack.push(number);
        }
        return answer;
    }
}
