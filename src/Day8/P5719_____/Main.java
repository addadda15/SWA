package Day8.P5719_____;

import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<Info>[] Map;
    static int N, M, S, D;
    static ArrayList<Integer>[] Parent;
    static int[] Distance;
    static boolean[][] isShortest;

    static class Info implements Comparable<Info> {
        int node;
        int cost;

        public Info(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Info info) {
            return Integer.compare(this.cost, info.cost);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if (N == 0 && M == 0)
                break;
            Map = new ArrayList[N];
            Parent = new ArrayList[N];
            isShortest = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                Map[i] = new ArrayList<>();
                Parent[i] = new ArrayList<>();
            }
            st = new StringTokenizer(br.readLine(), " ");
            S = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());

            int a, b, c;
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                c = Integer.parseInt(st.nextToken());
                Map[a].add(new Info(b, c));
            }

            getShortest();
            if (Distance[D] == Integer.MAX_VALUE) {
                bw.write(-1 + "\n");
                continue;
            }

            getTracking(D, S);
            getShortest();
            if (Distance[D] == Integer.MAX_VALUE)
                bw.write(-1 + "\n");
            else
                bw.write(Distance[D] + "\n");

        }
        bw.flush();
        bw.close();
    }

    private static void getTracking(int now, int end) {
        if (now == end)
            return;

        for (int next : Parent[now]) {
            if (!isShortest[next][now]) {
                isShortest[next][now] = true;
                getTracking(next, end);
            }
        }
    }

    private static void getShortest() {
        Distance = new int[N];
        for (int i = 0; i < N; i++) {
            Distance[i] = Integer.MAX_VALUE;
        }
        Distance[S] = 0;
        PriorityQueue<Info> pq = new PriorityQueue<>();
        pq.add(new Info(S, 0));

        while (!pq.isEmpty()) {
            Info now = pq.poll();

            if (Distance[now.node] < now.cost)
                continue;

            for (Info next : Map[now.node]) {
                if (isShortest[now.node][next.node])
                    continue;
                if (Distance[next.node] == Distance[now.node] + next.cost) {
                    Parent[next.node].add(now.node);
                } else if (Distance[next.node] > Distance[now.node] + next.cost) {
                    Parent[next.node].clear();
                    Parent[next.node].add(now.node);
                    Distance[next.node] = Distance[now.node] + next.cost;
                    pq.add(new Info(next.node, Distance[next.node]));
                }
            }

        }
    }
}
