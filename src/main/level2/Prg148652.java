package main.level2;

// 유사 칸토어 비트열
// https://school.programmers.co.kr/learn/courses/30/lessons/148652
class Prg148652 {
    public int solution(int n, long l, long r) {
        // n번째칸토어
        // [n-1번째 칸토어][n-1번째 칸토어]['0' * 5^n-1][n-1번째 칸토어][n-1번째 칸토어]
        // length = 5^n
        // 1개수 = 4^n-1
        // l, r 은 1부터 시작하는 index, 둘다 포함
        return count(n, l - 1, r - 1);
    }

    private int count(int n, long l, long r) {
        if (n == 0) {
            return 1;
        }
        long prevLen = (long) Math.pow(5, n - 1);
        long prevCount = (long) Math.pow(4, n - 1);

        int sum = 0;
        for (int i = 0; i < 5; i++) {
            if (i == 2) {
                continue; //0인 부분
            }

            long partStartsAt = i * prevLen;
            long partEndsAt = (i + 1) * prevLen - 1;
            if (r < partStartsAt || l > partEndsAt) {
                //범위를 완전히 벗어남
                continue;
            }
            if (l <= partStartsAt && partEndsAt <= r) {
                //범위를 완전히 포함
                sum += prevCount;
            } else {
                // 범위 일부만 포함
                long nextL = Math.max(l - partStartsAt, 0);
                long nextR = Math.min(r - partStartsAt, prevLen - 1);
                sum += count(n - 1, nextL, nextR);
            }
        }
        return sum;
    }
}
