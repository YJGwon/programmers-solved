package main.level3;

// 110 옮기기
// https://school.programmers.co.kr/learn/courses/30/lessons/77886
class Prg77886 {
    // 110을 옮겨서 0 or 10 > 110 > 연속된 1 순으로 배치하는 것이 목표

    public String[] solution(String[] s) {
        String[] answer = new String[s.length];
        for (int i = 0; i < s.length; i++) {
            String str = s[i];
            answer[i] = getAnswer(str);
        }
        return answer;
    }

    private String getAnswer(String s) {
        StringBuilder sb = new StringBuilder();
        int countOne = 0; // 앞에 있는 연속된 1 개수
        int count110 = 0; // 옮길 수 있는 110 개수

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '1') {
                countOne++;
                continue;
            }

            // 0 발견
            if (countOne <= 1) { // 0 또는 10 -> 맨앞으로 배치
                sb.append("1".repeat(countOne));
                sb.append("0");
                countOne = 0;
                continue;
            }

            // 110
            countOne -= 2;
            count110++;
        }
        // 110 배치
        sb.append("110".repeat(count110));
        // 마지막으로 1 털어내기
        sb.append("1".repeat(countOne));
        return sb.toString();
    }
}
