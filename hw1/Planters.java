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
    public static String planters(int[] S, int T[]) {
        int[] sortedS = AlgoUtils.mergeSort(S);
        int[] sortedT = AlgoUtils.mergeSort(T);
        // System.out.println(Arrays.toString(sortedS));
        // System.out.println(Arrays.toString(sortedT));
        System.out.println("Initial S: " + Arrays.toString(sortedS) +", Initial T: " + Arrays.toString(sortedT));
        int swapped; // initial value
        int currentTindex = sortedT.length - 1;
        int s = sortedS[sortedS.length - 1];
        int t = sortedT[currentTindex];

        // initial case to initialize swapped
        if (t > s) {
            swapped = s;
            swap(sortedS, sortedT, sortedS.length - 1, currentTindex);
            sortedT[currentTindex] = 0;
        } else {
            return "NO";
        }

        for (int i = 1; i < sortedS.length; i++) {
            int sIndex = sortedS.length - 1 - i;
            s = sortedS[sIndex];
            t = sortedT[currentTindex];
            
            if (t > s && t > swapped) {
                swap(sortedS, sortedT, sIndex, currentTindex);
                sortedT[currentTindex] = 0;
                currentTindex--;
            } else if (s >= t && swapped > t && swapped > s) {
                int temp = sortedS[sIndex];
                sortedS[sIndex] = swapped;
                swapped = temp;
            } else if (t > s) {
                swap(sortedS, sortedT, sIndex, currentTindex);
                sortedT[currentTindex] = 0;
                currentTindex--;
            }
        }
        System.out.println("After S: " + Arrays.toString(sortedS) +", After T: " + Arrays.toString(sortedT));
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
        System.out.println(Arrays.toString(S));
        System.out.println((Arrays.toString(T)));
        // get result
        System.out.println(planters(S, T));
    }

    public static void main(String[] args) throws IOException {
        //plantersMain(args);
        TestCases.main(args);
    }
}