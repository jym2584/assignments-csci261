import java.util.Arrays;

public class AlgoUtils { // O(nlogn)

    /**
     * Merge 2 sorted arrays
     * O(n) time complexity
     * @param A sorted A
     * @param B sorted B
     * @return a sorted array 
     */
    private static int[] merge(int[] A, int[] B) {
        int[] merged = new int[A.length + B.length];

        int i = 0; // index for array a
        int j = 0; // index for array b
        int k = 0; // index for array `merged`

        // sort arrays into merged
        while (i < A.length && j < B.length) {
            int a = A[i];
            int b = B[j];
            if (a <= b) {
                merged[k] = a;
                i++;
            } else {
                merged[k] = b;
                j++;
            }
            k++;
        }

        int[] C = null; // array to merge into `merged`
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
            int c = C[m];
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
    public static int[] mergeSort(int[] array) {
        if (array.length == 0 || array.length == 1) {
            return array;
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

        // use recursion to merge sorted arrays
        int[] A_merged = mergeSort(A);
        int[] B_merged = mergeSort(B);

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
    public static int binarySearch(int[] array, int target, int start, int end) {
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
        int[] A = {12, 11, 13, 5, 6, 7};
        int[] sorted = mergeSort(A);
        System.out.println(Arrays.toString(sorted));
        System.out.println(binarySearch(sorted, 11, 0, sorted.length));
    }
}