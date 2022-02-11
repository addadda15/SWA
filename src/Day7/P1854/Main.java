package Day7.P1854;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static ArrayList<Info>[] Map;
    static PriorityQueue<Integer>[] distance;

    static class Info implements Comparable<Info> {
        int to;
        int cost;

        public Info(int next, int cost) {
            this.to = next;
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        Map = new ArrayList[N + 1];
        distance = new PriorityQueue[N + 1];
        for (int i = 1; i <= N; i++) {
            Map[i] = new ArrayList<>();
            distance[i] = new PriorityQueue<>(Collections.reverseOrder());
        }
        int a, b, c;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            Map[a].add(new Info(b, c));
        }

        PriorityQueue<Info> pq = new PriorityQueue<>();
        pq.add(new Info(1, 0));
        distance[1].add(0);
        while (!pq.isEmpty()) {
            Info now = pq.poll();

//            if(now.cost > distance[now.to].peek())
//                continue;

            for(Info next : Map[now.to]){
                if(distance[next.to].size() < K) {
                    distance[next.to].add(now.cost + next.cost);
                    pq.add(new Info(next.to, now.cost + next.cost));
                }else if(distance[next.to].peek() > (now.cost + next.cost)){
                    distance[next.to].poll();
                    distance[next.to].add(now.cost + next.cost);
                    pq.add(new Info(next.to, now.cost + next.cost));
                }
            }
        }

        for(int i = 1; i <=N;i++){
            if(distance[i].size() == K){
                bw.write(distance[i].peek()+"\n");
            }else {
                bw.write(-1 + "\n");
            }
        }
        bw.flush();
        bw.close();
    }
}
