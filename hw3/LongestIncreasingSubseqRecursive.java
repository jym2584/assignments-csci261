import java.util.Scanner;

/**
 * Fails 5th test case for solving this problem using just recursion (no required complexity)
 */
public class LongestIncreasingSubseqRecursive {

    /**
     * Longest Increasing subsequence problem using the top down (recursion) approach
     * @param j calculates possible values up to length j
     * @param A array input
     * @param memory incorporates memorization to store previously calculated results to avoid redundant recursive calls
     * @return count of longest increasing subsequence
     */
    public static int incrSubseqRecursive(int j, int[] A) {
        int maxLength = 1; // minimum length of a subsequence is the element itself
        for (int i = 1; i < j; i++) {
            int length = 1 + incrSubseqRecursive(i, A); // minimum length of a subsequence is the element itself & recursively calculates the length of the subsequence up to the current index
            if (A[i - 1] < A[j - 1] && length > maxLength) { // find longest subsequence
                maxLength = length;
            }
        }

        return maxLength;
    }

    // /**
    //  * TEST FUNCTION
    //  */
    // public static void testLIS(String[] input) {
    //     int[] array = new int[input.length];

    //     for (int i = 0; i < input.length; i++) {
    //         array[i] = Integer.parseInt(input[i]);
    //     }

    //     System.out.println(incrSubseqRecursive(array.length - 1, array));
    // }

    // /**
    //  * TEST FUNCTION
    //  */
    // public static void testLISlocal() {
    //     String[] input = "41 18467 6334 26500 19169 15724 11478 29358 26962 24464".split(" ");
    //     testLIS(input); // 4
    //     input = "25053 4601 4540 20255 23073 17419 10282 3621 32092 945".split(" ");
    //     testLIS(input); // 4
    //     input = "25197 18779 4132 30465 18162 26355 16158 5893 4938 25342 29960 3947 4841 1006 9238 30953 28094 15299 30936 1561".split(" ");
    //     testLIS(input); // 5
    //     input = "25285 14074 27712 24814 1744 1305 1890 25160 24767 23184 25967 5136 12766 21773 18602 7562 31283 21260 14829 14135 22276 26702 31564 25329 10906 30343 3573 27191 28996 15960".split(" ");
    //     testLIS(input); // 10
    // }

    public static void main(String[] args) {
        //testLISlocal();
        Scanner scanner = new Scanner(System.in);
        int length = Integer.parseInt(scanner.nextLine());
        String[] input = scanner.nextLine().split(" ");
        scanner.close();
        int[] array = new int[length];
        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.parseInt(input[i]);
        }
        System.out.println(incrSubseqRecursive(array.length - 1, array));
    }
}
