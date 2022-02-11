package Day2.P2243;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N, Offset;
    static int[] Tree;
    static int Ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int size = getTreeSize();
        Tree = new int[size * 2];
        Offset = size - 1;

        int q, a, b;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            q = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            if (q == 1) {
                Ans = query(1, size, 1, a);
                update(Ans, - 1);
                bw.write(Ans + "\n");
            } else if (q == 2) {
                b = Integer.parseInt(st.nextToken());
                update(a, b);
            }
        }

        bw.flush();
        bw.close();
    }

    private static int query(int left, int right, int node, int val) {
        if (left == right) {
            return left;
        }
        int mid = (left + right) / 2;
        if (Tree[node * 2] >= val) {
            return query(left, mid, node * 2, val);
        } else {
            return query(mid + 1, right, node * 2 + 1, val - Tree[node * 2]);
        }
    }


    private static void update(int a, int b) {
        int idx = a + Offset;
        Tree[idx] += b;
        idx >>= 1;
        while (idx != 0) {
            Tree[idx] = Tree[idx * 2] + Tree[idx * 2 + 1];
            idx >>= 1;
        }
    }

    private static int getTreeSize() {
        int size = 1;
        while (size <  4) {
            size <<= 1;
        }
        return size;
    }
}
