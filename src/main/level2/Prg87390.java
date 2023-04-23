package main.level2;

// n^2 배열 자르기
// https://school.programmers.co.kr/learn/courses/30/lessons/87390
class Prg87390 {
    public int[] solution(int n, long left, long right) {
        int[] answer = new int[(int)(right - left) + 1];
        for (int i = 0; i < answer.length; i++) {
            int col = (int)((left + i) % n) + 1;
            if (col == n) {
                answer[i] = n;
                continue;
            }
            int row = (int)((left + i) / n) + 1;
            answer[i] = Math.max(col, row);
        }
        return answer;
    }
}
