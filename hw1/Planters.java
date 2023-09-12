import java.util.Scanner;

public class Planters {
    /**
     * In-place swap between 2 arrays
     * @param S Array 1
     * @param T Array 2
     * @param index_s Index at Array 1
     * @param index_t Index at Array 2
     */
    public static void swap(int[] S, int[] T, int index_s, int index_t) {
        int temp = S[index_s];
        S[index_s] = T[index_t];
        T[index_t] = temp;
    }

    /**
     * Planters problems
     * @param S Planters
     * @param T Extra planters
     */
    public static String planters(int[] S, int T[]) {
        int[] sortedS = MergeSort.mergeSort(S);
        int[] sortedT = MergeSort.mergeSort(T);

        int tEl = sortedT[sortedT.length - 1]; // grab highest plant in T
        for (int i = 0; i < sortedS.length; i++) {
            int sEl = sortedS[sortedS.length - 1 - i]; // grab highest plant in S
            if (sEl >= tEl) {
                return "NO"; 
            }
        }

        return "YES";
    }

    public static void main(String[] args) {
        // convert inputs from stdin to necessary data structures
        Scanner scanner = new Scanner(System.in);
        String[] firstLine = scanner.nextLine().split(" ");
        String[] secondLine = scanner.nextLine().split(" ");
        String[] thirdLine = scanner.nextLine().split(" ");
        scanner.close();
        // create arrays based on firstLine from stdin
        int[] S = new int[Integer.parseInt(firstLine[0])];
        int[] T = new int[Integer.parseInt(firstLine[1])];
        
        // fill both arrays
        for (int i = 0; i < S.length; i++) {
            S[i] = Integer.parseInt(secondLine[i]);
        }

        for (int i = 0; i < T.length; i++) {
            T[i] = Integer.parseInt(thirdLine[i]);
        }

        // get result
        System.out.println(planters(S, T));

    }
}