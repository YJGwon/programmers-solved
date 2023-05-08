package main.level3;

// 선입 선출 스케줄링
// https://school.programmers.co.kr/learn/courses/30/lessons/12920
class Prg12920 {
    public int solution(int n, int[] cores) {
        if (n <= cores.length) {
            return n;
        }

        // 이분탐색 - parametric search
        // n개의 작업이 모두 진입하는데에 필요한 최소 시간 탐색
        int left = 1;
        int right = n * cores[0];
        int processed = 0; // 찾은 필요 시간동안 진입할 수 있는 총 작업 수

        while (left < right) {
            final int mid = (left + right) / 2;
            final int processesInTime = processesInTime(mid, cores);
            if (processesInTime >= n) {
                right = mid;
                processed = processesInTime;
            } else {
                left = mid + 1;
            }
        }

        int gap = processed - n; // 필요한 만큼 진입하고 남는 자리 수
        for (int i = cores.length - 1; i >= 0; i--) {
            if (right % cores[i] != 0) { // 해당 시점에 진입한 코어 아님
                continue;
            }
            if (gap-- == 0) { // 해당 시점에 진입한 코어
                return i + 1; // 남는 자리 없다면 마지막으로 처리한 코어
            }
        }

        return 0;
    }

    private int processesInTime(int time, int[] cores) {
        int processes = cores.length;
        for (final int core : cores) {
            processes += (time / core);
        }
        return processes;
    }

}
