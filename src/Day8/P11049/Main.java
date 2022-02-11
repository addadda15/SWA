package Day8.P11049;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
        public static void main(String[] args) throws NumberFormatException, IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringTokenizer st;

            int N = Integer.parseInt(br.readLine());
            int matR [] = new int [N+1];
            int matC [] = new int [N+1];
            int d [][] = new int [N+1+1][N+1+1];
            final int Inf = Integer.MAX_VALUE;
            // 입력
            for(int i = 1 ; i <= N ; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                matR[i] = Integer.parseInt(st.nextToken());
                matC[i] = Integer.parseInt(st.nextToken());
            }

            for(int i = N-1 ; i >= 1 ; i--) {
                for(int j = i + 1 ; j <= N ; j++) {
                    d[i][j] = Inf;
                    for(int k = i ; k <= j ; k++) {
                        d[i][j] = Math.min(d[i][j], d[i][k]+d[k+1][j]+matR[i]*matC[k]*matC[j]);
                    }
                }
            }
            bw.write(d[1][N] + "\n");
            bw.flush();
            bw.close();
    }
}
