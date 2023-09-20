import java.util.Arrays;

public class SizeDoubleS {
    
    /**
     * 
     * @param array
     * @return size of set
     */
    public static int sizeDoubleS(double[] array) {
        // append elements to array
        double possible_length = Math.pow(array.length, 2);
        double[] possible = new double[(int)possible_length];

        int i = 0;
        for (int j = 0; j < array.length; j++) {
            for (int k = 0; k < array.length; k++) {
                double sum = array[j] + array[k];
                possible[i] = sum;
                i++;
            }
        }

        // sort and find duplicates
        double[] sorted = AlgoUtilsDouble.mergeSort(possible);
        System.out.println(Arrays.toString(sorted));
        int count = 1; // always count the first element because it is guaranteed to be unique
                        // the logic below compares if the current element is the same as the next element
                        // eg: 2.0, 3.0 == 2 unique counts; 1 + [is 2.0 different from 3.0? yes, then increment count]
                        // 2.0, 2.0 == 1 unique count; 1 + [is 2.0 different from 2.0? no, do not increment count]
        for (int j = 0; j < sorted.length - 1; j++) {
            if (sorted[j] != sorted[j + 1]) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int n = 3;
        String[] input = "3 6 7 5 2 9 1 0 8 4".split(" ");
        double[] S = new double[input.length];
        for (int i = 0; i < input.length; i++) {
            S[i] = Double.parseDouble(input[i]);
        }

        System.out.println(sizeDoubleS(S));

    }
}
