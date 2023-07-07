package main.level3;

import java.util.Stack;

// 표 편집
// https://school.programmers.co.kr/learn/courses/30/lessons/81303
class Prg81303 {

    private static final String SELECT_UP = "U";
    private static final String SELECT_DOWN = "D";
    private static final String DELETE = "C";
    private static final String UNDO = "Z";

    private static final String EXISTS = "O";
    private static final String DELETED = "X";

    private int size;
    private int current;
    private Stack<Integer> removed;

    public String solution(int n, int k, String[] cmd) {
        size = n;
        current = k;
        removed = new Stack<>();

        for (String line : cmd) {
            if (line.startsWith(SELECT_UP)) {
                selectUp(extractOffset(line));
            } else if (line.startsWith(SELECT_DOWN)) {
                selectDown(extractOffset(line));
            } else if (line.startsWith(DELETE)) {
                delete();
            } else if (line.startsWith(UNDO)) {
                undo();
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(EXISTS.repeat(Math.max(0, size)));
        while (!removed.isEmpty()) {
            sb.insert(removed.pop(), DELETED);
        }
        return sb.toString();
    }

    private void selectUp(int offset) {
        current -= offset;
    }

    private void selectDown(int offset) {
        current += offset;
    }

    private void delete() {
        removed.push(current);
        if (current == --size) {
            current--;
        }
    }

    private void undo() {
        int row = removed.pop();
        size++;
        if (current >= row) {
            current++;
        }
    }

    private int extractOffset(String commandLine) {
        int length = commandLine.length();
        String lastChar = commandLine.substring(2, length);
        return Integer.parseInt(lastChar);
    }
}
