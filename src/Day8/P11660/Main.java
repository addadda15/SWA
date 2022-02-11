package Day8.P11660;

        import java.io.*;
        import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N + 1][N + 1];


        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            arr[i][0] = 0;
            for (int j = 1; j <= N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken()) + arr[i][j - 1];
            }
        }

        int a, b, c, d, ans;
        for (int i = 0; i < M; i++) {
            ans = 0;
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            for(int j = a; j <= c;j++){
                ans += arr[j][d] - arr[j][b-1];
            }
            bw.write(ans + "\n");
        }
        bw.flush();
        bw.close();
    }
}
