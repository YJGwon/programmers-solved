package main.level2;

import java.util.*;

// 괄호 회전하기
// https://school.programmers.co.kr/learn/courses/30/lessons/76502
class Prg76502 {
    // 회전해서 만들 수 있는 경우의 수 = 분리할 수 있는 올바른 괄호 문자열 원소의 개수

    private static final Map<Character, Character> OPEN = Map.of(
            '[', ']',
            '(', ')',
            '{', '}'
    );

    private static final Map<Character, Character> CLOSE = Map.of(
            ']', '[',
            ')', '(',
            '}', '{'
    );

    public int solution(String s) {
        Queue<Character> queue = new LinkedList<>();
        Stack<Character> stack = new Stack<>();

        int answer = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (OPEN.containsKey(c)) { // 여는 괄호
                stack.push(c);
                continue;
            }

            // 닫는 괄호
            if (stack.isEmpty()) { // 여는 괄호가 뒤에 있을 가능성 O
                queue.offer(c);
                answer = 0; // 이 앞에 나온 올바른 괄호 문자열은 모두 이 괄호안에 포함됨
                continue;
            }

            if (stack.peek() != CLOSE.get(c)) { // 올바른 괄호 문자열 아님
                return 0;
            }

            stack.pop();
            if (stack.isEmpty()) { // 분리할 수 있는 올바른 괄호 문자열
                answer++;
            }
        }

        while(!queue.isEmpty()) {
            char c = queue.poll();
            char pair = CLOSE.get(c);
            if (stack.isEmpty() || stack.peek() != pair) { // 올바른 괄호 문자열 x
                return 0;
            }

            stack.pop();
            if (stack.isEmpty()) { // 분리할 수 있는 올바른 괄호 문자열
                answer++;
            }
        }
        return answer;
    }
}
