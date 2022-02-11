package Day7.P11266______;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int V, E,order;
    static ArrayList<Integer>[] Map;
    static int[] orderArr;
    static boolean[] cutVertex;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        orderArr = new int[V + 1];
        cutVertex = new boolean[V + 1];
        Map = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            Map[i] = new ArrayList<>();
        }
        int a, b;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            Map[a].add(b);
            Map[b].add(a);
        }
        order = 0;
//        for(int i = 1; i<=V;i++){
//            if(orderArr[i] ==0)
//                dfs(i,true);
//        }
        dfs(1,true);
        int ans = 0;
        StringBuffer sb = new StringBuffer();
        for(int i =0;i <= V;i++){
            if(cutVertex[i]) {
                ans++;
                sb.append(i).append(" ");
            }
        }
        bw.write(ans+"\n");
        if(ans>0){
            bw.write(String.valueOf(sb));
        }
        bw.flush();
        bw.close();
    }

    private static int dfs(int now, boolean isRoot) {
        order++;
        orderArr[now] = order;
        int rtn = order;
        int child = 0;
        for(int next : Map[now]){
            if(orderArr[next] == 0){
                child++;
                int low = dfs(next,false);

                if(!isRoot && low >= orderArr[now]){
                    cutVertex[now] = true;
                }
                rtn = Math.min(rtn,low);
            }else{
                rtn = Math.min(rtn,orderArr[next]);
            }

            if(isRoot && child >=2)
                cutVertex[now] = true;
        }
        return rtn;
    }
}
