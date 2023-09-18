import java.io.IOException;
import java.util.Arrays;
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
    // public static String planters(int[] S, int T[]) {
    //     int[] sortedS = AlgoUtils.mergeSort(S);
    //     int[] sortedT = AlgoUtils.mergeSort(T);
    //     // System.out.println(Arrays.toString(sortedS));
    //     // System.out.println(Arrays.toString(sortedT));
    //     int tIndex = sortedT.length - 1;
    //     for (int sIndex = sortedS.length - 1; sIndex >= 0; sIndex--) {
    //         int s = sortedS[sIndex];
    //         int t = sortedT[tIndex];
    //         int nextT = sortedT[tIndex - 1];
    //         if (nextT >= t) {
    //             tIndex--;
    //         }

    //         if (s >= t) {
    //             return "NO";
    //         } else {
    //             swap(sortedS, sortedT, sIndex, tIndex);
    //         }
    //     }

    //     return "YES";
    // }
    public static String planters(int[] S, int T[]) {
        int[] sortedS = AlgoUtils.mergeSort(S);
        int[] sortedT = AlgoUtils.mergeSort(T);
        // System.out.println(Arrays.toString(sortedS));
        // System.out.println(Arrays.toString(sortedT));
        int currentSindex = sortedS.length - 1;
        int currentTindex = sortedT.length - 1;
        
        // O(n^2)?
        int lowestT = Integer.MIN_VALUE;
        while(currentSindex >= 0) { // iterating backwards
            if (currentTindex == -1) {
                return "NO";
            }
            int s = sortedS[currentSindex];
            int t = sortedT[currentTindex];

            if (s >= t) {
                currentTindex--;
            } else {
                swap(sortedS, sortedT, currentSindex, currentTindex);
                currentTindex = sortedT.length - 1;
                lowestT = Integer.MIN_VALUE;
                currentSindex--;
            }
        }
        System.out.println(Arrays.toString(sortedS));
        return "YES";
    }

    public static void plantersMain(String[] args) {
        // convert inputs from stdin to necessary data structures
        Scanner scanner = new Scanner(System.in);
        String[] firstLine ="4 2".split(" ");
        String[] secondLine = "1 4 3 2".split(" ");
        String[] thirdLine = "5 1".split(" ");
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

    public static void main(String[] args) throws IOException {
        //plantersMain(args);
        TestCases.main(args);

    }
}