import java.io.IOException;
import java.util.Arrays;
import java.util.Stack;
public class MaxRectangle {

    /**
     * Determines whether 2 pairs are going into the positive direction
     * @param one pair one
     * @param two pair two
     * @return true if the direction of two pairs are positive (or they have the same y coordinates)
     */
    public static boolean isPairPositive(Pair left, Pair right) {
        return left.getY() <= right.getY();
    }

    /**
     * Determines whether 2 pairs are in the same y coordinates (safe to skip from counting towards stack)
     * @param left
     * @param right
     * @return true if they are
     */
    public static boolean isHorizontal(Pair left, Pair right) {
        return left.getY() == right.getY();
    }
    public static int maxAreaHorizontal(int hist[]) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int top, areaWithTop;

        int i = 0;
        while (i < hist.length) {
            if (stack.isEmpty() || hist[stack.peek()] <= hist[i]) {
                stack.push(i);
                i++;
            } else {
                top = stack.pop();
                ////
                int width;
                if (stack.isEmpty()) {
                    width = i;
                } else {
                    width = i - stack.peek() - 1;
                }
                
                // Calculate the area with hist[top] as the height
                areaWithTop = hist[top] * width;
                ///////

                maxArea = Math.max(maxArea, areaWithTop);
            }
        }

        while (!stack.isEmpty()) {
            top = stack.pop();
                ////
                int width;
                if (stack.isEmpty()) {
                    width = i;
                } else {
                    width = i - stack.peek() - 1;
                }
                
                // Calculate the area with hist[top] as the height
                areaWithTop = hist[top] * width;
                ///////

            maxArea = Math.max(maxArea, areaWithTop);
        }

        return maxArea;
    }


    public static int maxAreaVertical(Pair[] pairs) {
        int maxArea = 0;
        
        // calculates vertical areas
        for (int i = 0; i < pairs.length - 1; i++) {
            Pair current = pairs[i];
            Pair next = pairs[i+1];

            if (isHorizontal(current, next)) {
                int verticalArea = current.getY() * (next.getX() - current.getX());
                if (verticalArea > maxArea) {
                    maxArea = verticalArea;
                }
            }
        }
        return maxArea;
    }
    public static void main(String[] args) throws IOException {
        Pair[] pairs = TestCases.generatePairs("input1.txt");
        int max = maxAreaVertical(pairs);
        System.out.println(max);

        // max area horizontal
        int histogramSize = 0;
        for (int i = 0; i < pairs.length - 1; i++) {
            Pair current = pairs[i];
            Pair next = pairs[i+1];
            if (isHorizontal(current, next)) {
                histogramSize += next.getX() - current.getX();
            }
        } 

        int[] y_pairs = new int[histogramSize];
        int y_pairs_i = 0;
        for (int i = 0; i < pairs.length - 1; i++) {
            Pair current = pairs[i];
            Pair next = pairs[i+1];
            if (isHorizontal(current, next)) {
                int size =  next.getX() - current.getX();
                for (int j = 0; j < size; j++) {
                    y_pairs[y_pairs_i] = current.getY();
                    y_pairs_i++;
                }
            }
        } 



        System.out.println(Arrays.toString(y_pairs));

        System.out.println(maxAreaHorizontal(y_pairs));
    }
}