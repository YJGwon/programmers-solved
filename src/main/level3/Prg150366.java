package main.level3;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// 표 병합
// https://school.programmers.co.kr/learn/courses/30/lessons/150366
class Prg150366 {
    private String[][] values = new String[51][51];
    private Cell[][] merged = new Cell[51][51];

    public String[] solution(String[] commands) {
        for (int i = 1; i < 51; i++) {
            for (int j = 1; j < 51; j++) {
                values[i][j] = "";
                merged[i][j] = new Cell(i, j);
            }
        }

        List<String> prints = new ArrayList<>();
        for (int i = 0; i < commands.length; i++) {
            String[] command = commands[i].split(" ");
            if ("UPDATE".equals(command[0])) {
                update(command);
            } else if ("MERGE".equals(command[0])) {
                merge(command);
            } else if ("UNMERGE".equals(command[0])) {
                unmerge(command);
            } else if ("PRINT".equals(command[0])) {
                prints.add(print(command));
            }
        }
        return prints.toArray(new String[prints.size()]);
    }

    private void update(String[] command) {
        if (command.length == 4) {
            update(new Cell(command[1], command[2]), command[3]);
        } else if (command.length == 3) {
            update(command[1], command[2]);
        }
    }

    private void update(Cell cell, String value) {
        Cell root = findRoot(cell);
        values[root.r][root.c] = value;
    }

    private void update(String from, String to) {
        for (int i = 1; i < 51; i++) {
            for (int j = 1; j < 51; j++) {
                if (from.equals(values[i][j])) {
                    values[i][j] = to;
                }
            }
        }
    }

    private void merge(String[] command) {
        Cell cell1 = new Cell(command[1], command[2]);
        Cell cell2 = new Cell(command[3], command[4]);
        merge(cell1, cell2);
    }

    private void merge(Cell cell1, Cell cell2) {
        Cell root1 = findRoot(cell1);
        Cell root2 = findRoot(cell2);
        if (root1.equals(root2)) {
            return;
        }
        merged[root2.r][root2.c] = root1;
        if (values[root1.r][root1.c].isBlank()) {
            values[root1.r][root1.c] = values[root2.r][root2.c];
        }
        values[root2.r][root2.c] = "";
    }

    private void unmerge(String[] command) {
        Cell cell = new Cell(command[1], command[2]);
        Cell root = findRoot(cell);
        List<Cell> cellsToUnmerge = new ArrayList<>();
        for (int i = 1; i < 51; i++) {
            for (int j = 1; j < 51; j++) {
                if (findRoot(merged[i][j]).equals(root)) {
                    cellsToUnmerge.add(new Cell(i, j));
                }
            }
        }
        for (Cell cellToUnmerge : cellsToUnmerge) {
            merged[cellToUnmerge.r][cellToUnmerge.c] = cellToUnmerge;
        }
        if (!cell.equals(root)) {
            values[cell.r][cell.c] = values[root.r][root.c];
            values[root.r][root.c] = "";
        }
    }

    private String print(String[] command) {
        Cell cell = new Cell(command[1], command[2]);
        Cell root = findRoot(cell);
        if (values[root.r][root.c].isBlank()) {
            return "EMPTY";
        } else {
            return values[root.r][root.c];
        }
    }

    private Cell findRoot(Cell cell) {
        if (merged[cell.r][cell.c].equals(cell)) {
            return cell;
        }

        return merged[cell.r][cell.c] = findRoot(merged[cell.r][cell.c]);
    }

    private static class Cell {
        final int r;
        final int c;

        Cell(int r, int c) {
            this.r = r;
            this.c = c;
        }

        Cell(String r, String c) {
            this(Integer.parseInt(r), Integer.parseInt(c));
        }

        @Override
        public boolean equals(Object o) {
            Cell cell = (Cell) o;
            return this.r == cell.r && this.c == cell.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }
}
