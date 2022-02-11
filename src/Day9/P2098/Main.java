package Day9.P2098;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] W;
    static int[][] dp;
    static final int Inf = Integer.MAX_VALUE;
    static int Visit_all;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        W = new int[N + 1][N + 1];
        Visit_all = (1 << N) - 1;
        dp = new int[N + 1][Visit_all + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= Visit_all; j++) {
                dp[i][j] = Inf;
            }
        }
        ans = Inf;
        dp[1][1] = 0;
        getDP(1, 1);


        bw.write(ans + "\n");
        bw.flush();
        bw.close();
    }

    private static void getDP(int now, int visited) {
        if (visited == Visit_all) {
            if (W[now][1] == 0)
                return;
            else
                ans = Math.min(ans, dp[now][visited] + W[now][1]);
        }

        for (int i = 1; i <= N; i++) {
            int next = 1 << (i - 1);
            int nextVisit = visited | next;

            if (visited == nextVisit) {
                continue;
            }
            if (W[now][i] == 0) {
                continue;
            }

            if (dp[i][nextVisit] > dp[now][visited] + W[now][i]) {
                dp[i][nextVisit] = dp[now][visited] + W[now][i];
                getDP(i, nextVisit);
            }
        }
    }
}
