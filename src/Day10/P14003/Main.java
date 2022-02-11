package Day10.P14003;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N, Size, Answer;
    static int[] D, Num;
    static int[] IndexOrder;
    static int[] Tracking;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        D = new int[N + 1];
        IndexOrder = new int[N + 1];
        Tracking = new int[N + 1];
        Num = new int[N + 1];

        int a;
        for (int i = 1; i <= N; i++) {
            a = Integer.parseInt(st.nextToken());
            Num[i] = a;
            if (i == 1) {
                Size = 1;
                IndexOrder[1] = 1;
                D[1] = a;
            } else {
                int searchIndex = doBinarySearch(a, 1, Size);
                IndexOrder[i] = searchIndex;
                if (searchIndex > Size) {
                    Size++;
                    D[Size] = a;
                } else {
                    D[searchIndex] = a;
                }
            }
        }

        Answer = Size;

        for (int i = N; i > 0; i--) {
            if (Size == IndexOrder[i]) {
                Tracking[Size] = Num[i];
                Size--;
            }
        }

        bw.write(Answer + "\n");
        for (int i = 1; i <= Answer; i++) {
            bw.write(Tracking[i] + " ");
        }
        bw.flush();
        bw.close();
    }

    private static int doBinarySearch(int a, int i, int size) {
        int low = i;
        int high = size;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (D[mid] > a) {
                high = mid - 1;
            } else if (D[mid] < a) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return low;
    }
}
