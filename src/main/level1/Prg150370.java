package main.level1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

// 개인정보 수집 유효기간
// https://school.programmers.co.kr/learn/courses/30/lessons/150370
class Prg150370 {

    public int[] solution(String today, String[] terms, String[] privacies) {
        Date dateToday = Date.of(today);

        Map<String, Integer> termMonths = new HashMap<>();
        StringTokenizer st;
        for (int i = 0; i < terms.length; i++) {
            st = new StringTokenizer(terms[i]);
            String term = st.nextToken();
            termMonths.put(term, Integer.parseInt(st.nextToken()));
        }

        List<Integer> answers = new ArrayList<>();
        for (int i = 0; i < privacies.length; i++) {
            st = new StringTokenizer(privacies[i]);
            Date signed = Date.of(st.nextToken());
            Date expires = signed.addMonth(termMonths.get(st.nextToken()));
            if (dateToday.isAfter(expires)) {
                answers.add(i + 1);
            }
        }
        int[] answer = new int[answers.size()];
        for (int i = 0; i < answers.size(); i++) {
            answer[i] = answers.get(i);
        }
        return answer;
    }

    private static class Date {
        final int value;

        Date(int value) {
            this.value = value;
        }

        static Date of(String rawDate) {
            StringTokenizer st = new StringTokenizer(rawDate, ".");
            int year = Integer.parseInt(st.nextToken());
            int month = Integer.parseInt(st.nextToken());
            int date = Integer.parseInt(st.nextToken());
            int value = (year * 12 * 28) + (month * 28) + date;
            return new Date(value);
        }

        Date addMonth(int m) {
            return new Date(value + (m * 28));
        }

        private boolean isAfter(Date other) {
            return this.value >= other.value;
        }
    }
}
