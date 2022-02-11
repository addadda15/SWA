package Day7.P11438____;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static ArrayList<Integer>[] Map;
    static int[][] Parent;
    static int[] Depth;
    static int LogN;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        LogN = getLog(100000);
        N = Integer.parseInt(br.readLine());
        Map = new ArrayList[N + 1];
        Parent = new int[LogN + 1][N + 1];
        Depth = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            Map[i] = new ArrayList<>();
        }

        int a, b;
        for (int i = 1; i <= N-1; i++) {
            st = new StringTokenizer(br.readLine()," ");
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            Map[a].add(b);
            Map[b].add(a);
        }
        bfs(1);
        makeTable();

        M = Integer.parseInt(br.readLine());
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine()," ");
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            bw.write(getLCA(a,b) + "\n");
        }

        bw.flush();
        bw.close();
    }

    private static int getLCA(int a, int b) {
        if(Depth[a] < Depth[b])
            return getLCA(b,a);

        for (int i = 0; i <= LogN; i++) {
            if (((Depth[a] - Depth[b]) & (1 << i)) >= 1) {
                a = Parent[i][a];
            }
        }

        if (a == b)
            return a;

        for (int i = LogN; i >= 0; i--) {
            if(Parent[i][a] != Parent[i][b]) {
                a = Parent[i][a];
                b = Parent[i][b];
            }
        }

        return Parent[0][a];
    }

    private static int getLog(int k) {
        int cnt = 0;
        for (int i = 1; i < k; i *= 2)
            cnt++;
        return cnt;
    }

    private static void makeTable() {
        for (int i = 1; i <= LogN; i++) {
            for (int j = 1; j <= N; j++) {
                Parent[i][j] = Parent[i - 1][Parent[i - 1][j]];
            }
        }
    }

    private static void bfs(int start) {
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        Depth[start] = 1;
        dq.add(start);

        while (!dq.isEmpty()) {
            int now = dq.poll();
            for (int next : Map[now]) {
                if (Depth[next] == 0) {
                    Depth[next] = Depth[now] + 1;
                    Parent[0][next] = now;
                    dq.add(next);
                }
            }
        }
    }
}
