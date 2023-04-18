package main.level2;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.Predicate;

// 단체사진 찍기
// https://school.programmers.co.kr/learn/courses/30/lessons/1835
public class Prg1835 {
    private static final char[] FRIENDS = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    private boolean[] visited;
    private int answer;
    private List<Predicate<String>> conditions;

    public int solution(int n, String[] data) {
        visited = new boolean[8];
        answer = 0;

        conditions = parseConditions(n, data);
        dfs("");
        return answer;
    }

    private List<Predicate<String>> parseConditions(final int n, final String[] data) {
        final List<Predicate<String>> conditions = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            conditions.add(parseCondition(data[i]));
        }
        return conditions;
    }

    private void dfs(String photoLine) {
        if (photoLine.length() == 8) {
            checkConditions(photoLine);
        }

        for (int i = 0; i < 8; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(photoLine + FRIENDS[i]);
                visited[i] = false;
            }
        }
    }

    private Predicate<String> parseCondition(final String rawCondition) {
        final StringTokenizer st = new StringTokenizer(rawCondition, "~=><");
        final String aFriend = st.nextToken();
        final String anotherFriend = st.nextToken();
        final int gap = Integer.parseInt(st.nextToken()) + 1;

        if (rawCondition.contains("=")) {
            return (photoLine) -> Math.abs(photoLine.indexOf(aFriend) - photoLine.indexOf(anotherFriend)) == gap;
        }
        if (rawCondition.contains(">")) {
            return (photoLine) -> Math.abs(photoLine.indexOf(aFriend) - photoLine.indexOf(anotherFriend)) > gap;
        }
        return (photoLine) -> Math.abs(photoLine.indexOf(aFriend) - photoLine.indexOf(anotherFriend)) < gap;
    }

    private void checkConditions(String photoLine) {
        for (Predicate<String> condition : conditions) {
            if (!condition.test(photoLine)) {
                return;
            }
        }
        answer++;
    }
}
