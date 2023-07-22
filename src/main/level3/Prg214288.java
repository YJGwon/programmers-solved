package main.level3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

// 상담원 인원
// https://school.programmers.co.kr/learn/courses/30/lessons/214288
class Prg214288 {

    private int[][] reqs;
    private int answer;

    public int solution(int k, int n, int[][] reqs) {
        this.reqs = reqs;

        int[] mentors = new int[k + 1];
        Arrays.fill(mentors, 1);

        answer = Integer.MAX_VALUE;
        dfs(1, mentors, n - k);
        return answer;
    }

    private void dfs(int depth, int[] mentors, int remainingMentors) {
        if (depth == mentors.length - 1) {
            mentors[depth] += remainingMentors;
            answer = Math.min(getTotalWaiting(mentors), answer);
            mentors[depth] -= remainingMentors;
            return;
        }

        for (int i = 0; i <= remainingMentors; i++) {
            mentors[depth] += i;
            dfs(depth + 1, mentors, remainingMentors - i);
            mentors[depth] -= i;
        }
    }

    private int getTotalWaiting(int[] mentors) {
        List<PriorityQueue<Integer>> mentorsEndTimes = new ArrayList<>();
        for (int mentor : mentors) {
            PriorityQueue<Integer> mentorEndTimes = new PriorityQueue<>();
            for (int j = 0; j < mentor; j++) {
                mentorEndTimes.offer(0);
            }
            mentorsEndTimes.add(mentorEndTimes);
        }

        int totalWaiting = 0;
        for (int[] req : reqs) {
            PriorityQueue<Integer> mentorEndTimes = mentorsEndTimes.get(req[2]);
            int mentorEndsAt = mentorEndTimes.poll();
            if (mentorEndsAt <= req[0]) {
                mentorEndTimes.offer(req[0] + req[1]);
            } else {
                totalWaiting += mentorEndsAt - req[0];
                mentorEndTimes.offer(mentorEndsAt + req[1]);
            }
        }
        return totalWaiting;
    }
}
