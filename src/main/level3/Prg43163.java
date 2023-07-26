package main.level3;

import java.util.ArrayDeque;
import java.util.Queue;

// 단어 변환
// https://school.programmers.co.kr/learn/courses/30/lessons/43163
class Prg43163 {

    public int solution(String begin, String target, String[] words) {

        boolean[] visited = new boolean[words.length];
        int answer = 0;

        Queue<Word> queue = new ArrayDeque<>();
        queue.offer(new Word(begin, 0));
        while (!queue.isEmpty()) {
            Word now = queue.poll();

            if (now.value.equals(target)) {
                answer = now.converted;
                break;
            }

            for (int i = 0; i < words.length; i++) {
                if (visited[i]) {
                    continue;
                }

                if (now.countDiff(words[i]) > 1) {
                    continue;
                }

                visited[i] = true;
                queue.offer(new Word(words[i], now.converted + 1));
            }
        }

        return answer;
    }

    private static class Word {
        String value;
        int converted;

        Word(String value, int converted) {
            this.value = value;
            this.converted = converted;
        }

        int countDiff(String otherValue) {
            int count = 0;
            for (int i = 0; i < value.length(); i++) {
                if (otherValue.charAt(i) != value.charAt(i)) {
                    count++;
                }
            }
            return count;
        }
    }
}
