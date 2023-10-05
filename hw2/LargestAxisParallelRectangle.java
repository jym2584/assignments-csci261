public class LargestAxisParallelRectangle {
  public static int largestAxisParallelRectangle(int[][] polygon) {
    int maxArea = 0;
    int currentArea = 0;
    int[] currentRectangle = new int[]{polygon[0][0], polygon[0][1], polygon[0][0], polygon[0][1]};

    for (int i = 1; i < polygon.length; i++) {
      if (polygon[i][1] > currentRectangle[3]) {
        currentArea += (polygon[i][1] - currentRectangle[3]) * (currentRectangle[2] - currentRectangle[0]);
        currentRectangle = new int[]{currentRectangle[0], currentRectangle[1], currentRectangle[2], polygon[i][1]};
      }

      if (currentArea > maxArea) {
        maxArea = currentArea;
      }
    }

    return maxArea;
  }
  public static void main(String[] args) {
    int[][] polygon = {{3, 0}, {3, 1}, {4, 1}, {4, 3}, {6, 3}, {6, 6}, {10, 6}, {10, 2},
                    {13, 2}, {13, 5}, {17, 5}, {17, 1}, {18, 1}, {18, 8}, {20, 8}, {20, 0}};

    int largestArea = LargestAxisParallelRectangle.largestAxisParallelRectangle(polygon);

    System.out.println(largestArea);
  }
}