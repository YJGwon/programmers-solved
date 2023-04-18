package main.level3;

// 풍선 터트리기
// https://school.programmers.co.kr/learn/courses/30/lessons/68646
public class Prg68646 {
    public int solution(int[] a) {
        // 자신보다 작은 값은 왼쪽, 오른쪽 중 한 쪽에만 있어야 함
        // 지금까지의 최소 -> 남을 수 O
        // 아니면 -> 그 다음 풍선은 모두 해당 풍선보다 커야함

        int answer = 0;
        int minIdx = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > a[minIdx]) {
                continue;
            }
            answer++;
            minIdx = i;
        }

        int min = a[a.length - 1];
        for (int i = a.length - 1; i > minIdx; i--) {
            if (a[i] > min) {
                continue;
            }
            answer++;
            min = a[i];
        }
        return answer;
    }
}
