import java.util.Arrays;

public class AlgoUtilsDouble { // O(nlogn)

    /**
     * Merge 2 sorted arrays
     * O(n) time complexity
     * @param A sorted A
     * @param B sorted B
     * @return a sorted array 
     */
    private static double[] merge(double[] A, double[] B) {
        double[] merged = new double[A.length + B.length];

        int i = 0; // index for array a
        int j = 0; // index for array b
        int k = 0; // index for array `merged`

        // sort arrays into merged
        while (i < A.length && j < B.length) {
            double a = A[i];
            double b = B[j];
            if (a <= b) {
                merged[k] = a;
                i++;
            } else {
                merged[k] = b;
                j++;
            }
            k++;
        }

        double[] C = null; // array to merge into `merged`
        int l = 0; // index for array c
        // determine which array has remainders and to be merged into `merged`
        if (i < A.length) {
            C = A;
            l = i;
        } else if (j < B.length) {
            C = B;
            l = j;
        }

        // merge remaining array
        for (int m = l; m < C.length; m++) {
            double c = C[m];
            merged[k] = c;
            k++; 
        }

        return merged;
    }

    /**
     * Merge sort algorithm
     * O(nlogn)
     * @param array initial input
     * @return sorted array
     */
    public static double[] mergeSort(double[] array) {
        if (array.length == 0 || array.length == 1) {
            return array;
        }

        // determine what the middle should be
        int middle;
        double[] A, B;
        if (array.length % 2 == 0) { // even array
            middle = array.length / 2;
            A = new double[middle];
            B = new double[middle];
        } else { // odd array
            middle = (array.length - 1) / 2;
            A = new double[middle + 1];
            B = new double[middle];
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

        // use recursion to merge sorted arrays
        double[] A_merged = mergeSort(A);
        double[] B_merged = mergeSort(B);

        return merge(A_merged, B_merged);
    }

    /**
     * Uses binary search to find the highest value 
     * O(logn) time complexity
     * @param array array to search
     * @param target target value to find
     * @param start starting index
     * @param end ending index
     * @return index of value at array
     */
    public static double binarySearch(double[] array, double target, int start, int end) {
        int middle = (start + end) / 2; // middle index
        if (array[middle] == target) {
            return middle;
        } else if (array[middle] < target) {
            return binarySearch(array, target, middle + 1, end); // search from the right
        } else {
            return binarySearch(array, target, start, middle - 1); // search from the left
        }
    }
    public static void main(String[] args) {
        double[] A = {12.0, 11.0, 13.0, 5.0, 6.0, 7.0};
        double[] sorted = mergeSort(A);
        System.out.println(Arrays.toString(sorted));
        System.out.println(binarySearch(sorted, 11.0, 0, sorted.length));
    }
}