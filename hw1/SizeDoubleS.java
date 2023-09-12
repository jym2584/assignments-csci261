public class SizeDoubleS {
    
    /**
     * Checks if a number exists in the array
     * O(n)
     * @param array input array
     * @param num number to find
     * @return true if found; false otherwise
     */
    public static boolean checkIfExists(double[] array, int num) {
        for (int i = 0; i < array.length; i++) {
            double el = array[i];
            if (el == num) {
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * @param array
     * @return size of set
     */
    public static int sizeDoubleS(double[] array) {
        double[][] set = new double[array.length * 2][array.length];
        double[] sorted = MergeSortDouble.mergeSort(array); 


        for(int i = 0; i < array.length * 2; i++) {

        }
        
        return 0;
    }

    public static void main(String[] args) {
        int n = 3;
        double[] S = {1, 2, 4};

    }
}
