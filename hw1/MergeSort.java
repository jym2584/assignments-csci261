package hw1;

public class MergeSort { // O(nlogn)

    /**
     * Merge 2 sorted arrays
     * O(n) time complexity
     * @param A sorted A
     * @param B sorted B
     * @return a sorted array 
     */
    public static int[] merge(int[] A, int[] B) {
        int[] merged = new int[A.length + B.length];

        int i = 0; // index for array a
        int j = 0; // index for array b
        int k = 0; // index for array `merged`

        // sort arrays into merged
        while (i < A.length && j < B.length) {
            int a = A[i];
            int b = B[i];
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
        int c = 0; // starting index of index (not completely iterated over)
        // determine which array has remainders and to be merged into `merged`
        if (i < A.length) {
            C = A;
            c = i;
        } else if (j < B.length) {
            C = B;
            c = j;
        }

        // merge remaining array
        for (int l = c; l < merged.length; l++) {
            
        }

        return merged;
    }

    public static void main(String[] args) {
        
    }
}