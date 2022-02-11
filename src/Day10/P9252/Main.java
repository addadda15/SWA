package Day10.P9252;

import java.io.*;

public class Main {
    static String[] A, B;
    static int[][] dp, Direction;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        A = br.readLine().split("");
        B = br.readLine().split("");
        dp = new int[A.length + 1][B.length + 1];
        Direction = new int[A.length + 1][B.length + 1];

        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= B.length; j++) {
                if (dp[i - 1][j] < dp[i][j - 1]) {
                    dp[i][j] = dp[i][j - 1];
                    Direction[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j];
                    Direction[i][j] = 2;
                }
                if (A[i-1].equals(B[j-1])) {
                    if (dp[i][j] < dp[i - 1][j - 1] + 1) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                        Direction[i][j] = 3;
                    }
                }
            }
        }
        int i = A.length, j = B.length;
        int cnt = dp[i][j];
        while (cnt != 0) {
            if (Direction[i][j] == 1) {
                j--;
            } else if (Direction[i][j] == 2) {
                i--;
            } else {
                sb.append(A[i-1]);
                i--;
                j--;
                cnt--;
            }
        }

        bw.write(dp[A.length][B.length] + "\n");
        bw.write(sb.reverse().toString());
        bw.flush();
        bw.close();
    }
}
