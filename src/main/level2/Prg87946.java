package main.level2;

// 피로도
// https://school.programmers.co.kr/learn/courses/30/lessons/87946
class Prg87946 {

    private int k;
    private int[][] dungeons;
    private int answer;

    public int solution(int k, int[][] dungeons) {
        this.k = k;
        this.dungeons = dungeons;
        answer = 0;

        dfs(0, new int[dungeons.length], new boolean[dungeons.length]);
        return answer;
    }

    private void dfs(int depth, int[] path, boolean[] visited) {
        if (depth == path.length) {
            int count = explore(path);
            answer = Math.max(count, answer);
            return;
        }

        for (int i = 0; i < path.length; i++) {
            if (visited[i]) {
                continue;
            }
            path[depth] = i;
            visited[i] = true;
            dfs(depth + 1, path, visited);
            visited[i] = false;
        }
    }

    private int explore(int[] path) {
        int count = 0;
        int hp = k;
        for (int now : path) {
            if (hp < dungeons[now][0]) {
                return count;
            }
            count++;
            hp -= dungeons[now][1];
        }
        return count;
    }
}
