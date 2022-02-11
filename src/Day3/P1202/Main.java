package Day3.P1202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Jew> jList = new ArrayList<>();
        ArrayList<Integer> aList = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        long cnt = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            jList.add(new Jew(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        for (int i = 0; i < k; i++) {
            aList.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(aList);
        jList.sort(new Comparator<Jew>() {
            @Override
            public int compare(Jew o1, Jew o2) {
                return Integer.compare(o1.weight, o2.weight);
            }
        });
        int p1 = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < aList.size(); i++) {
            int bag = aList.get(i);
            while (p1 < N && jList.get(p1).weight <= bag) {
                pq.add(jList.get(p1).value);
                p1++;
            }
            if (!pq.isEmpty()) {
                cnt += pq.poll();
            }
        }
        System.out.println(cnt);
    }
}

class Jew {
    int weight;
    int value;

    public Jew(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }
}
