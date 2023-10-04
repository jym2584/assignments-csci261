import java.util.Arrays;

public class WeightedInversions {
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

    private static int countAcrossMiddle(int[] array, int[] left, int[] right) {
        int countMiddle = 0;
        int currentLeft = 0;
        int currentRight = 0;
        int currentArray = 0; // Track the current position in the original array
        int distance = 0; // Track the current distance between the two arrays
      
        while (currentLeft < left.length && currentRight < right.length) {
          if (left[currentLeft] <= right[currentRight]) { // calculation for no inversions
            array[currentArray] = left[currentLeft];
            currentLeft++;
          } else { // inversion
            array[currentArray++] = right[currentRight++];
      
            // Update the distance
            distance += left.length - currentLeft;
      
            // Calculate the weight of the inversion
            int weight = distance;
      
            // Add the weight of the inversion to the total count
            countMiddle += weight;
          }
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
