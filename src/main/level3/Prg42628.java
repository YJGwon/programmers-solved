package main.level3;

import java.util.Collections;
import java.util.PriorityQueue;

// 이중우선순위큐
// https://school.programmers.co.kr/learn/courses/30/lessons/42628
class Prg42628 {

    private static final char COMMAND_INSERT = 'I';
    private static final char COMMAND_DELETE = 'D';

    private static final int FLAG_MAX = 1;
    private static final int FLAG_MIN = -1;

    public int[] solution(String[] operations) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (String operation : operations) {
            char command = operation.charAt(0);
            int value = Integer.parseInt(operation.substring(2));

            // 삽입 연산
            if (COMMAND_INSERT == command) {
                maxHeap.offer(value);
                minHeap.offer(value);
                continue;
            }

            // 삭제 연산
            if (maxHeap.isEmpty()) { // 큐 비어있으면 무시
                continue;
            }

            if (value == FLAG_MAX) {
                int maxValue = maxHeap.poll();
                minHeap.remove(maxValue);
            } else {
                int minValue = minHeap.poll();
                maxHeap.remove(minValue);
            }
        }

        if (maxHeap.isEmpty()) {
            return new int[]{0, 0};
        }
        return new int[]{maxHeap.poll(), minHeap.poll()};
    }
}
