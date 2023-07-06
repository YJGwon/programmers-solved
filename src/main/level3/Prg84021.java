package main.level3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Collectors;

// 퍼즐 조각 채우기
// https://school.programmers.co.kr/learn/courses/30/lessons/84021
class Prg84021 {
    // 좌표 시계방향 90도 회전: -x -> y, y -> x
    // ex) (2, 1) -> (1, -2) -> (-2, -1) -> (-1, 2)

    private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private int size;
    private List<Block> blocks;

    public int solution(int[][] game_board, int[][] table) {
        size = game_board.length;
        blocks = new ArrayList<>();

        boolean[][] visited = new boolean[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                int target = 1;
                if (table[x][y] != target || visited[x][y]) {
                    continue;
                }
                visited[x][y] = true;
                List<Position> block = measure(table, target, visited, x, y);
                blocks.add(new Block(block));
            }
        }

        int answer = 0;
        visited = new boolean[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                int target = 0;
                if (game_board[x][y] != target || visited[x][y]) {
                    continue;
                }
                visited[x][y] = true;
                List<Position> blank = measure(game_board, target, visited, x, y);
                if (matchBlocks(new Block(blank))) {
                    answer += blank.size();
                }
            }
        }

        return answer;
    }

    private List<Position> measure(int[][] source, int target, boolean[][] visited, int x, int y) {
        List<Position> result = new ArrayList<>();
        result.add(new Position(0, 0));

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            for (int i = 0; i < DIRECTIONS.length; i++) {
                int[] direction = DIRECTIONS[i];
                int nextX = now[0] + direction[0];
                int nextY = now[1] + direction[1];

                if (isOutOfRange(nextX, nextY)) { // 범위 밖
                    continue;
                }
                if (source[nextX][nextY] != target) { // target 아님
                    continue;
                }
                if (visited[nextX][nextY]) { // 이미 방문
                    continue;
                }

                visited[nextX][nextY] = true;
                int[] next = new int[]{nextX, nextY};
                queue.offer(next);
                result.add(new Position(nextX - x, nextY - y));
            }
        }

        return result;
    }

    private boolean matchBlocks(Block blank) {
        for (int i = 0; i < blocks.size(); i++) {
            if (blocks.get(i).matches(blank)) {
                blocks.remove(i);
                return true;
            }
        }
        return false;
    }

    private boolean isOutOfRange(int x, int y) {
        return x < 0 || x >= size || y < 0 || y >= size;
    }

    private class Block {

        private final List<Position> tiles;

        public Block(List<Position> tiles) {
            Collections.sort(tiles, Comparator.comparing(Position::getX).thenComparing(Position::getY));
            this.tiles = tiles;
        }

        public boolean matches(Block block) {
            if (this.size() != block.size()) {
                return false;
            }

            for (int i = 0; i < 4; i++) {
                if (this.tiles.equals(block.tiles)) {
                    return true;
                }
                block = block.rotate().toRelative();
            }
            return false;
        }

        public Block rotate() {
            List<Position> rotatedTiles = tiles.stream()
                    .map(Position::rotate)
                    .collect(Collectors.toList());
            return new Block(rotatedTiles);
        }

        public Block toRelative() {
            Position start = tiles.get(0);
            List<Position> relativePositions = tiles.stream()
                    .map(p -> p.relativePosition(start))
                    .collect(Collectors.toList());
            return new Block(relativePositions);
        }

        public int size() {
            return tiles.size();
        }
    }

    private class Position {

        private final int x;
        private final int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Position rotate() {
            return new Position(y, -x);
        }

        public Position relativePosition(Position from) {
            return new Position(this.x - from.x, this.y - from.y);
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public boolean equals(Object o) {
            Position p = (Position) o;
            return this.x == p.x && this.y == p.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return String.format("x: %d, y: %d", x, y);
        }
    }
}
