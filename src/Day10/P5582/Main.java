package Day10.P5582;

import java.io.*;

public class Main {
    static String[] A, B;
    static int[][] dp;
    static int Max;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        A = br.readLine().split("");
        B = br.readLine().split("");
        dp = new int[A.length + 1][B.length + 1];
        Max = 0;
        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= B.length; j++) {
                if (A[i - 1].equals(B[j - 1])) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    Max = Math.max(Max, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        bw.write(Max + "\n");
        bw.flush();
        bw.close();
    }
}
