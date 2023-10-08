import java.util.Scanner;

public class MaxRectangle {

    /**
     * Determines whether 2 pairs are going into the positive direction
     * @param one pair one
     * @param two pair two
     * @return true if the direction of two pairs are positive (or they have the same y coordinates)
     */
    private static boolean isPairPositive(Pair left, Pair right) {
        return left.getY() <= right.getY();
    }

    /**
     * 
     * @param current Negative pair to stop calculating when we iterate through stack
     * @param stack positive pairs
     * @return
     */
    private static int calculateMaxAreaFromStack(Pair current, StackPair stack) {
        int maxArea = 0;
        Pair lastPair = stack.peek();
        Pair[] getTops = getLastTwoPairs(stack);
        Pair top1 = getTops[0];
        Pair top2 = getTops[1];

        // calculate pairs if there are more than 2 pairs & until we reached a pair that is less than current
        while (top2 != null && isPairPositive(current, top1)) {
            // calculate area
            int area = (lastPair.getX() - top2.getX()) * top1.getY();
            if (area > maxArea) {
                maxArea = area;
            }
            stack.pop(); // remove calculated area
            // get new top1 and top2
            getTops = getLastTwoPairs(stack);
            top1 = getTops[0];
            top2 = getTops[1];
        }

        stack.push(current); // add current now that we have calculated every area up until this point
        return maxArea;
    }

    /**
     * Gets last 2 pairs in the stack
     * @param stack stack containing more than 2 elements
     * @return top1 and top2
     */
    private static Pair[] getLastTwoPairs(StackPair stack) {
        Pair firstTop = stack.pop();
        Pair secondTop = stack.peek();
        stack.push(firstTop);
        return new Pair[] {firstTop, secondTop};
    }

    /**
     * Solves max rectangle problem
     * @param pairs coordinates
     * @return max area given coordinates
     */
    public static int maxRectangle(Pair[] pairs) {
        int maxArea = 0;
        StackPair stack = new StackPair(pairs.length);
        stack.push(pairs[0]); // add the starting point to the stack

        // iterate through every other pair O(1/2n)
        for (int i = 0; i < pairs.length - 2; i+=2) {
            Pair current = pairs[i];
            Pair next = pairs[i+2]; // next bar
            if (isPairPositive(current, next)) {
                stack.push(next);
            } else {
                int area = calculateMaxAreaFromStack(next, stack);
                if (area > maxArea) { 
                    maxArea = area;
                };
            }
        }
        // calculate the last possible areas at the ending point (not included in logic above)
        Pair lastCoordinate = pairs[pairs.length - 1];
        int area = calculateMaxAreaFromStack(lastCoordinate, stack);
        if (area > maxArea) { 
            maxArea = area;
        };
        return maxArea;
    }

    public static void main(String[] args) {
        // initialize array from input file
        Scanner scanner = new Scanner(System.in);

        int length = scanner.nextInt();
        Pair[] pairs = new Pair[length];
        for(int i = 0; i < length; i++){
            String[] input = scanner.nextLine().split(" ");
            pairs[i] = new Pair(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
        }

        scanner.close();

        // solve max rectangle problem
        System.out.println(maxRectangle(pairs));
    }
}