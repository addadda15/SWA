package Day7.P11404;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int V, E;
    static int[][] Map;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        V = Integer.parseInt(br.readLine());
        E = Integer.parseInt(br.readLine());
        Map = new int[V + 1][V + 1];

        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                Map[i][j] = INF;
            }
        }

        int i, j, c;
        for (int a = 0; a < E; a++) {
            st = new StringTokenizer(br.readLine());
            i = Integer.parseInt(st.nextToken());
            j = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            Map[i][j] = Math.min(Map[i][j],c);
        }

        for (i = 1; i <= V; i++) {
            for (j = 1; j <= V; j++) {
                for (int k = 1; k <= V; k++) {
                    if(j == k || Map[j][i] == INF || Map[i][k] == INF)
                        continue;
                    Map[j][k] = Math.min(Map[j][i] + Map[i][k], Map[j][k]);
                }
            }
        }

        for (i = 1; i <= V; i++) {
            for (j = 1; j <= V; j++) {
                if(Map[i][j] == Integer.MAX_VALUE)
                    bw.write(0+" ");
                else
                    bw.write(Map[i][j] + " ");
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }
}

