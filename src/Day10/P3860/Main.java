package Day10.P3860;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int W, H, E, G;
    static int[][] Map;
    static ArrayList<Info> adjList;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static long[] dist;
    static boolean Flag;

    static class Info {
        int from;
        int to;
        int cost;

        public Info(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        while (W != 0) {
            G = Integer.parseInt(br.readLine());
            Map = new int[W][H];
            adjList = new ArrayList<>();
            dist = new long[W * H];
            int x, y;
            for (int i = 0; i < G; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());
                Map[x][y] = -1;
            }

            E = Integer.parseInt(br.readLine());
            int x1, y1, c;
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());
                x1 = Integer.parseInt(st.nextToken());
                y1 = Integer.parseInt(st.nextToken());
                c = Integer.parseInt(st.nextToken());
                Map[x][y] = 1;
                x = pointToNum(x, y);
                y = pointToNum(x1, y1);
                adjList.add(new Info(x, y, c));
            }

            makeList();
            bellmanFord();
            if (Flag) {
                bw.write("Never\n");
            } else {
                if (dist[W * H - 1] != Long.MAX_VALUE)
                    bw.write(dist[W * H - 1] + "\n");
                else
                    bw.write("Impossible\n");
            }
            st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

        }

        bw.flush();
        bw.close();

    }

    private static void bellmanFord() {
        Flag = false;
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;
        for (int i = 0; i < W * H; i++) {
            for (Info now : adjList) {
                if (dist[now.from] != Long.MAX_VALUE) {
                    dist[now.to] = Math.min(dist[now.from] + now.cost, dist[now.to]);
                }
            }
        }
        for (Info now : adjList) {
            if (dist[now.from] != Long.MAX_VALUE) {
                if (dist[now.from] + now.cost < dist[now.to]) {
                    Flag = true;
                    break;
                }
            }
        }
    }

    private static void makeList() {
        for (int i = 0; i < W; i++) {
            for (int j = 0; j < H; j++) {
                if (i == W - 1 && j == H - 1) {
                    continue;
                }
                if (Map[i][j] != 0)
                    continue;

                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if (check(nx, ny) && Map[nx][ny] != -1) {
                        int x = pointToNum(i, j);
                        int y = pointToNum(nx, ny);
                        adjList.add(new Info(x, y, 1));
                    }
                }
            }
        }
    }

    private static boolean check(int x, int y) {
        return x >= 0 && y >= 0 && x < W && y < H;
    }


    private static int pointToNum(int x, int y) {
        return x + y * W;
    }
}
