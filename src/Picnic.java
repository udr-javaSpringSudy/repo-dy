import java.util.*;

public class Picnic {
    static int count = 0;

    public static void main(String[] args) {
        //데이터 입력
        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt(); // 테스트 케이스 수
        for (int t = 0; t < testCase; t++) {
            count = 0;
            int studentCount = sc.nextInt(); // 학생 수
            int pairCount = sc.nextInt(); // 친구 관계의 수
            HashSet<Integer> students = new HashSet<>(studentCount); // 학생들의 집합
            boolean areFriends[][] = new boolean[10][10];

            for (int i = 0; i < studentCount; i++) {
                students.add(i);
            }
            int[] pairs = new int[pairCount * 2]; // 친구 관계를 저장할 배열
            for (int i = 0; i < pairCount * 2; i++) {
                pairs[i] = sc.nextInt();
            }
            for (int i = 0; i < pairs.length; i = i + 2) {
                areFriends[pairs[i]][pairs[i + 1]] = true;
                areFriends[pairs[i + 1]][pairs[i]] = true;
            }
//            System.out.println("pairs = " + Arrays.toString(pairs));
//            System.out.println("areFriends = " + Arrays.deepToString(areFriends));
            linkFriends(students, areFriends, studentCount);
//            System.out.println("----------------------");
            System.out.println(count);

        }
    }

    public static void linkFriends(HashSet<Integer> students, boolean[][] areFriends, int studentCount) {
        if (students.isEmpty()) {
            count++;
            return;
        }
        int first = Collections.min(students);
        for (int i = first + 1; i < studentCount; i++) {
            if (areFriends[first][i] && students.contains(i)) {
                students.remove(first);
                students.remove(i);
//                System.out.println("first = " + first + " i = " + i);
//                System.out.println("tmp = " + students);
                linkFriends(students, areFriends, studentCount);
                students.add(first);
                students.add(i);
            }
        }
    }
}
