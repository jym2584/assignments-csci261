import java.util.Scanner;

public class SmallestTwo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // initialize array
        int[] array = new int[scanner.nextInt()]; // initialize array based on first line from stdin
        int i = 0;
        while (scanner.hasNext()) { // initialize values on array
            array[i] = scanner.nextInt();
            i++;
        }

        // initialize first ands second smallest
        Integer smallestFirst = array[0];
        Integer smallestSecond = array[0];

        // find the first smallest
        for (int j = 0; j < array.length; j++) {
            int current = array[j];
            if (smallestFirst > current) {
                smallestFirst = current;
            }
        }

        // find the second smallest
        for (int j = 0; j < array.length; j++) {
            int current = array[j];
            if (smallestSecond > current && current != smallestFirst) {
                smallestSecond = current;
            }
        }
        System.out.println(String.format("%s\n%s", smallestFirst, smallestSecond));

        scanner.close();
    }
}