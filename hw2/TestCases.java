import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class TestCases {
    public static Pair[] generatePairs(String filename) throws IOException {
        FileReader file = new FileReader(filename);
        BufferedReader reader = new BufferedReader(file);
        String firstLine = reader.readLine().strip();
        
        Pair[] pairs = new Pair[Integer.parseInt(firstLine)];
        String line = reader.readLine();
        int i = 0;
        while (line != null) {
            String[] parsed = line.strip().split(" ");
            pairs[i] = new Pair(Integer.parseInt(parsed[0]), Integer.parseInt(parsed[1]));
            i++;
            line = reader.readLine();
        }

        reader.close();
        file.close();

        return pairs;
    }

    public static void main(String[] args) throws IOException {
        Pair[] pairs = generatePairs("input1.txt");
        System.out.println(Arrays.toString(pairs));
    }
}
