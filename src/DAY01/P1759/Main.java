package DAY01.P1759;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int l = sc.nextInt();
        int c = sc.nextInt();
        char[] cArr = new char[c];
        for (int i = 0; i < c; i++) {
            cArr[i] = sc.next().charAt(0);
        }
        char[] ansArr = new char[l];
        Arrays.sort(cArr);

        dfs(0, cArr, ansArr, 0);

    }

    private static void dfs(int i, char[] cArr, char[] ansArr, int cnt) {
        if (cnt == ansArr.length) {
            int cnt1 = 0;
            int cnt2 = 0;
            for (char c : ansArr) {
                if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                    cnt1++;
                } else {
                    cnt2++;
                }
            }
            if (cnt1 >= 1 && cnt2 >= 2) {
                System.out.println(ansArr);
            }
        } else {
            for (int idx = i; idx < cArr.length; idx++) {
                ansArr[cnt] = cArr[idx];
                dfs(idx + 1, cArr, ansArr, cnt + 1);
            }
        }
    }
}
