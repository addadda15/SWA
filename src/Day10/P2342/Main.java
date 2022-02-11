package Day10.P2342;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][][] dp;
    static int[] direction;
    static int Inf, Ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = 100000;
        direction = new int[N + 1];
        int now;
        for (int i = 1; i <= N; i++) {
            now = Integer.parseInt(st.nextToken());
            if (now == 0) {
                N = i - 1;
                break;
            }
            direction[i] = now;
        }

        Inf = Integer.MAX_VALUE;
        dp = new int[N + 1][5][5];

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    dp[i][j][k] = Inf;
                }
            }
        }

        dp[1][0][direction[1]] = 2;
        dp[1][direction[1]][0] = 2;

        int next;
        for (int i = 1; i <= N - 1; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    if (dp[i][j][k] != Inf) {
                        next = direction[i + 1];
                        if (next != k) {
                            dp[i + 1][next][k] = Math.min(dp[i + 1][next][k], dp[i][j][k] + getCost(j, next));
                        }
                        if (next != j) {
                            dp[i + 1][j][next] = Math.min(dp[i + 1][j][next], dp[i][j][k] + getCost(k, next));
                        }
                    }
                }
            }
        }
        Ans = Integer.MAX_VALUE;
        for (int j = 0; j < 5; j++) {
            for (int k = 0; k < 5; k++) {
                Ans = Math.min(dp[N][j][k], Ans);
            }
        }
        bw.write(Ans + "\n");
        bw.flush();
        bw.close();
    }

    private static int getCost(int a, int b) {
        if (a == b)
            return 1;
        else if (a == 0)
            return 2;
        else if (Math.abs(a - b) == 2)
            return 4;
        else
            return 3;
    }
}

