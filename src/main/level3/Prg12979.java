package main.level3;

// 기지국 설치
// https://school.programmers.co.kr/learn/courses/30/lessons/12979
class Prg12979 {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int lastReached = 0;
        for (int station : stations) {
            int unReachedLength = station - w - 1 - lastReached;
            answer += countRequiredStations(unReachedLength, w);
            lastReached = station + w;
        }

        answer += countRequiredStations(n - lastReached, w);
        return answer;
    }

    private int countRequiredStations(int length, int w) {
        if (length <= 0) {
            return 0;
        }
        return (int) Math.ceil((double) length / (w * 2 + 1));
    }
}
