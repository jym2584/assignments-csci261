public class TestCases {
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
