package DAY01.P1713;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        ArrayList<Student> sList = new ArrayList<>();
        int num;
        boolean flag;

        for(int i =0; i< K ;i++){
            flag = false;
            num = sc.nextInt();
            for(Student s : sList){
                if(s.num == num){
                    s.cnt += 1;
                    flag = true;
                    break;
                }
            }
            if(!flag){
                if(sList.size() <N){
                    sList.add(new Student(num,0,i));
                }else{
                    Student s = Collections.min(sList);
                    sList.set(sList.indexOf(s),new Student(num,0,i));
                }
            }
        }

        sList.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return Integer.compare(o1.num,o2.num);
            }
        });
        for (Student student : sList) {
            System.out.print(student.num + " ");
        }
    }
}

class Student implements Comparable<Student> {
    int num;
    int cnt;
    int old;

    public Student(int num, int cnt, int old) {
        this.num = num;
        this.cnt = cnt;
        this.old = old;
    }

    @Override
    public int compareTo(Student o) {
        return cnt == o.cnt ? Integer.compare(old, o.old) : Integer.compare(cnt, o.cnt);
    }
}