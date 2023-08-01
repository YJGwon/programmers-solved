package main.level3;

import java.util.Comparator;
import java.util.PriorityQueue;

// 스타 수열
// https://school.programmers.co.kr/learn/courses/30/lessons/70130
class Prg70130 {

    // 우선 가장 개수가 많은 원소에 대해 만들 수 있는 스타 수열 길이 구하기
    // 여태까지 구한 스타 수열의 길이 / 2 보다 개수가 적은 원소는 skip하면서 만들 수 있는 스타 수열 구하고 최대값 갱신

    public int solution(int[] a) {
        int[] count = new int[a.length];
        for (int element : a) {
            count[element]++;
        }

        PriorityQueue<Element> pq = new PriorityQueue<>(compareByCount().reversed());
        for (int element = 0; element < a.length; element++) {
            if (count[element] == 0) {
                continue;
            }
            pq.offer(new Element(element, count[element]));
        }

        int answer = 0;
        while (!pq.isEmpty()) {
            Element now = pq.poll();
            if (now.count * 2 <= answer) {
                break;
            }
            answer = Math.max(getMaxStarLength(a, now.value), answer);
        }

        return answer;
    }

    private int getMaxStarLength(int[] a, int element) {
        boolean[] used = new boolean[a.length];
        int answer = 0;

        for (int i = 0; i < a.length; i++) {
            if (a[i] != element) {
                continue;
            }

            if (i != 0 && a[i - 1] != element && !used[i - 1]) {
                answer += 2;
                continue;
            }

            if (i + 1 != a.length && a[i + 1] != element) {
                used[i + 1] = true;
                answer += 2;
            }
        }

        return answer;
    }

    private Comparator<Element> compareByCount() {
        return Comparator.comparingInt(e -> e.count);
    }

    private static class Element {
        int value;
        int count;

        Element(int value, int count) {
            this.value = value;
            this.count = count;
        }
    }
}
