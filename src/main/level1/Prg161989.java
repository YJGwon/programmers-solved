package main.level1;

// 덧칠하기
// https://school.programmers.co.kr/learn/courses/30/lessons/161989
class Prg161989 {
    public int solution(int n, int m, int[] section) {
        int painted = 0;
        int answer = 0;
        for (int i = 0; i < section.length; i++) {
            int current = section[i];
            if (current > painted) {
                answer++;
                painted = current + m - 1;
            }
        }

        return answer;
    }
}
