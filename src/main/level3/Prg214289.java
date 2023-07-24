package main.level3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 에어컨
// https://school.programmers.co.kr/learn/courses/30/lessons/214289
class Prg214289 {

    private int outside;
    private int minT;
    private int maxT;
    private int coastDiff;
    private int coastSame;
    private int[] onboard;

    // minCoast.get(시간).get(실내온도) = 최소전력
    private List<Map<Integer, Integer>> minCoast;

    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        outside = temperature;
        minT = t1;
        maxT = t2;
        coastDiff = a;
        coastSame = b;
        this.onboard = onboard;

        minCoast = new ArrayList<>();
        for (int i = 0; i < onboard.length; i++) {
            minCoast.add(new HashMap<>());
        }

        return dp(0, temperature);
    }

    private int dp(int time, int inside) {
        if (onboard[time] == 1 && (inside < minT || inside > maxT)) {
            // 적정 온도 지키지 못해서 실패
            minCoast.get(time).put(inside, -1);
            return -1;
        }

        if (time == onboard.length - 1) {
            // 시간 끝
            return 0;
        }

        if (minCoast.get(time).containsKey(inside)) {
            return minCoast.get(time).get(inside);
        }

        int answer = Integer.MAX_VALUE;

        // 온도 1도 낮추는 경우
        int coastDown = inside > outside ? 0 : coastDiff;
        int coastAfterDown = dp(time + 1, inside - 1);
        if (coastAfterDown != -1) {
            answer = Math.min(coastDown + coastAfterDown, answer);
        }

        // 온도 1도 올리는 경우
        int coastUp = inside < outside ? 0 : coastDiff;
        int coastAfterUp = dp(time + 1, inside + 1);
        if (coastAfterUp != -1) {
            answer = Math.min(coastUp + coastAfterUp, answer);
        }

        // 온도 유지하는 경우
        int coastMaintain = inside == outside ? 0 : coastSame;
        int coastAfterMaintain = dp(time + 1, inside);
        if (coastAfterMaintain != -1) {
            answer = Math.min(coastMaintain + coastAfterMaintain, answer);
        }

        if (answer == Integer.MAX_VALUE) {
            // 어떻게 해도 적정온도 못지켜서 실패
            minCoast.get(time).put(inside, -1);
            return -1;
        }

        minCoast.get(time).put(inside, answer);
        return answer;
    }
}
