package main.level2;

// 숫자 카드 나누기
// https://school.programmers.co.kr/learn/courses/30/lessons/135807
class Prg135807 {
    public int solution(int[] arrayA, int[] arrayB) {
        int gcdA = gcd(arrayA);
        if (isDivisor(gcdA, arrayB)) {
            gcdA = 0;
        }
        int gcdB = gcd(arrayB);
        if (isDivisor(gcdB, arrayA)) {
            gcdB = 0;
        }
        return Math.max(gcdA, gcdB);
    }

    private int gcd(int[] array) {
        int gcd = array[0];
        for (int i = 1; i < array.length; i++) {
            gcd = gcd(gcd, array[i]);
        }
        return gcd;
    }

    private boolean isDivisor(int a, int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] % a == 0) {
                return true;
            }
        }
        return false;
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
