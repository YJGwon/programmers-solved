package main.level3;

import java.util.Arrays;

class Prg70130 {

    public int solution(int[] a) {
        int[] count = new int[a.length];
        int[] lastIndex = new int[a.length];
        Arrays.fill(lastIndex, -1);

        int maxCount = 0;
        for (int i = 0; i < a.length; i++) {
            int now = a[i];
            if (lastIndex[now] < i - 1 && a[i - 1] != now) {
                count[now]++;
                lastIndex[now] = i;
            } else if (i != a.length - 1 && a[i + 1] != now) {
                count[now]++;
                lastIndex[now] = i + 1;
            }
            maxCount = Math.max(maxCount, count[now]);
        }
        return maxCount * 2;
    }
}
