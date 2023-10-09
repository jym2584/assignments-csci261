import java.util.Scanner;

public class WeightedInversions {
    private static int countAcrossMiddle(int[] array, int[] left, int[] right) {
        int countMiddle = 0;
        int currentLeft = 0;
        int currentRight = 0;
        while (true) {
            if (currentRight >= right.length) {
                currentRight = 0;
                currentLeft++;
            }
            if (currentLeft >= left.length) {
                break;
            }
            if (left[currentLeft] > right[currentRight]) {
                int distance = left.length - (currentLeft - currentRight);
                countMiddle += distance;
            }
            currentRight++;
        }
        return countMiddle;
    }

    public static int countingInversions(int[] array) {
        if (array.length <= 1) {
            return 0;
        }

        // determine what the middle should be
        int middle;
        int[] A, B;
        if (array.length % 2 == 0) { // even array
            middle = array.length / 2;
            A = new int[middle];
            B = new int[middle];
        } else { // odd array
            middle = (array.length - 1) / 2;
            A = new int[middle + 1];
            B = new int[middle];
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

        int countLeft = countingInversions(A);
        int countRight = countingInversions(B);
        int countMiddle = countAcrossMiddle(array, A, B);

        int numInversions = countLeft + countRight + countMiddle;
        return numInversions;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] numbers = new int[Integer.parseInt(scanner.nextLine())];
        String[] input = scanner.nextLine().split(" ");
        for (int i = 0; i < input.length; i++) {
            numbers[i] = Integer.parseInt(input[i]);
        }
        scanner.close();
        // int inversions = countingInversions(numbers);
        // System.out.println(inversions);

        // n^2 solution, gave up
        int count = 0;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] > numbers[j]) {
                    count += Math.abs(j - i);
                }
            }
        }
        System.out.println(count);
    }
}
