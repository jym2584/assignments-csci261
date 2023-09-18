import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class TestCases {
    public static void plantersTest() throws IOException {
        LinkedHashMap<String, String> files = new LinkedHashMap<>();
        files.put("input1.txt", "NO");
        files.put("input2.txt", "YES");
        files.put("input3.txt", "NO");
        files.put("input4.txt", "YES");

        for (Map.Entry<String, String> entry : files.entrySet()) {
            FileReader file = new FileReader(entry.getKey());
            BufferedReader reader = new BufferedReader(file);
            String[] firstLine = reader.readLine().strip().split(" ");
            String[] secondLine = reader.readLine().strip().split(" ");
            String[] thirdLine = reader.readLine().strip().split(" ");
            // create arrays based on firstLine from stdin
            int[] S = new int[Integer.parseInt(firstLine[0])];
            int[] T = new int[Integer.parseInt(firstLine[1])];
        
            // fill both arrays
            for (int i = 0; i < S.length; i++) {
                S[i] = Integer.parseInt(secondLine[i]);
            }

            for (int i = 0; i < T.length; i++) {
                T[i] = Integer.parseInt(thirdLine[i]);
            }

            // get result
            System.out.println("Expected:" + entry.getValue() + ", Actual: " + Planters.planters(S, T));
        }
    }

    public static void main(String[] args) throws IOException {
        plantersTest();
    }
}
