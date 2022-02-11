package Day2.P2042;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K, S, Offset;
    static long[] Tree;
    static long Ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        S = 1;
        while (S < N) {
            S *= 2;
        }
        Offset = S - 1;
        Tree = new long[(2 * S)];
        for (int i = 0; i < N; i++) {
            Tree[S + i] = Long.parseLong(br.readLine());
        }
        fill_tree();
        int q, a;
        long b;
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            q = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Long.parseLong(st.nextToken());
            if (q == 2) {
                Ans = query(a, (int)b);
                bw.write(Ans + "\n");
            } else if (q == 1) {

                update(a, b);
            }
        }
        bw.flush();
        bw.close();
    }

    private static void update(int a, Long b) {
        int idx = a + Offset;
        Tree[idx] = b;
        for (idx >>= 1; idx > 0; idx >>= 1) {
            Tree[idx] = Tree[idx * 2] + Tree[(idx * 2) + 1];
        }
    }

    private static long query(int left, int right) {
        left += Offset;
        right += Offset;
        long sum = 0;
        while (left <= right) {
            if (left % 2 == 1) {
                sum += Tree[left];
                left++;
            }
            if (right % 2 == 0) {
                sum += Tree[right];
                right--;
            }
            left >>= 1;
            right >>= 1;
        }
        return sum;
    }

    private static void fill_tree() {
        for (int i = Offset; i > 0; i--) {
            Tree[i] = Tree[i * 2] + Tree[(i * 2) + 1];
        }
    }
}
