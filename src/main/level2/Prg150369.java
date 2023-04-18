package main.level2;

// 택배 배달과 수거하기
// https://school.programmers.co.kr/learn/courses/30/lessons/150369
class Prg150369 {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        for (int i = n - 1; i >= 0; i--) {
            int visit = howMayVisit(i, cap, deliveries, pickups);
            answer += (long) (i + 1) * visit;
            if (i == 0) {
                break;
            }
            int handled = visit * cap;
            deliveries[i - 1] += deliveries[i] - handled;
            pickups[i - 1] += pickups[i] - handled;
        }
        return answer * 2;
    }

    private int howMayVisit(int i, int cap, int[] deliveries, int[] pickups) {
        int deliverVisit = deliveries[i] / cap;
        if (deliveries[i] % cap > 0) {
            deliverVisit++;
        }
        int pickupVisit = pickups[i] / cap;
        if (pickups[i] % cap > 0) {
            pickupVisit++;
        }
        return Math.max(deliverVisit, pickupVisit);
    }
}
