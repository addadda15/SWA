package Day8.P3830;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static long[] diffWeight;
    static int[] Parent;
    static long ans;

    static class Info {
        int node;
        int cost;

        public Info(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int a, b, w;
        String Q;
        while (true) {
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if (N == 0 && M == 0)
                break;
            Parent = new int[N + 1];
            diffWeight = new long[N + 1];
            for (int i = 1; i <= N; i++) {
                Parent[i] = i;
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                Q = st.nextToken();
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                if (Q.equals("!")) {
                    w = Integer.parseInt(st.nextToken());
                    union(a, b, w);
                } else {
                    if (find(a) == find(b)) {
                        ans = diffWeight[b] - diffWeight[a];
                        bw.write(ans + "\n");
                    } else {
                        bw.write("UNKNOWN" + "\n");
                    }
                }
            }
        }

        bw.flush();
        bw.close();

    }

    private static void union(int a, int b, int w) {
        int parA = find(a);
        int parB = find(b);

        if (parA == parB)
            return;

        //System.out.println(diffWeight[a] - diffWeight[b] + w + " = " + diffWeight[a] + " - " + diffWeight[b] + " + " + w);
        diffWeight[parB] = diffWeight[a] - diffWeight[b] + w;
        Parent[parB] = parA;
    }

    private static int find(int i) {
        if (Parent[i] == i)
            return i;
        else {
            int parentIndex = find(Parent[i]);
            diffWeight[i] += diffWeight[Parent[i]];
            return Parent[i] = parentIndex;
        }
    }
}


