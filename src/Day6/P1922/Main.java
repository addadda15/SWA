package Day6.P1922;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int[] Group;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        ArrayList<Net> netList = new ArrayList<>();
        Group = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            Group[i] = i;
        }

        int a, b, w;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            netList.add(new Net(a, b, w));
        }

        Collections.sort(netList);

        int cnt = N - 1;
        int ans = 0;
        while (cnt > 0) {
            a = netList.get(0).from;
            b = netList.get(0).to;
            w = netList.get(0).w;
            netList.remove(0);
            if (find(a) != find(b)) {
                ans += w;
                union(a, b);
                cnt--;
            }
        }
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
    }

    private static void union(int a, int b) {
        int groupA = find(a);
        int groupB = find(b);
        Group[groupA] = groupB;
    }

    private static int find(int a) {
        if (Group[a] == a)
            return a;
        else
            return Group[a] = find(Group[a]);
    }
}

class Net implements Comparable<Net> {
    int from;
    int to;
    int w;

    public Net(int from, int to, int w) {
        this.from = from;
        this.to = to;
        this.w = w;
    }

    @Override
    public int compareTo(Net n) {
        return Integer.compare(this.w, n.w);
    }

}