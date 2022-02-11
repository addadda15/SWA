package Day2.P1806;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

        int low = 0, high = 0, min = Integer.MAX_VALUE, sum = nArr[low];
        while(low < N && high < N){
            if(sum >= M){
                if((high - low) < min){
                    min = high - low;
                }
                sum -= nArr[low++];
            }else{
                sum += nArr[++high];
            }
        }
        if(min == Integer.MAX_VALUE)
            System.out.println(0);
        else
            System.out.println(min + 1);
    }
}
