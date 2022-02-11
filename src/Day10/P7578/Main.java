package Day10.P7578;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static int N, Offset;
    static long[] Tree;
    static int[] A;
    static HashMap<Integer, Integer> B;
    static long Ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        A = new int[N + 1];
        B = new HashMap<>();
        int treeSize = getTreeSize();
        Offset = treeSize - 1;
        Ans = 0;
        Tree = new long[treeSize * 2];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            B.put(Integer.parseInt(st.nextToken()),i);
        }
        for (int i = 1; i <= N; i++) {
            int idx = B.get(A[i]);
            query(idx + 1, N);
            update(idx);
        }

        bw.write(Ans+"\n");
        bw.flush();
        bw.close();
    }

    private static void query(int left, int right) {
        left += Offset;
        right += Offset;

        while (left <= right) {
            if (left % 2 == 1) {
                Ans += Tree[left];
                left++;
            }
            if (right % 2 == 0) {
                Ans += Tree[right];
                right--;
            }
            left >>= 1;
            right >>= 1;
        }
    }

    private static void update(int idx) {
        idx += Offset;
        Tree[idx] += 1;
        idx >>= 1;
        while (idx > 0) {
            Tree[idx] = Tree[idx * 2] + Tree[idx * 2 + 1];
            idx >>= 1;
        }
    }

    private static int getTreeSize() {
        int size = 1;
        while (size < N) {
            size <<= 1;
        }
        return size;
    }
}
