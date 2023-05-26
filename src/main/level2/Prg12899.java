package main.level2;

// 124 나라의 숫자
// https://school.programmers.co.kr/learn/courses/30/lessons/12899
class Prg12899 {

    private static final int[] DIGITS = {4, 1, 2};
    private static final int DIVIDER = DIGITS.length;

    public String solution(int n) {
        return convert(n);
    }

    private String convert(int n) {
        final StringBuilder sb = new StringBuilder();
        while (n > 0) {
            final int remain = n % DIVIDER;
            sb.append(DIGITS[remain]);
            n /= DIVIDER;
            if (remain == 0) {
                n--;
            }
        }
        return sb.reverse().toString();
    }
}
