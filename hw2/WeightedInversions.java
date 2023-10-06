import java.util.Arrays;

public class WeightedInversions {
    private static int countAcrossMiddle(int[] array, int[] left, int[] right) {
        int countMiddle = 0;
        int currentLeft = 0;
        int currentRight = 0;
        int currentArray = 0; // Track the current position in the original array

        System.out.println(String.format("array=%s, left=%s, right=%s", Arrays.toString(array), Arrays.toString(left), Arrays.toString(right)));
        while (currentLeft < left.length && currentRight < right.length) {
            if (left[currentLeft] <= right[currentRight]) { // shift scanning to right on left array there are no inversions on current element in left
                array[currentArray] = left[currentLeft];
                currentLeft++;
            } else { // inversion
                System.out.println(String.format("INVERSION: (%s, %s) %s", left[currentLeft], right[currentRight], Arrays.toString(array)));
                array[currentArray] = right[currentRight];
                System.out.println(String.format("INVERSION AFTER: %s,", Arrays.toString(array)));
                currentRight++;
                countMiddle += left.length - currentLeft;
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
        int inversions = countingInversions(new int[]{2, 5, 3, 1, 4});
        System.out.println(inversions);
    }
}
