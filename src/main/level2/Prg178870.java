package main.level2;

// 연속된 부분 수열의 합
// https://school.programmers.co.kr/learn/courses/30/lessons/178870
class Prg178870 {
    public int[] solution(int[] sequence, int k) {
        int left = sequence.length - 1;
        int right = left;

        int sum = sequence[right];
        while (sum != k) {
            if (sum > k) {
                sum -= sequence[right];
                right--;
            }
            if (sum < k) {
                left--;
                sum += sequence[left];
            }
        }

        while (left > 0 && sequence[right] == sequence[left - 1]) {
            right--;
            left--;
        }

        return new int[]{left, right};
    }
}
