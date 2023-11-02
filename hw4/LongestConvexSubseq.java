import java.util.Scanner;

public class LongestConvexSubseq {
    /**
     * Bottom up (non-recursive) approach to the longest increasing convex subsequence problem using dynamic programming
     * @param array input
     * @return length of longest convex subsequence
     */
    public static int longestConvexSubseq(int[] array) {
        // initialize optimal solution/DP array 
        int[][] OPT = new int[array.length][array.length];
        for (int i = 0; i < array.length; i++) {
            OPT[i][i] = 1; // minimum length of a subsequence is the element itself
        }
        
        for (int i = 0; i < array.length; i++) { 
            for (int j = 0; j < i; j++) {
                OPT[i][j] = 2; // minimum length for a non-convex subsequence
                for (int k = 0; k < j; k++) { 
                    if (array[k] +  array[i] >= 2 * array[j]) { // check if the following sequence is a convex subsequence
                        if (1 + OPT[j][k] > OPT[i][j]) { 
                            OPT[i][j] = OPT[j][k] + 1;
                        }
                    }
                }
            }
        }

        // calculate maximum subsequence given DP array
        int maxLength = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < i; j++){ 
                if (OPT[i][j] > maxLength) {
                    maxLength = OPT[i][j];
                }
            }
        }
    
        return maxLength;
    }


    public static void longestConvexSubseqTest(String input) {
        String[] inputToken = input.split(" ");
        int[] array = new int[inputToken.length];
        for(int i = 0; i < inputToken.length; i++) {
            array[i] = Integer.parseInt(inputToken[i]);
        }

        System.out.println(longestConvexSubseq(array));
    }


    public static void testCasesLocal() {
        // 6
        longestConvexSubseqTest("2082 0 24719 1 383 4 20029 9 3781 16"); 
        // 14
        longestConvexSubseqTest("26601 31894 9 23371 1 0 1 4 9 16 25 19747 49 64 27576 100 121 647 2055 7354");
        // 12
        longestConvexSubseqTest("225 21553 25262 81 64 27687 16 1 4 25 25 25 36 6378 14180 4655 12749 81 10123 225 289 12818 400 576 625 7839 8250 1089 5020 3931");
        // 24
        longestConvexSubseqTest("17254 11868 2304 22469 1936 1600 6118 11420 26212 15497 177 7815 30279 576 30443 5273 32272 289 1154 196 18126 169 144 81 21902 21229 4 1 8870 15499 16 28446 49 9868 13402 81 20010 196 3301 361 529 22270 729 841 961 961 1156 19409 6232 2025 29910 2304 2304 2704 2916 5109 3364 12394 506 31821 3603 4337 5329 26106 4191 19045 28397 17197 6724 6889 6889 7225 7396 22954 9112 8464 8464 9025 16636 28083 10404 10609 20132 14326 14475 12769 12937 13456 26911 18504 15625 16129 2503 17424 19519 28415 3089 19321 6424 20164");
    }

    public static void algoScanner() {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        String[] input = scanner.nextLine().split(" ");
        scanner.close();
        int[] arr = new int[input.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }
        System.out.println(longestConvexSubseq(arr));
    }

    public static void main(String[] args) {
        algoScanner();
    }
}
