import java.util.Scanner;

public class NegativeCycle {
    public static int numVertices; 
    private static int numEdges;

    /**
     * Negative cycle problem
     * @param edges
     * @return true if there exists a negative weight cycle
     */
    public static boolean negativeCycleProblem(Edge[] edges) {
        int[] distances = new int[numVertices];
        
        for (int i = 1; i < distances.length; i++) {
            distances[i] = Integer.MAX_VALUE;
        }

        // bellman-ford algorithm
        for (int i = 0; i < numVertices - 1; i++) {
            for (int j = 0; j < edges.length; j++) {
                Edge edge = edges[j];
                boolean visited = distances[edge.getSrc() - 1] != Integer.MAX_VALUE;
                // checks whether the current edge provides a shorter path from the src --> dest vertex than the currently known shortest path
                boolean shorterPathFound = distances[edge.getSrc() - 1] + edge.weight < distances[edge.getDest() - 1];
                if (visited && shorterPathFound) {
                    distances[edge.getDest() - 1] = distances[edge.getSrc() - 1] + edge.weight; // update the distance with the new shorter distance
                }
            }
        }

        // check for negative weight cycle
        for (int j = 0; j < edges.length; j++) {
            Edge edge = edges[j];
            boolean visited = distances[edge.getSrc() - 1] != Integer.MAX_VALUE;
            // checks whether the current edge provides a shorter path from the src --> dest vertex than the currently known shortest path
            boolean shorterPathFound = distances[edge.getSrc() - 1] + edge.weight < distances[edge.getDest() - 1]; 
            if (visited && shorterPathFound) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] firstLineInput = scanner.nextLine().split(" ");
        numVertices = Integer.parseInt(firstLineInput[0]); // Number of vertices
        numEdges = Integer.parseInt(firstLineInput[1]); // Number of edges

        Edge[] edges = new Edge[numEdges];
        for (int i = 0; i < numEdges; i++) {
            String[] input = scanner.nextLine().split(" ");
            edges[i] = new Edge(Integer.parseInt(input[0]), Integer.parseInt(input[1]), Integer.parseInt(input[2])); // src, dest, weight
        }

        scanner.close();

        if (negativeCycleProblem(edges)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}