import java.io.IOException;
import java.util.Stack;

  public class LargestAxisParallelRectangle {
    public static int findLargestRectangleArea(Pair[] vertices) {
      int n = vertices.length;
      Stack<Integer> stack = new Stack<>();
      int maxArea = 0;

      for (int i = 0; i < n; i++) {
          while (!stack.isEmpty() && vertices[i].getY() < vertices[stack.peek()].getY()) {
              int height = vertices[stack.pop()].getY();
              int width = i - (stack.isEmpty() ? 0 : stack.peek() + 1);
              maxArea = Math.max(maxArea, height * width);
          }
          stack.push(i);
      }

      while (!stack.isEmpty()) {
          int height = vertices[stack.pop()].getY();
          int width = n - (stack.isEmpty() ? 0 : stack.peek() + 1);
          maxArea = Math.max(maxArea, height * width);
      }

      return maxArea;
  }
    public static void main(String[] args) throws IOException {
      Pair[] pairs = TestCases.generatePairs("input1.txt");
      int maxArea = findLargestRectangleArea(pairs);
      System.out.println("Largest possible area of an axis-parallel rectangle: " + maxArea);
  }
}