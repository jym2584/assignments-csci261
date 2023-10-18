public class Hopscotch {

    /**
     * Determines whether an element at a specified index is within the bounds of an array
     * @param array 
     * @param index
     * @return true if there exists a value that is the length of an array
     */
    public static boolean isValid(int[] array, int index) {
        if (index > array.length) {
            return false;
        }
        return true;
    }

    /**
     * 
     * @param array
     * @param index
     * @param otherIndex
     * @return index of highest value
     */
    public static int getHigherOfTheTwo(int[] array, int index, int otherIndex) {
        if (array[index] > array[otherIndex]) {
            return index;
        }
        return otherIndex;
    }

    public static int getHighestValue(int[] array, int index, int otherIndex) throws Exception {
        if (isValid(array, index) && isValid(array, otherIndex)) {
            return getHigherOfTheTwo(array, index, otherIndex);
        } else if (isValid(array, index) && !isValid(array, otherIndex)) {
            return index;
        } else {
            throw new Exception("idk what gets thrown here");
        }
    }

    public static void hopScotchProblem(int[] array) throws Exception {
        int i = 0;
        while(i > array.length) {
            int highestIndex = getHighestValue(array, i + 2, i + 3);
            i = highestIndex;
        }
    }
    public static void main(String[] args) {
        
    }
}