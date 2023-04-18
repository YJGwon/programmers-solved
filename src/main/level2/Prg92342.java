package main.level2;

// 양궁대회
// https://school.programmers.co.kr/learn/courses/30/lessons/92342
class Prg92342 {
    int[] apeachCount;
    int[] ryanCount;
    int maxScore;

    public int[] solution(int n, int[] info) {
        maxScore = 0;
        apeachCount = info;
        ryanCount = new int[]{-1};
        dfs(n, 0, new int[11]);
        return ryanCount;
    }

    private void dfs(int remain, int idx, int[] count) {
        if (remain == 0) {
            getScore(count);
            return;
        }

        if (idx == 10) {
            count[10] = remain;
            getScore(count);
            count[10] = 0;
            return;
        }

        int min = apeachCount[idx] + 1;
        if (remain >= min) {
            count[idx] = min;
            dfs(remain - min, idx + 1, count);
        }
        count[idx] = 0;
        dfs(remain, idx + 1, count);
    }

    private void getScore(int[] count) {
        int apeach = 0;
        int ryan = 0;
        for (int i = 0; i < count.length; i++) {
            if (apeachCount[i] == 0 && count[i] == 0) {
                continue;
            }
            if (apeachCount[i] < count[i]) {
                ryan += 10 - i;
            } else {
                apeach += 10 - i;
            }
        }
        if (apeach < ryan) {
            if (ryan - apeach > maxScore) {
                maxScore = ryan - apeach;
                ryanCount = count.clone();
            } else if (ryan - apeach == maxScore) {
                findAnswer(count);
            }
        }
    }

    private void findAnswer(int[] count) {
        for (int i = 10; i >= 0; i--) {
            if (ryanCount[i] > count[i]) {
                break;
            }
            if (ryanCount[i] < count[i]) {
                ryanCount = count.clone();
                break;
            }
        }
    }
}
