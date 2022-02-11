package Day6.P2252;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;

    public static void main(String[] args) throws IOException {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N + 1];
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        int a, b;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            arr[b]++;
            list.get(a).add(b);
        }

        for(int i = 1; i <= N; i++){
            if(arr[i] == 0){
                q.offer(i);
            }
        }

        int idx;
        while(!q.isEmpty()){
            idx = q.poll();
            bw.write(idx+" ");

            for(int i : list.get(idx))
                if(--arr[i] == 0)
                    q.offer(i);
        }

        bw.flush();
        bw.close();
    }
}
