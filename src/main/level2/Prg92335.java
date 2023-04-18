package main.level2;

// k진수에서 소수 개수 구하기
// https://school.programmers.co.kr/learn/courses/30/lessons/92335
class Prg92335 {
    public int solution(int n, int k) {
        return countPrime(convert(n, k));
    }

    private String convert(int n, int k) {
        int quotient = n;
        StringBuilder result = new StringBuilder();
        while (quotient > 0) {
            result.append(quotient % k);
            quotient /= k;
        }
        return result.reverse().toString();
    }

    private int countPrime(String number) {
        int count = 0;
        StringBuilder n = new StringBuilder();
        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) != '0') {
                n.append(number.charAt(i));
            } else if (n.length() != 0) {
                if (isPrime(n.toString())) {
                    count++;
                }
                n = new StringBuilder();
            }
        }
        if (n.length() != 0) {
            if (isPrime(n.toString())) {
                count++;
            }
        }
        return count;
    }

    private boolean isPrime(String number) {
        long n = Long.parseLong(number);
        if (n < 2) {
            return false;
        }
        if (n == 2) {
            return true;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
