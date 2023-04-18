package main.level1;

// 바탕화면 정리
// https://school.programmers.co.kr/learn/courses/30/lessons/161990
class Prg161990 {
    private static final char FILE = '#';

    public int[] solution(String[] wallpaper) {
        int sx = Integer.MAX_VALUE; //가장 작은 x
        int sy = Integer.MAX_VALUE; //가장 작은 y
        int ex = 0; //가장 큰 x
        int ey = 0; //가장 큰 y
        for (int i = 0; i < wallpaper.length; i++) {
            for (int j = 0; j < wallpaper[0].length(); j++) {
                if (wallpaper[i].charAt(j) == FILE) {
                    sx = Math.min(sx, i);
                    sy = Math.min(sy, j);
                    ex = Math.max(ex, i);
                    ey = Math.max(ey, j);
                }
            }
        }
        return new int[]{sx, sy, ex + 1, ey + 1};
    }
}
