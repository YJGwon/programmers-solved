package main.level2;
import java.util.LinkedList;
import java.util.Queue;


// 카카오프렌즈 컬러링북
// https://school.programmers.co.kr/learn/courses/30/lessons/1829
public class Prg1829 {
    private static final int[] moveX = {0, 0, -1, 1};
    private static final int[] moveY = {-1, 1, 0, 0};
    private int width;
    private int height;
    private boolean[][] visited;

    public int[] solution(int m, int n, int[][] picture) {
        width = m;
        height = n;
        visited = new boolean[m][n];
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] == 0) {
                    visited[i][j] = true;
                    continue;
                }
                if (visited[i][j]) {
                    continue;
                }
                maxSizeOfOneArea = Math.max(checkSize(i, j, picture), maxSizeOfOneArea);
                numberOfArea++;
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    private int checkSize(final int x, final int y, final int[][] picture) {
        final int color = picture[x][y];

        final Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        int size = 0;
        while (!queue.isEmpty()) {
            final int[] current = queue.poll();
            size++;
            for (int i = 0; i < 4; i++) {
                final int[] next = {current[0] + moveX[i], current[1] + moveY[i]};
                if (next[0] < 0 || next[1] < 0 || next[0] >= width || next[1] >= height) {
                    continue;
                }
                if (visited[next[0]][next[1]]) {
                    continue;
                }
                if (picture[next[0]][next[1]] != color) {
                    continue;
                }
                visited[next[0]][next[1]] = true;
                queue.offer(next);
            }
        }
        return size;
    }
}
