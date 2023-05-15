package main.level3;

// 최고의 집합
// https://school.programmers.co.kr/learn/courses/30/lessons/12938
class Prg12938 {
    public int[] solution(int n, int s) {
        if (s < n) {
            return new int[]{-1};
        }

        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            answer[i] = s / (n - i);
            s -= answer[i];
        }
        return answer;
    }
}
