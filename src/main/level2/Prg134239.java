package main.level2;

import java.util.ArrayList;
import java.util.List;

// 우박수열 정적분
// https://school.programmers.co.kr/learn/courses/30/lessons/134239
class Prg134239 {
    public double[] solution(int k, int[][] ranges) {
        List<Integer> sequence = getSequence(k);
        // System.out.println(sequence.toString());

        double[] areaPrefix = new double[sequence.size()];
        for (int i = 1; i < sequence.size(); i++) {
            double area = (sequence.get(i - 1) + sequence.get(i)) / 2.0;
            areaPrefix[i] = areaPrefix[i - 1] + area;
        }
        // System.out.println(Arrays.toString(areaPrefix));

        double[] answer = new double[ranges.length];
        for (int i = 0; i < ranges.length; i++) {
            int left = ranges[i][0];
            int right = areaPrefix.length + ranges[i][1] - 1;
            if (left > right) {
                answer[i] = -1.0;
                continue;
            }
            answer[i] = areaPrefix[right] - areaPrefix[left];
        }
        return answer;
    }

    private List<Integer> getSequence(int k) {
        List<Integer> sequence = new ArrayList<>();
        sequence.add(k);
        while (k != 1) {
            if (k % 2 == 0) {
                k /= 2;
            } else {
                k *= 3;
                k++;
            }
            sequence.add(k);
        }
        return sequence;
    }
}
