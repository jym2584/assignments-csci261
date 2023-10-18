import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class LongestIncreasingSubseqTimesDP {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input25.txt"));
        int length = Integer.parseInt(reader.readLine());
        String[] input = reader.readLine().split(" ");
        reader.close();
        int[] array = new int[length];
        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.parseInt(input[i]);
        }
        
        for (int i = 1; i < 36; i++) {
            int[] resizedArray = new int[i];
            System.arraycopy(array, 0, resizedArray, 0, i);
            long start = System.nanoTime();
            LongestIncreasingSubseqDP.incrSubseqDP(resizedArray);
            float duration = (float)(System.nanoTime() - start) / 1000000;
            System.out.println(String.format("%s",  duration));
        }

    }
}
