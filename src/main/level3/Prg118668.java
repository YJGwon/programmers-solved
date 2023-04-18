package main.level3;

import java.util.Arrays;

// 코딩 테스트 공부
// https://school.programmers.co.kr/learn/courses/30/lessons/118668
class Prg118668 {
    private static final int MAX = 300;

    private int maxAlp = 0;
    private int maxCop = 0;
    private int[][] cache;

    public int solution(int alp, int cop, int[][] problems) {
        for (int i = 0; i < problems.length; i++) {
            maxAlp = Math.max(maxAlp, problems[i][0]);
            maxCop = Math.max(maxCop, problems[i][1]);
        }

        cache = new int[maxAlp + 1][maxCop + 1]; //[알고력][코딩력] = 드는 시간
        for (int i = 0; i < cache.length; i++) {
            Arrays.fill(cache[i], MAX);
        }
        return dp(alp, cop, problems);
    }

    private int dp(int alp, int cop, int[][] problems) {
        if (alp >= maxAlp && cop >= maxCop) { // 바로 문제 해결 가능
            return 0;
        }

        // 초과 범위 처리
        if (alp > maxAlp) {
            return dp(maxAlp, cop, problems);
        }
        if (cop > maxCop) {
            return dp(alp, maxCop, problems);
        }

        if (cache[alp][cop] < MAX) { // 미리 구해둔 값 존재
            return cache[alp][cop];
        }

        cache[alp][cop] = maxAlp - alp + maxCop - cop; // 최대 cost(순수 공부로만)
        cache[alp][cop] = Math.min(cache[alp][cop], 1 + dp(alp + 1, cop, problems)); //알고리즘 공부
        cache[alp][cop] = Math.min(cache[alp][cop], 1 + dp(alp, cop + 1, problems)); //코딩 공부
        for (int i = 0; i < problems.length; i++) {
            // [alp_req, cop_req, alp_rwd, cop_rwd, cost]
            int alpReq = problems[i][0];
            int copReq = problems[i][1];
            if (alp < alpReq || cop < copReq) { // 풀 수 없는 문제
                continue;
            }
            // 문제 풀기
            int alpRwd = problems[i][2];
            int copRwd = problems[i][3];
            int cost = problems[i][4];
            cache[alp][cop] = Math.min(cache[alp][cop], cost + dp(alp + alpRwd, cop + copRwd, problems));
        }
        return cache[alp][cop];
    }
}
