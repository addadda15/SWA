package Day9.P11049;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int matR [] = new int [N+1];
        int matC [] = new int [N+1];
        int dp [][] = new int [N+1+1][N+1+1];
        final int Inf = Integer.MAX_VALUE;
        // 입력
        for(int i = 1 ; i <= N ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            matR[i] = Integer.parseInt(st.nextToken());
            matC[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = N - 1; i  > 0; i--){
            for(int j = i + 1; j <= N;j++){
                dp[i][j] = Integer.MAX_VALUE;
                for(int k = i; k <= j;k++){
                    dp[i][j] = Math.min(dp[i][j],dp[i][k] + dp[k+1][j] + (matR[i] * matC[j] * matC[k]));
                }
            }
        }
        bw.write(dp[1][N] + "\n");
        bw.flush();
        bw.close();
    }
}
