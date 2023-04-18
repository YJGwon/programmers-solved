package main.level2;

// 두 원 사이의 정수 쌍
// https://school.programmers.co.kr/learn/courses/30/lessons/181187
class Prg181187 {
    public long solution(int r1, int r2) {
        long dotsInQuarter = 0;

        double pow1 = Math.pow(r1, 2);
        double pow2 = Math.pow(r2, 2);

        for (int i = 0; i <= r2; i++) {
            long min = (long) Math.ceil(Math.sqrt(pow1 - Math.pow(i, 2)));
            long max = (long) Math.sqrt(pow2 - Math.pow(i, 2));
            dotsInQuarter += max - min + 1;
        }

        dotsInQuarter -= r2 - r1 + 1;
        return dotsInQuarter * 4;
    }
}
