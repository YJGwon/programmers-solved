package main.level3;

import java.util.*;

// 리틀 프렌즈 사천성
// https://school.programmers.co.kr/learn/courses/30/lessons/1836
class Prg1836 {
    private static final char BLANK = '.';
    private static final char BLOCK = '*';
    private static final String FAIL = "IMPOSSIBLE";

    public String solution(int m, int n, String[] board) {
        Map<Character, int[]> positions = new HashMap<>();
        Map<Character, Set<Character>> leftGraph = new HashMap<>();
        Map<Character, Set<Character>> rightGraph = new HashMap<>();
        Map<Character, int[]> degrees = new HashMap<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char now = board[i].charAt(j);
                if (now == BLANK || now == BLOCK) {
                    continue;
                }
                if (!positions.containsKey(now)) {
                    positions.put(now, new int[]{i, j});
                    continue;
                }

                int[] from = positions.get(now);
                int[] to = new int[]{i, j};

                Set<Character> leftPath = searchLeftPath(now, from, to, board);
                Set<Character> rightPath = searchRightPath(now, from, to, board);

                if (leftPath.contains(BLOCK) && rightPath.contains(BLOCK)) {
                    return FAIL;
                }

                degrees.put(now, new int[]{leftPath.size(), rightPath.size()});
                for (Character c : leftPath) {
                    leftGraph.putIfAbsent(c, new TreeSet<>());
                    leftGraph.get(c).add(now);
                }
                for (Character c : rightPath) {
                    rightGraph.putIfAbsent(c, new TreeSet<>());
                    rightGraph.get(c).add(now);
                }
            }
        }

        StringBuilder removed = new StringBuilder();
        PriorityQueue<Character> removable = new PriorityQueue<>();
        for (Character c : degrees.keySet()) {
            int[] degree = degrees.get(c);
            if (degree[0] == 0 || degree[1] == 0) {
                removable.offer(c);
            }
        }

        while (!removable.isEmpty()) {
            char now = removable.poll();
            removed.append(now);

            for (Character c : leftGraph.getOrDefault(now, Collections.emptySet())) {
                int[] degree = degrees.get(c);
                if (degree[0] == 0 || degree[1] == 0) {
                    continue;
                }
                if (--degrees.get(c)[0] == 0) {
                    removable.offer(c);
                }
            }
            for (Character c : rightGraph.getOrDefault(now, Collections.emptySet())) {
                int[] degree = degrees.get(c);
                if (degree[0] == 0 || degree[1] == 0) {
                    continue;
                }
                if (--degrees.get(c)[1] == 0) {
                    removable.offer(c);
                }
            }
        }

        if (positions.size() != removed.length()) {
            return FAIL;
        }
        return removed.toString();
    }

    private Set<Character> searchLeftPath(char c, int[] from, int[] to, String[] board) {
        if (from[1] < to[1]) {
            Set<Character> path = searchYPath(c, from[1], from[0], to[0], board);
            path.addAll(searchXPath(c, to[0], from[1], to[1], board));
            return path;
        }
        Set<Character> path = searchYPath(c, to[1], from[0], to[0], board);
        path.addAll(searchXPath(c, from[0], to[1], from[1], board));
        return path;
    }

    private Set<Character> searchRightPath(char c, int[] from, int[] to, String[] board) {
        if (from[1] < to[1]) {
            Set<Character> path = searchYPath(c, to[1], from[0], to[0], board);
            path.addAll(searchXPath(c, from[0], from[1], to[1], board));
            return path;
        }
        Set<Character> path = searchYPath(c, from[1], from[0], to[0], board);
        path.addAll(searchXPath(c, to[0], to[1], from[1], board));
        return path;
    }

    private Set<Character> searchXPath(char c, int x, int fromY, int toY, String[] board) {
        Set<Character> set = new HashSet<>();
        for (int y = fromY; y <= toY; y++) {
            char now = board[x].charAt(y);
            if (now == BLANK || now == c) {
                continue;
            }
            set.add(now);
        }
        return set;
    }

    private Set<Character> searchYPath(char c, int y, int fromX, int toX, String[] board) {
        Set<Character> set = new HashSet<>();
        for (int x = fromX; x <= toX; x++) {
            char now = board[x].charAt(y);
            if (now == BLANK || now == c) {
                continue;
            }
            set.add(now);
        }
        return set;
    }
}
