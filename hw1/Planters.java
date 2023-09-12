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
        int numSwapped = 0; // number of times a plant has been swapped. Mostly will be used for indexing to grab the next highest in T
        for (int i = 0; i < sortedS.length; i++) {
            int highestS = sortedS[sortedS.length - 1 - i]; // grab highest plant in S
            int highestT = sortedT[sortedT.length - 1 - numSwapped]; // grab highest plant in T
            if (highestS >= highestT) {
                return "NO"; 
            } else {
                swap(sortedS, sortedT, sortedS.length - 1 - i, sortedT.length - 1);
                numSwapped++;
            }
        }

        return "YES";
    }

    public static void main(String[] args) {
        // convert inputs from stdin to necessary data structures
        Scanner scanner = new Scanner(System.in);
        String[] firstLine ="100 156".split(" ");
        String[] secondLine = "38 92 54 44 74 29 26 92 11 19 18 37 64 56 91 59 31 5 72 62 34 86 90 74 5 52 6 51 69 4 86 7 96 40 50 21 68 27 64 78 97 82 66 61 37 56 71 19 12 43 33 97 80 22 71 85 73 28 35 41 84 73 99 31 64 48 51 31 74 15 60 23 48 25 83 36 33 5 55 44 99 87 41 79 60 63 63 84 42 49 24 25 73 23 55 36 22 58 66 48".split(" ");
        String[] thirdLine = "72 77 70 19 2 4 54 34 8 60 29 7 98 21 85 9 35 99 92 77 99 16 53 72 90 60 7 11 17 25 10 40 1 79 10 54 82 15 39 90 27 68 48 24 88 32 33 23 82 76 51 80 91 55 51 32 14 58 95 82 82 4 21 34 83 82 88 16 97 26 5 23 93 52 98 33 35 82 7 16 58 9 96 100 63 98 84 77 55 78 10 88 33 83 22 67 64 61 83 12 86 87 86 31 91 84 15 77 17 21 93 26 29 40 26 91 37 61 19 44 38 29 83 22 11 56 89 26 16 71 38 54 9 23 84 51 58 98 28 27 70 72 52 50 11 29 40 99 89 11 94 78 91 77 100 53".split(" ");
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