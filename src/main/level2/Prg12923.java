package main.level2;

// 숫자 블록
// https://school.programmers.co.kr/learn/courses/30/lessons/12923
class Prg12923 {

    private static final int MAX_BLOCK = 10_000_000;

    public int[] solution(long begin, long end) {
        final int size = (int) (end - begin) + 1;
        final int[] answer = new int[size];
        for (int i = 0; i < size; i++) {
            answer[i] = getMaxDivisor(begin + i);
        }
        return answer;
    }

    private int getMaxDivisor(long number) {
        if (number == 1) {
            return 0;
        }

        int maxDivisor = 0;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i != 0) {
                continue;
            }

            if (number / i <= MAX_BLOCK) {
                return (int) (number / i);
            }
            maxDivisor = i;
        }

        if (maxDivisor != 0) {
            return maxDivisor;
        }

        return 1;
    }
}
