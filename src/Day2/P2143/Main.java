package Day2.P2143;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] a = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int[] b = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer> aList = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            int[] tmpArr = new int[N + 1];
            for (int j = i; j <= N; j++) {
                tmpArr[j] = a[j - 1] + tmpArr[j - 1];
                aList.add(tmpArr[j]);
            }
        }
        ArrayList<Integer> bList = new ArrayList<>();

        for (int i = 1; i <= M; i++) {
            int[] tmpArr = new int[M + 1];
            for (int j = i; j <= M; j++) {
                tmpArr[j] = b[j - 1] + tmpArr[j - 1];
                bList.add(tmpArr[j]);
            }
        }

        Collections.sort(aList);
        bList.sort(Collections.reverseOrder());

        int p1 = 0, p2 = 0;
        long ans = 0;
        while (p1 < aList.size() && p2 < bList.size()) {
            if (aList.get(p1) + bList.get(p2) == T) {

                int v1 = aList.get(p1);
                int v2 = bList.get(p2);
                long cnt1 = 0, cnt2 = 0;
                while (p1 < aList.size() && aList.get(p1) == v1) {
                    cnt1++;
                    p1++;
                }
                while (p2 < bList.size() && bList.get(p2) == v2) {
                    cnt2++;
                    p2++;
                }
                ans += cnt1 * cnt2;
            } else if (aList.get(p1) + bList.get(p2) < T) {
                p1++;
            } else {
                p2++;
            }
        }
        System.out.println(ans);
    }
}
