package Day6.P11657;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ArrayList<Node> aList = new ArrayList<>();

        int a, b, w;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            aList.add(new Node(a, b, w));
        }
        long[] arr = new long[N + 1];
        for (int i = 2; i <= N; i++) {
            arr[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < N - 1; i++) {
            for (Node node : aList) {
                if (arr[node.start] != Integer.MAX_VALUE) {
                    arr[node.end] = Math.min(arr[node.end], arr[node.start] + node.w);
                }
            }
        }
        boolean flag = false;
        for (Node node : aList) {
            if (arr[node.start] != Integer.MAX_VALUE) {
                if (arr[node.end] > arr[node.start] + node.w) {
                    flag = true;
                    break;
                }
            }
        }
        if (flag) {
            bw.write("-1");
        } else {
            for (int i = 2; i <= N; i++) {
                if (arr[i] == Integer.MAX_VALUE) {
                    bw.write(-1 + "\n");
                } else {
                    bw.write(arr[i] + "\n");
                }

            }
        }
        bw.flush();
        bw.close();
    }
}

class Node {
    int start;
    int end;
    int w;

    public Node(int start, int end, int w) {
        this.start = start;
        this.end = end;
        this.w = w;
    }
}
