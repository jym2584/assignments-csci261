import java.util.Arrays;

public class WeightedInversionsCopy {
    public static Object[] mergeSortWeightedInversions(int[] array) {
        if (array.length <= 1) {
          return new Object[] {0, array};
        }
      
        // Determine the middle of the array
        int middle = array.length / 2;
      
        // Recursively sort the left and right halves of the array
        int[] left = (int[])mergeSortWeightedInversions(Arrays.copyOfRange(array, 0, middle))[1];
        int[] right = (int[])mergeSortWeightedInversions(Arrays.copyOfRange(array, middle, array.length))[1];
      
        // Merge the sorted left and right halves of the array
        int[] mergedArray = new int[array.length];
        int currentLeft = 0;
        int currentRight = 0;
        int distance = 0;
        int count = 0;
      
        while (currentLeft < left.length && currentRight < right.length) {
          if (left[currentLeft] <= right[currentRight]) {
            mergedArray[currentLeft + currentRight] = left[currentLeft];
            currentLeft++;
          } else {
            // Inversion
            mergedArray[currentLeft + currentRight] = right[currentRight];
            currentRight++;
      
            // Update the distance
            distance += left.length - currentLeft;
      
            // Calculate the weight of the inversion
            int weight = distance;
      
            // Add the weight of the inversion to the total count
            count += weight;
          }
        }
      
        // Add any remaining elements from the left and right arrays
        while (currentLeft < left.length) {
          mergedArray[currentLeft + currentRight] = left[currentLeft];
          currentLeft++;
        }
      
        while (currentRight < right.length) {
          mergedArray[currentLeft + currentRight] = right[currentRight];
          currentRight++;
        }
      
        return new Object[]{count, mergedArray};
      }
    public static void main(String[] args) {
        int[] inversions = mergeSortWeightedInversions(new int[]{2, 5, 3, 1, 4});
        System.out.println(inversions);
    }
}