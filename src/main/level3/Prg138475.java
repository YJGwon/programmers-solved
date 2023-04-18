package main.level3;

// 억억단을 외우자
// https://school.programmers.co.kr/learn/courses/30/lessons/138475
class Prg138475 {
    public int[] solution(int e, int[] starts) {
        int[] counts = new int[e + 1];
        for (int i = e; i > 0; i--) {
            for (int j = 1; j <= e / i; j++) {
                counts[i * j]++;
            }
        }

        int[] cache = new int[e + 1];
        int maxCount = 0;
        int answer = 0;
        for (int i = e; i > 0; i--) {
            if (counts[i] >= maxCount) {
                answer = i;
                maxCount = counts[i];
            }
            cache[i] = answer;
        }

        int[] answers = new int[starts.length];
        for (int i = 0; i < starts.length; i++) {
            answers[i] = cache[starts[i]];
        }
        return answers;
    }
}
