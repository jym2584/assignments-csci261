import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class DoubleKnapsack {
    
    public static void print2Darray(int[][] array) {
        for (int[] arr: array) {
            System.out.println(Arrays.toString(arr));
        }
    }
    /**
     * Knapsack problem single backpack
     * @param items list of items, where each item has a weight and a cost
     * @param maxWeight max weight of the backpack
     * @return maximum cost of knapsack
     */
    public static int knapsackIndivisible(Item[] items, int maxWeight) {
        int maxCost;
        // initialize DP array
        int[][] OPT = new int[items.length + 1][maxWeight + 1];
        // for (int i = 1; i < maxWeight + 1; i++) { OPT[0][i] = 0; } // initialize maxWeight
        // for (int i = 1; i < items.length + 1; i++) { OPT[i][0] = 0; } // initialize items
        
        // populate dp table
        for (int currentWeight = 1; currentWeight < maxWeight + 1; currentWeight++) { // iterate over possible weights
            for (int itemIndex = 1; itemIndex < items.length + 1; itemIndex++) { // iterate over items
                OPT[itemIndex][currentWeight] = OPT[itemIndex-1][currentWeight]; // maximum value obtained without including the current item
                Item item = items[itemIndex - 1]; // get the current item
                if (item.getWeight() <= currentWeight && (OPT[itemIndex-1][currentWeight-item.getWeight()] + item.getCost()) > OPT[itemIndex][currentWeight]) { // check if including the current item would result in a higher cost than not including it
                    OPT[itemIndex][currentWeight] = OPT[itemIndex - 1][currentWeight - item.getWeight()] + item.getCost(); // update maximum value by including the current item 
                }
            }
        }
        maxCost = OPT[items.length][maxWeight];
        return maxCost;
    }

    public static int doubleKnapsackIndivisible(Item[] items, int maxWeightOne, int maxWeightTwo) {
        int maxCost;
        // initialize DP array
        int[][][] OPT = new int[items.length + 1][maxWeightOne + 1][maxWeightTwo + 1];
        // for (int i = 1; i < maxWeight + 1; i++) { OPT[0][i] = 0; } // initialize maxWeight
        // for (int i = 1; i < items.length + 1; i++) { OPT[i][0] = 0; } // initialize items
        
        // populate dp table
        for (int itemIndex = 1; itemIndex <= items.length; itemIndex++) { // iterate over each item 
            for (int currentWeightOne = 0; currentWeightOne <= maxWeightOne ; currentWeightOne++) { // iterate over knapsack 1
                for (int currentWeightTwo = 0; currentWeightTwo <= maxWeightTwo; currentWeightTwo++) { // iterate over knapsack 2
                    OPT[itemIndex][currentWeightOne][currentWeightTwo] = OPT[itemIndex - 1][currentWeightOne][currentWeightTwo]; // maximum value obtained without including the current item
                    int currentCostAchiveable = OPT[itemIndex][currentWeightOne][currentWeightTwo];
                    Item item = items[itemIndex - 1]; // get the current item
                    // Check if the item can be placed in knapsack one or two
                    if (item.getWeight() <= currentWeightOne) { // knapsack one
                        int cost = OPT[itemIndex - 1][currentWeightOne - item.getWeight()][currentWeightTwo] + item.getCost(); // calculate current cost from DP array
                        if (cost > currentCostAchiveable) {
                            OPT[itemIndex][currentWeightOne][currentWeightTwo] = cost; // add the cost of the item in the first knapsack
                        }
                    }else if (item.getWeight() <= currentWeightTwo) {  // knapsack two
                        int cost = OPT[itemIndex - 1][currentWeightOne][currentWeightTwo - item.getWeight()] + item.getCost(); // calculate current cost from DP array
                        if (cost > currentCostAchiveable) {
                            OPT[itemIndex][currentWeightOne][currentWeightTwo] = cost; // add the cost of the item in the second knapsack
                        }
                    }
                }
            }
        }
        maxCost = OPT[items.length][maxWeightOne][maxWeightTwo];
        return maxCost;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////// TESTS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void doubleKnapsackFileTests(String filename, int expected) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String[] firstInput = reader.readLine().split(" "); // <numItems> <maxWeightOne> <maxWeightTwo>
        Item[] items = new Item[Integer.parseInt(firstInput[0])];
        int maxWeightOne = Integer.parseInt(firstInput[1]);
        int maxWeightTwo = Integer.parseInt(firstInput[2]);
        for (int i = 0; i < items.length; i++) {
            String[] itemInput = reader.readLine().split(" ");
            items[i] = new Item(Integer.parseInt(itemInput[0]), Integer.parseInt(itemInput[1]));
        }
        reader.close();
        int actual = doubleKnapsackIndivisible(items, maxWeightOne, maxWeightTwo);
        System.out.println(String.format("%s: expected=%s, actual=%s", filename, expected, actual));
    }

    public static void testsLocal() throws IOException {
        doubleKnapsackFileTests("knapsack/input-4.1.txt", 19);
        doubleKnapsackFileTests("knapsack/input-4.2.txt", 36);
        doubleKnapsackFileTests("knapsack/input-4.3.txt", 151);
        doubleKnapsackFileTests("knapsack/input-4.4.txt", 2890);
        doubleKnapsackFileTests("knapsack/input-4.5.txt", 3465);

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
        
        for (int i = 0; i < items.length; i++) {
            String[] itemInput = scanner.nextLine().split(" ");
            items[i] = new Item(Integer.parseInt(itemInput[0]), Integer.parseInt(itemInput[1]));
        }
        scanner.close();

        System.out.println(doubleKnapsackIndivisible(items, maxWeightOne, maxWeightTwo));
    }

    public static void main(String[] args) throws IOException {
        //testsLocal();
        stdinAlgo();
    }
}