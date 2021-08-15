package contest.kakao.intern_2021;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class kakao_거리두기_bfs {

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) {

        System.out.println(
            Arrays.toString(solution(new String[][]{{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}})));
    }

    public static int[] solution(String[][] places) {
        int[] ans = new int[5];
        int idx = 0;

        for (String[] place : places) {
            boolean[][] check = new boolean[5][5];
            Queue<Pair> candidate = new LinkedList<>();
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (place[i].charAt(j) == 'P') {
                        candidate.add(new Pair(i, j));
                    }
                }
            }

            boolean flag = true;
            while (!candidate.isEmpty()) {
                Pair p = candidate.remove();

                if (!bfs(place, check, p.x, p.y)) {
                    flag = false;
                    break;
                }
            }

            ans[idx++] = flag ? 1 : 0;
        }
        return ans;
    }

    public static boolean bfs(String[] place, boolean[][] check, int x, int y) {
        Queue<Pair> queue = new LinkedList<>();

        queue.add(new Pair(x, y));
        check[x][y] = true;

        int cnt = 0;
        while (!queue.isEmpty() && cnt++ < 2) {
            int size = queue.size();

            while (size-- > 0) {
                Pair p = queue.remove();

                for (int i = 0; i < 4; i++) {
                    int nx = p.x + dx[i];
                    int ny = p.y + dy[i];

                    if (0 <= nx && nx < 5 && 0 <= ny && ny < 5 && place[nx].charAt(ny) != 'X') {
                        if (!check[nx][ny]) {
                            if (place[nx].charAt(ny) == 'P') {
                                return false;
                            } else {
                                check[nx][ny] = true;
                                queue.add(new Pair(nx, ny));
                            }
                        }
                    }
                }
            }
        }

        return true;
    }

    public static class Pair {

        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
