package Day3.P1927;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int val;
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);

        for (int i = 0; i < N; i++) {
            val = Integer.parseInt(br.readLine());

            if (val != 0) {
                list.add(val);
                int current = list.size() - 1;
                int parent = current / 2;
                while (true) {
                    if (parent == 0 || list.get(parent) <= list.get(current)) {
                        break;
                    }
                    int temp = list.get(parent);
                    list.set(parent, list.get(current));
                    list.set(current, temp);

                    current = parent;
                    parent = current / 2;
                }
            } else {
                if (list.size() == 1)
                    System.out.println(0);
                else {
                    int top = list.get(1);
                    System.out.println(top);
                    list.set(1, list.get(list.size() - 1));
                    list.remove(list.size() - 1);

                    int currentPos = 1;
                    while (true) {
                        int leftPos = currentPos * 2;
                        int rightPos = leftPos + 1;

                        if (leftPos >= list.size())
                            break;

                        int minValue = list.get(leftPos);
                        int minPos = leftPos;
                        if (rightPos < list.size() && list.get(rightPos) < minValue) {
                            minValue = list.get(rightPos);
                            minPos = rightPos;
                        }

                        if (list.get(currentPos) > minValue) {
                            int temp = list.get(currentPos);
                            list.set(currentPos, list.get(minPos));
                            list.set(minPos, temp);
                            currentPos = minPos;
                        }else{
                            break;
                        }
                    }
                }
            }
        }
    }
}

