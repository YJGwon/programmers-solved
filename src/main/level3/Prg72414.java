package main.level3;

import java.util.StringTokenizer;

// 광고 삽입
// https://school.programmers.co.kr/learn/courses/30/lessons/72414
class Prg72414 {
    public String solution(String play_time, String adv_time, String[] logs) {
        int[] count = new int[secondsFrom(play_time) + 1];
        for (String log : logs) {
            StringTokenizer st = new StringTokenizer(log, "-");
            int start = secondsFrom(st.nextToken());
            int end = secondsFrom(st.nextToken());
            for (int i = start; i < end; i++) {
                count[i]++;
            }
        }

        int adv = secondsFrom(adv_time);
        long sum = 0;
        for (int i = 0; i < adv; i++) {
            sum += count[i];
        }

        long max = sum;
        int answerTime = 0;
        for (int i = adv; i < count.length; i++) {
            sum -= count[i - adv];
            sum += count[i];
            if (max < sum) {
                max = sum;
                answerTime = i - adv + 1;
            }
        }

        return secondsToString(answerTime);
    }

    private int secondsFrom(String rawTime) {
        StringTokenizer st = new StringTokenizer(rawTime, ":");
        int hour = Integer.parseInt(st.nextToken());
        int minute = Integer.parseInt(st.nextToken());
        int second = Integer.parseInt(st.nextToken());
        return hour * 60 * 60 + minute * 60 + second;
    }

    private String secondsToString(int seconds) {
        StringBuilder sb = new StringBuilder();
        int divider = 60 * 60;
        for (int i = 0; i < 3; i++) {
            int num = seconds / divider;
            if (num < 10) {
                sb.append("0");
            }
            sb.append(num);
            if (i == 2) {
                continue;
            }
            seconds %= divider;
            divider /= 60;
            sb.append(":");
        }
        return sb.toString();
    }
}
