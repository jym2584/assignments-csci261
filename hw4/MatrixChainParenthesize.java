import java.util.Arrays;
import java.util.Scanner;

public class MatrixChainParenthesize {

    public static void print2Darray(int[][] array) {
        for(int[] arr: array) {
            System.out.println(Arrays.toString(arr));
        }
    }

    /**
     * Matrix Chain Multiplication problem
     * @param array
     * @return
     */
    public static void matrixChainMultiplication(int[] array) {
        // initialize OPT array
        int[][] costOPT = new int[array.length][array.length]; // DP array to compute the steps of a matrix
        int[][] seqOPT = new int[array.length][array.length]; // FP array to store information about how matricies are split to achieve the minimum cost 

        // psuedocode converted from lecture slides
        for (int d = 1; d < array.length; d++) {
            for (int i = 1; i < array.length - d; i++) {
                int j = i + d;
                costOPT[i][j] = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {
                    int cost = costOPT[i][k] + costOPT[k + 1][j] + array[i - 1] * array[k] * array[j];

                    if (costOPT[i][j] > cost) {
                        costOPT[i][j] = cost;
                        seqOPT[i][j] = k; // add matrix
                    }
                }
            }
        }

        System.out.println(costOPT[1][array.length - 1]);
        printOptimalMatrix(seqOPT, 1, array.length - 1);
    }

    /**
     * Print optimial parenthesizing of matrices
     * @param seqOPT
     * @param i
     * @param j
     */
    public static void printOptimalMatrix(int[][] seqOPT, int i, int j) {
        if (i == j) {
            System.out.print("A" + i);
        } else {
            System.out.print("( ");
            printOptimalMatrix(seqOPT, i, seqOPT[i][j]);
            System.out.print(" x ");
            printOptimalMatrix(seqOPT, seqOPT[i][j] + 1, j);
            System.out.print(" )");
        }
    }

    public static void algoScanner() {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine(); // ignore the first line
        String[] input = scanner.nextLine().split(" ");
        scanner.close();
        int[] arr = new int[input.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }
        matrixChainMultiplication(arr);
    }

    public static void main(String[] args) {
        algoScanner();
    }
}
