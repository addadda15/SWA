package Day8.P1932;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] arr;
    static int[][] ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        arr[0][0] = Integer.parseInt(br.readLine());
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j <= i; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        ans = new int[N][N];
        ans[0][0] = arr[0][0];
        for (int i = 1; i < N ; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    ans[i][j] = ans[i - 1][j] + arr[i][j];
                } else if (j == i) {
                    ans[i][j] = ans[i - 1][j - 1] + arr[i][j];
                } else {
                    ans[i][j] = Math.max(ans[i - 1][j - 1] + arr[i][j], ans[i - 1][j] + arr[i][j]);
                }
            }
        }
        int max = 0;
        for(int n : ans[N - 1]){
            max = Math.max(max, n);
        }
        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
    }
}
