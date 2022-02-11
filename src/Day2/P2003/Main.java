package Day2.P2003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] nArr = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N;i++){
            nArr[i] = Integer.parseInt(st.nextToken());
        }
        int low = 0;
        int high = 0;
        int sum = nArr[low];
        int cnt = 0;

        while (low < N && high < N) {
            if (sum == M) {
                cnt++;
                sum -= nArr[low];
                low++;
            } else if (sum < M) {
                high++;
                sum += nArr[high];
            } else {
                sum -= nArr[low];
                low++;
            }
        }
        System.out.println(cnt);
    }
}
