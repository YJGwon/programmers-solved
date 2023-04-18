package main.level2;

// 점 찍기
// https://school.programmers.co.kr/learn/courses/30/lessons/140107
class Prg140107 {
    public long solution(int k, int d) {
        long answer = 0;
        int maxA = d / k;
        for (int i = 0; i <= maxA; i++) {
            int maxY = (int) Math.sqrt(Math.pow(d, 2) - Math.pow(k * i, 2));
            int maxB = maxY / k;
            answer += maxB + 1;
        }
        return answer;
    }
}
