package main.level2;

// 마법의 엘리베이터
// https://school.programmers.co.kr/learn/courses/30/lessons/148653
class Prg148653 {
    public int solution(int storey) {
        int answer = 0;
        while (storey > 0) {
            int mod = storey % 10;
            if (mod > 5 || (mod == 5 && storey % 100 > 50)) {
                answer += 10 - mod;
                storey += 10;
            } else {
                answer += mod;
            }
            storey -= mod;
            storey /= 10;
        }
        return answer;
    }
}
