import java.util.Scanner;

public class LongestIncreasingSubseqDP {

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


    public static void testLIS(String[] input) {
        int[] array = new int[input.length];

        for (int i = 0; i < input.length; i++) {
            array[i] = Integer.parseInt(input[i]);
        }

        System.out.println(incrSubseqDP(array));
    }

    public static void testLISlocal() {
        String[] input = "41 18467 6334 26500 19169 15724 11478 29358 26962 24464".split(" ");
        testLIS(input); // 4
        input = "25053 4601 4540 20255 23073 17419 10282 3621 32092 945".split(" ");
        testLIS(input); // 4
        input = "25197 18779 4132 30465 18162 26355 16158 5893 4938 25342 29960 3947 4841 1006 9238 30953 28094 15299 30936 1561".split(" ");
        testLIS(input); // 5
        input = "25285 14074 27712 24814 1744 1305 1890 25160 24767 23184 25967 5136 12766 21773 18602 7562 31283 21260 14829 14135 22276 26702 31564 25329 10906 30343 3573 27191 28996 15960".split(" ");
        testLIS(input); // 10
    }


    public static void main(String[] args) {
       //testLISlocal();
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        String[] input = scanner.nextLine().split(" ");
        scanner.close();
        int[] arr = new int[input.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }
        System.out.println(incrSubseqDP(arr));
    }
}
