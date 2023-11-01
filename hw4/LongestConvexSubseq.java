public class LongestConvexSubseq {
    /**
     * Bottom up (non-recursive) approach to the longest increasing subsequence problem using dynamic programming
     * @param array input
     * @return length of longest subsequence
     */
    public static int incrSubseqDP(int[] array) {
        // initialize optimal solution/DP array 
        int[] OPT = new int[array.length];
        for (int i = 0; i < OPT.length; i++) {
            OPT[i] = 1; // minimum length of a subsequence is the element itself
        }

        for (int i = 0; i < array.length; i++) { // iterate through each element
            for (int j = 0; j < i; j++) {  // iterates through elements before the current i
                /**
                 * Determines whether this is a best solution if:
                 * 1. array[j] can be part of the increasing subsequence
                 * 2. The new subsequence is longer than the current i
                 */
                if (array[i] > array[j] && OPT[i] < (OPT[j] + 1)) {
                    OPT[i] = OPT[j] + 1;
                }
            }
        }

        // calculate maximum subsequence given DP array
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            if (OPT[i] > max) {
                max = OPT[i];
            }
        }

        return max;
    }

    public static void main(String[] args) {
        
    }
}
