package Day9.P3176;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N, K, Max, Min;
    static ArrayList<Info>[] Map;
    static int[][] Parents, MaxMat, MinMat;
    static int[] Depth;
    static int LogN;

    static class Info {
        int node;
        int w;

        public Info(int node, int w) {
            this.node = node;
            this.w = w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        LogN = getLog(N);
        Map = new ArrayList[N + 1];
        Depth = new int[N + 1];
        Parents = new int[LogN + 1][N + 1];
        MaxMat = new int[LogN + 1][N + 1];
        MinMat = new int[LogN + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Map[i] = new ArrayList<>();
        }
        int a, b, w;
        for (int i = 1; i <= N - 1; i++) {
            st = new StringTokenizer(br.readLine()," ");
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            Map[a].add(new Info(b, w));
            Map[b].add(new Info(a, w));
        }
        bfs();
        makeMatrix();

        K = Integer.parseInt(br.readLine());
        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine()," ");
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            getLCA(a, b);
            bw.write(Min + " " + Max + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static int getLog(int n) {
        int cnt = 0;
        for (int i = 1; i < n; i *= 2) {
            cnt++;
        }
        return cnt;
    }

    private static void bfs() {
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        Depth[1] = 1;
        dq.add(1);

        int now;
        while (!dq.isEmpty()) {
            now = dq.poll();
            for (Info next : Map[now]) {
                if (Depth[next.node] == 0) {
                    Depth[next.node] = Depth[now] + 1;
                    Parents[0][next.node] = now;
                    MaxMat[0][next.node] = next.w;
                    MinMat[0][next.node] = next.w;
                    dq.add(next.node);
                }
            }
        }
    }

    private static int getLCA(int a, int b) {
        if (Depth[a] < Depth[b])
            return getLCA(b, a);

        Max = Integer.MIN_VALUE;
        Min = Integer.MAX_VALUE;

        for (int i = 0; i <= LogN; i++) {
            if (((Depth[a] - Depth[b]) & (1 << i)) >= 1) {
                Max = Math.max(MaxMat[i][a], Max);
                Min = Math.min(MinMat[i][a], Min);
                a = Parents[i][a];
            }
        }

        if (a == b)
            return 0;

        for (int i = LogN; i >= 0; i--) {
            if (Parents[i][a] != Parents[i][b]) {
                Max = Math.max(MaxMat[i][a], Max);
                Min = Math.min(MinMat[i][a], Min);
                Max = Math.max(MaxMat[i][b], Max);
                Min = Math.min(MinMat[i][b], Min);
                a = Parents[i][a];
                b = Parents[i][b];
            }
        }

        Max = Math.max(MaxMat[0][a], Max);
        Min = Math.min(MinMat[0][a], Min);
        Max = Math.max(MaxMat[0][b], Max);
        Min = Math.min(MinMat[0][b], Min);

        return 0;

    }

    private static void makeMatrix() {
        for (int i = 1; i <= LogN; i++) {
            for (int j = 1; j <= N; j++) {
                Parents[i][j] = Parents[i - 1][Parents[i - 1][j]];
                MaxMat[i][j] = Math.max(MaxMat[i - 1][j], MaxMat[i - 1][Parents[i - 1][j]]); // 중요
                MinMat[i][j] = Math.min(MinMat[i - 1][j], MinMat[i - 1][Parents[i - 1][j]]);
            }
        }
    }
}
