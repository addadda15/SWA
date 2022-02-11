package Day7.P1753;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int V, E, S;
    static final int INF = Integer.MAX_VALUE;

    static class Info {
        int node;
        int dis;

        public Info(int node, int dis) {
            this.node = node;
            this.dis = dis;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(br.readLine());
        PriorityQueue<Info> pq = new PriorityQueue<>(new Comparator<Info>() {
            @Override
            public int compare(Info o1, Info o2) {
                return Integer.compare(o1.dis, o2.dis);
            }
        });
        ArrayList<Info>[] aList = new ArrayList[V + 1];
        int[] dis = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            aList[i] = new ArrayList<>();
            dis[i] = INF;
        }
        int u, v, w;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            aList[u].add(new Info(v, w));
        }

        while (!pq.isEmpty()) {
            Info curNode = pq.poll();
            if (curNode.dis > dis[curNode.node])
                continue;
            for (Info info : aList[curNode.node]) {
                if (dis[info.node] > curNode.dis + info.dis) {
                    dis[info.node] = curNode.dis + info.dis;
                    pq.add(new Info(info.node, dis[info.node]));
                }
            }
        }
        for (int i = 1; i <= V; i++) {
            if (dis[i] == INF)
                bw.write("INF\n");
            else
                bw.write(dis[i] + "\n");
        }

        bw.flush();
        bw.close();
    }
}
