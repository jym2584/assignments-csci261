import java.util.Scanner;

public class Majority {
    // /**
    //  * Counts the number of occurrences of a specific number in a subarray
    //  * @param array array input
    //  * @param num number to count instances of 
    //  * @param start index
    //  * @param end index
    //  * @return number of occurrences
    //  */
    // private static int countInstances(int[] array, int num, int start, int end) {
    //     int count = 0;
    //     for (int i = start; i < end; i++) {
    //         if (array[i] == num) {
    //             count++;
    //         }
    //     }
    //     return count;
    // }
    
    // /**
    //  * Divide and conquer algorithm to find the majority element in the array
    //  * @param array array input
    //  * @param start index
    //  * @param end index
    //  * @return majority element
    //  */
    // private static int findMajorityElement(int[] array, int start, int end) {
    //     if (start == end) {
    //         return array[start];
    //     }

    //     int mid = (start + end) / 2;
    //     int leftMajority = findMajorityElement(array, start, mid);
    //     int rightMajority = findMajorityElement(array, mid + 1, end);

    //     int countLeft = countInstances(array, leftMajority, start, mid);
    //     int countRight = countInstances(array, rightMajority, mid + 1, end);

    //     if (countLeft > countRight || countLeft == countRight) {
    //         return leftMajority;
    //     }
    //     return rightMajority;
    // }

    // /**
    //  * Majority element problem 
    //  * @param array input
    //  * @param numTimes determinant number if there exists an integer that occurs in the sequence more than array.length/numTimes
    //  * @return majority element
    //  */
    // public static String majorityElement(int[] array, int numTimes) {
    //     int element = findMajorityElement(array, 0, array.length - 1);
    //     int count = countInstances(array, element, 0, array.length - 1);

    //     if (count > array.length / numTimes) {
    //         return "YES";
    //     }

    //     return "NO";
    // }
    
    /**
     * Majority element problem
     * @param array
     * @param numTimes
     * @return
     */
    public static String majorityElement(int[] array, int numTimes) {
        // get the highest value
        int highest = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > highest) {
                highest = array[i];
            }
        }

        // initialize "set" array with number of instances at each element
        int[] set = new int[highest + 1];

        for (int i = 0; i < array.length; i++) {
            int element = array[i];
            set[element]++;
        }

        // find if there exists an integer that occurs in the sequence more than array.length/numTimes
        for (int i = 0; i < array.length; i++) {
            int element = array[i];
            if (set[element] > (array.length / numTimes)) {
                return "YES";
            }
        }
        return "NO";
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        String[] input = scanner.nextLine().split(" ");
        scanner.close();
        int[] arr = new int[input.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }
        System.out.println(majorityElement(arr, 2));
        System.out.println(majorityElement(arr, 3));
    }
}