import java.util.Arrays;
import java.util.Scanner;

public class WeightedInversions {
    private static long countAcrossMiddle(long[] array, long[] left, long[] right) {
        long countMiddle = 0;
        int currentLeft = 0;
        int currentRight = 0;
        int currentArray = 0; // Track the current position in the original array

        System.out.println(String.format("array=%s, left=%s, right=%s", Arrays.toString(array), Arrays.toString(left), Arrays.toString(right)));
        while (currentLeft < left.length && currentRight < right.length) {
            if (left[currentLeft] <= right[currentRight]) { // shift scanning to right on left array there are no inversions on current element in left
                array[currentArray] = left[currentLeft];
                currentLeft++;
            } else { // inversion
                array[currentArray] = right[currentRight];
                currentRight++;
                int distance = left.length - (currentLeft - currentRight);
                countMiddle += distance;
                System.out.println(String.format("countMiddle: %s", countMiddle));
            }
            currentArray++;
        }
        while (currentLeft < left.length) {
            array[currentArray++] = left[currentLeft++];
        }
    
        while (currentRight < right.length) {
            array[currentArray++] = right[currentRight++];
        }

        return countMiddle;
    }

    public static long countingInversions(long[] array) {
        if (array.length <= 1) {
            return 0;
        }

        // determine what the middle should be
        int middle;
        long[] A, B;
        if (array.length % 2 == 0) { // even array
            middle = array.length / 2;
            A = new long[middle];
            B = new long[middle];
        } else { // odd array
            middle = (array.length - 1) / 2;
            A = new long[middle + 1];
            B = new long[middle];
        }

        // fill in both arrays
        int i = 0; // index for array
        for (int j = 0; j < A.length; j++) {
            A[j] = array[i];
            i++;
        }

        for (int j = 0; j < B.length; j++) {
            B[j] = array[i];
            i++;
        }

        long countLeft = countingInversions(A);
        long countRight = countingInversions(B);
        long countMiddle = countAcrossMiddle(array, A, B);

        long numInversions = countLeft + countRight + countMiddle;
        return numInversions;
    }

    public static void main(String[] args) {
        // setup
        // Scanner scanner = new Scanner(System.in);
        // long[] numbers = new long[Integer.parseInt(scanner.nextLine())];
        // String[] input = scanner.nextLine().split(" ");
        // for (int i = 0; i < input.length; i++) {
        //     numbers[i] = (long)Long.parseLong(input[i]);
        // }
        // scanner.close();
        
        // testing
        long[] numbers = new long[] {2, 5, 3, 1, 4};
        // weighted inversions problem
        long inversions = countingInversions(numbers);
        System.out.println(inversions);

        // n^2 solution, gave up
    //     long count = 0;
    //     for (int i = 0; i < numbers.length; i++) {
    //         for (int j = i + 1; j < numbers.length; j++) {
    //             if (numbers[i] > numbers[j]) {
    //                 count += (long)(j - i);
    //             }
    //         }
    //     }
    //     System.out.println(count);
    }
}
