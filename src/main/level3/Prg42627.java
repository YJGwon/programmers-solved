package main.level3;

import java.util.Comparator;
import java.util.PriorityQueue;

// 디스크 컨트롤러
// https://school.programmers.co.kr/learn/courses/30/lessons/42627
class Prg42627 {

    private static final Comparator<int[]> COMPARE_REQ_TIME = Comparator.comparingInt(arr -> arr[0]);
    private static final Comparator<int[]> COMPARE_DURATION = Comparator.comparingInt(arr -> arr[1]);

    public int solution(int[][] jobs) {
        PriorityQueue<int[]> reqQueue = new PriorityQueue<>(COMPARE_REQ_TIME.thenComparing(COMPARE_DURATION));
        PriorityQueue<int[]> waitingQueue = new PriorityQueue<>(COMPARE_DURATION);

        for (final int[] job : jobs) {
            reqQueue.offer(job);
        }

        int totalDuration = 0;
        int endsAt = 0;
        do {
            while (!reqQueue.isEmpty() && reqQueue.peek()[0] < endsAt) { // 작업 중 요청
                waitingQueue.offer(reqQueue.poll()); // 대기큐에 추가
            }

            // 대기큐 있으면 대기큐 작업, 없으면 요청 작업 수행
            int[] now = waitingQueue.isEmpty() ? reqQueue.poll() : waitingQueue.poll();
            endsAt = Math.max(endsAt, now[0]) + now[1];
            totalDuration += endsAt - now[0];

        } while (!reqQueue.isEmpty() || !waitingQueue.isEmpty());

        return totalDuration / jobs.length;
    }
}
