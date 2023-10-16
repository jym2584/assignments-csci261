public class TestCases {
    public static String findMajorityElement(int[] arr, int threshold) {
        int majorityCandidate = findMajorityCandidate(arr, 0, arr.length - 1);
        int count = 0;
        int n = arr.length;

        // Count the occurrences of the majority candidate
        for (int num : arr) {
            if (num == majorityCandidate) {
                count++;
            }
        }

        if (count > n / threshold) {
            return "YES";
        } else {
            return "NO";
        }
    }

    // Helper function to find the majority candidate in a subarray
    private static int findMajorityCandidate(int[] arr, int low, int high) {
        if (low == high) {
            return arr[low];
        }

        // Divide the array into two halves
        int mid = (low + high) / 2;
        int leftMajority = findMajorityCandidate(arr, low, mid);
        int rightMajority = findMajorityCandidate(arr, mid + 1, high);

        // If the two halves have the same majority element, return it
        if (leftMajority == rightMajority) {
            return leftMajority;
        }

        // Otherwise, count the occurrences of both candidates and return the majority
        int leftCount = countOccurrences(arr, low, mid, leftMajority);
        int rightCount = countOccurrences(arr, mid + 1, high, rightMajority);

        return (leftCount > rightCount) ? leftMajority : rightMajority;
    }

    // Helper function to count the occurrences of an element in a subarray
    private static int countOccurrences(int[] arr, int low, int high, int element) {
        int count = 0;
        for (int i = low; i <= high; i++) {
            if (arr[i] == element) {
                count++;
            }
        }
        return count;
    }


    public static void main(String[] args) {
        String stringInput = "5 13 0 0 56 0 13 0 0 5";
        String[] input = stringInput.split(" ");
        int[] arr = new int[input.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }
        System.out.println(Majority.majorityElement(arr, 2));
        System.out.println(Majority.majorityElement(arr, 3));
    }
}
