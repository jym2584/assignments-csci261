import java.util.Scanner;

public class Hopscotch {

    /**
     * Hopscotch problem using dynamic programming (memorization)
     * @param array input
     * @return largest sum
     */
    public static int hopScotchProblem(int[] array) {
        // initialize dp array
        int[] dp = new int[array.length];
        dp[0] = array[0];
        // element at 1 should be voided since index 1 from the starting point at index 0 is not considered
        dp[2] = array[0] + array[2];
        
        // iterate through possible i + 2 and i + 3
        for (int i = 3; i < array.length; i++) {
            int currentElement = array[i];
            int secondPosition = dp[i - 2]; // "position" i + 2
            int thirdPosition = dp[i - 3]; // "position" i + 3
            int toAdd; 
            if (secondPosition > thirdPosition) {
                toAdd = secondPosition;
            } else {
                toAdd = thirdPosition;
            }
            dp[i] = currentElement + toAdd;
        }
        
        // grab the higher of the two solved solutions from dynamic programming
        if (dp[array.length - 1] > dp[array.length - 2]) {
            return dp[array.length - 1];
        }
        return dp[array.length - 2];
    }
    // /**
    //  * TEST FUNCTION
    //  */
    // public static void testLIS(String[] input) {
    //     System.out.println(String.format("INPUT: %s", Arrays.toString(input)));
    //     int[] array = new int[input.length];

    //     for (int i = 0; i < input.length; i++) {
    //         array[i] = Integer.parseInt(input[i]);
    //     }

    //     System.out.println(hopScotchProblem(array));
    // }

    // /**
    //  * TEST FUNCTION
    //  */
    // public static void testLISlocal() {
    //     String[] input = "1 5 2 3 6 1 8".split(" ");
    //     testLIS(input); // 17
    //     input = "1 5 2 6 3 8 1".split(" ");
    //     testLIS(input); // 15
    //     input = "4 16 18 7 14 10 4 8 19 11 2 8 20 13 1 7 2 12 14 2".split(" ");
    //     testLIS(input); // 102
    //     input = "32 6 59 56 23 67 75 15 58 89 26 92 62 77 76 81 46 20 61 70 66 90 98 63 20 16 73 95 42 63 7 73 68 66 28 90 84 54 56 41 42 34 32 55 10 59 35 7 79 47 28 96 89 25 58 60 93 82 54 34 44 60 58 11 77 37 100 60 90 8 52 83 41 84 37 50 42 23 8 72 70 35 67 10 59 76 69 3 57 22 36 100 33 45 62 10 81 14 21 22".split(" ");
    //     testLIS(input); // 3016
    // }

    public static void main(String[] args) {
        // testLISlocal();
        Scanner scanner = new Scanner(System.in);
        int length = Integer.parseInt(scanner.nextLine());
        String[] input = scanner.nextLine().split(" ");
        scanner.close();
        int[] array = new int[length];
        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.parseInt(input[i]);
        }
        System.out.println(hopScotchProblem(array));
    }
}