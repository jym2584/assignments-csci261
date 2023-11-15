import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class DoubleKnapsackSolution {
    public static void doubleKnapsackIndivisible(Item[] items, int maxWeightOne, int maxWeightTwo) {
        //int maxCost;
        // initialize DP array
        int[][][] OPT = new int[items.length + 1][maxWeightOne + 1][maxWeightTwo + 1];
        int[][][] itemsOPT = new int[items.length + 1][maxWeightOne + 1][maxWeightTwo + 1];
        // for (int i = 1; i < maxWeight + 1; i++) { OPT[0][i] = 0; } // initialize maxWeight
        // for (int i = 1; i < items.length + 1; i++) { OPT[i][0] = 0; } // initialize items
        
        for (int itemIndex = 1; itemIndex <= items.length; itemIndex++) {  // iterate over each item 
            for (int currentWeightOne = 0; currentWeightOne <= maxWeightOne; currentWeightOne++) { // iterate over knapsack 1
                for (int currentWeightTwo = 0; currentWeightTwo <= maxWeightTwo; currentWeightTwo++) { // iterate over knapsack 2
                    OPT[itemIndex][currentWeightOne][currentWeightTwo] = OPT[itemIndex - 1][currentWeightOne][currentWeightTwo]; // maximum value obtained without including the current item
                    int currentCostAchiveable = OPT[itemIndex][currentWeightOne][currentWeightTwo];
                    Item item = items[itemIndex - 1]; // get the current item
                    // Check if the item can be placed in knapsack one or two
                    if (item.getWeight() <= currentWeightOne) { // knapsack one
                        int cost = OPT[itemIndex - 1][currentWeightOne - item.getWeight()][currentWeightTwo] + item.getCost(); // calculate current cost from DP array
                        if (cost > currentCostAchiveable) {
                            OPT[itemIndex][currentWeightOne][currentWeightTwo] = cost; // add the cost of the item in the first knapsack
                            itemsOPT[itemIndex][currentWeightOne][currentWeightTwo] = 1; // Use DP to store the item that was placed on knapsack one in the DP array
                        }
                    } else if (item.getWeight() <= currentWeightTwo) { // knapsack two
                        int cost = OPT[itemIndex - 1][currentWeightOne][currentWeightTwo - item.getWeight()] + item.getCost(); // calculate current cost from DP array
                        if (cost > currentCostAchiveable) {
                            OPT[itemIndex][currentWeightOne][currentWeightTwo] = cost; // add the cost of the item in the second knapsack
                            itemsOPT[itemIndex][currentWeightOne][currentWeightTwo] = 2; // Use DP to store the item that was placed on knapsack one in the DP array
                        }
                    }
                }
            }
        }
    
        // Use itemsOPT to find the items that was placed in each knapsack
        int weightOne = maxWeightOne;
        int weightTwo = maxWeightTwo;
        int[] itemsKnapsackOne = new int[items.length + 1];
        int[] itemsKnapsackTwo = new int[items.length + 1];
        String stdoutKnapsackOne = "";
        String stdoutKnapsackTwo = "";

        for (int itemIndex = items.length; itemIndex >= 1; itemIndex--) {
            int knapsack = itemsOPT[itemIndex][weightOne][weightTwo];
            Item item = items[itemIndex - 1];
    
            if (knapsack == 1) {
                itemsKnapsackOne[item.getId()] = item.getId();
                weightOne -= item.getWeight();
            } else if (knapsack == 2) {
                itemsKnapsackTwo[item.getId()] = item.getId();
                weightTwo -= item.getWeight();
            }
        }

        // print formatting
        for (int val: itemsKnapsackOne) {
            if (val > 0) {
                stdoutKnapsackOne += String.format("%s ", val);
            }
        }
        stdoutKnapsackOne = stdoutKnapsackOne.strip();

        for (int val: itemsKnapsackTwo) {
            if (val > 0) {
                stdoutKnapsackTwo += String.format("%s ", val);
            }
        }
        stdoutKnapsackTwo = stdoutKnapsackTwo.strip();
    
        System.out.println(stdoutKnapsackOne);
        System.out.println(stdoutKnapsackTwo);
        // maxCost = OPT[items.length][maxWeightOne][maxWeightTwo];
        // return maxCost;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////// TESTS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void doubleKnapsackFileTests(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String[] firstInput = reader.readLine().split(" "); // <numItems> <maxWeightOne> <maxWeightTwo>
        Item[] items = new Item[Integer.parseInt(firstInput[0])];
        int maxWeightOne = Integer.parseInt(firstInput[1]);
        int maxWeightTwo = Integer.parseInt(firstInput[2]);
        int id = 1;
        for (int i = 0; i < items.length; i++) {
            String[] itemInput = reader.readLine().split(" ");
            items[i] = new Item(Integer.parseInt(itemInput[0]), Integer.parseInt(itemInput[1]), id);
            id++;
        }
        reader.close();
        doubleKnapsackIndivisible(items, maxWeightOne, maxWeightTwo);
    }

    public static void testsLocal() throws IOException {
        doubleKnapsackFileTests("problem_3_inputs/input-4.1.txt");
        doubleKnapsackFileTests("problem_3_inputs/input-4.2.txt");
        doubleKnapsackFileTests("problem_3_inputs/input-4.3.txt");
        doubleKnapsackFileTests("problem_3_inputs/input-4.4.txt");
        doubleKnapsackFileTests("problem_3_inputs/input-4.5.txt");

    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////// END TESTS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void stdinAlgo() {
        Scanner scanner = new Scanner(System.in);
        String[] firstInput = scanner.nextLine().split(" "); // <numItems> <maxWeightOne> <maxWeightTwo>
        Item[] items = new Item[Integer.parseInt(firstInput[0])];
        int maxWeightOne = Integer.parseInt(firstInput[1]);
        int maxWeightTwo = Integer.parseInt(firstInput[2]);
        int id = 1;
        for (int i = 0; i < items.length; i++) {
            String[] itemInput = scanner.nextLine().split(" ");
            items[i] = new Item(Integer.parseInt(itemInput[0]), Integer.parseInt(itemInput[1]), id);
            id++;
        }
        scanner.close();

        doubleKnapsackIndivisible(items, maxWeightOne, maxWeightTwo);
    }

    public static void main(String[] args) throws IOException {
        //testsLocal();
        stdinAlgo();
    }
}