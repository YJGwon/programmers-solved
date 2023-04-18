package main.level2;

import java.util.HashMap;
import java.util.Map;

// 광물 캐기
// https://school.programmers.co.kr/learn/courses/30/lessons/172927
class Prg172927 {
    private static final int[][] ENERGY = {{1, 1, 1}, {5, 1, 1}, {25, 5, 1}}; //[도구][광물]
    private static final Map<String, Integer> MINERAL_IDX = new HashMap<>();

    static {
        MINERAL_IDX.put("diamond", 0);
        MINERAL_IDX.put("iron", 1);
        MINERAL_IDX.put("stone", 2);
    }

    private int totalPicks;
    private int minEnerge;

    public int solution(int[] picks, String[] minerals) {
        totalPicks = 0;
        minEnerge = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            totalPicks += picks[i];
        }

        int[] usingPicks = new int[totalPicks];
        dfs(picks, minerals, usingPicks, 0);
        return minEnerge;
    }

    private void dfs(int[] picks, String[] minerals, int[] usingPicks, int count) {
        if (count == totalPicks) {
            minEnerge = Math.min(mine(minerals, usingPicks), minEnerge);
        }
        for (int i = 0; i < 3; i++) {
            if (picks[i] > 0) {
                usingPicks[count] = i;
                picks[i]--;
                dfs(picks, minerals, usingPicks, count + 1);
                picks[i]++;
            }
        }
    }

    private int mine(String[] minerals, int[] usingPicks) {
        int totalEnerge = 0;
        int usingPick = 0;
        for (int i = 0; i < minerals.length; i += 5) {
            if (usingPick >= usingPicks.length) {
                break;
            }
            for (int j = i; j < i + 5; j++) {
                if (j >= minerals.length) {
                    break;
                }
                int mineral = MINERAL_IDX.get(minerals[j]);
                totalEnerge += ENERGY[usingPicks[usingPick]][mineral];
            }
            usingPick++;
        }
        return totalEnerge;
    }
}
