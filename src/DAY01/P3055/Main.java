package DAY01.P3055;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int R, C;
    static char[][] arr;
    static boolean[][] visited;
    static int[][] dp;
    static boolean flag;
    static Queue<Point> queue;
    static final int[] mX = new int[]{-1, 1, 0, 0};
    static final int[] mY = new int[]{0, 0, -1, 1};


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();
        arr = new char[R][C];
        visited = new boolean[R][C];
        dp = new int[R][C];
        queue = new LinkedList<>();
        flag = false;

        Point start = null;
        for (int i = 0; i < R; i++) {
            String str = sc.next();
            for (int j = 0; j < C; j++) {
                char type = str.charAt(j);
                arr[i][j] = type;
                if (type == '*')
                    queue.add(new Point(i, j, type));
                else if (type == 'S') {
                    dp[i][j] = 0;
                    start = new Point(i, j, type);
                }
            }
        }
        queue.add(start);


        while (!queue.isEmpty()) {
            Point p = queue.poll();
            int x = p.x;
            int y = p.y;
            System.out.println(x + " " + y + " " + p.type);
            if (arr[x][y] == 'D') {
                if (p.type == 'S') {
                    System.out.println(dp[x][y]);
                    flag = true;
                    break;
                } else
                    return;
            }

            visited[x][y] = true;

            for (int i = 0; i < 4; i++) {
                int tmpX = x + mX[i];
                int tmpY = y + mY[i];
                //System.out.println("go -> " + tmpX + " " + tmpY + " " + p.type);
                if (tmpX < 0 || tmpX >= R || tmpY < 0 || tmpY >= C || visited[tmpX][tmpY] || arr[tmpX][tmpY] == 'X')
                    continue;
                //System.out.println("Realgo -> " + tmpX + " " + tmpY + " " + p.type);
                dp[tmpX][tmpY] = dp[x][y] + 1;
                queue.add(new Point(tmpX, tmpY, p.type));
            }
        }

        if (!flag)
            System.out.println("KAKTUS");
    }
}

class Point {
    int x;
    int y;
    char type;

    public Point(int x, int y, char type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }
}
