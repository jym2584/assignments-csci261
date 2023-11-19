import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class NumPaths {
    private static int startVertex;
    private static int endVertex;

    /**
     * Num paths problem using a modified version of BFS
     * @param graph
     */
    public static void numPathsProblem(GraphMatrix graph) {
        // initialize data structures
        //boolean[] seen = new boolean[this.size + 1];
        int[] dist = new int[graph.getNumVertices() + 1];
        dist[0] = Integer.MAX_VALUE;
        for (int i = 1; i < dist.length; i++) {
            //seen[i] = false;
            dist[i] = Integer.MAX_VALUE;
        }

        int[] queue = new int[graph.getNumVertices() + 1];
        int beg = 0, end = 0;

        // initialize starting vertex
        queue[end++] = startVertex;
        //seen[startVertex] = true;
        dist[startVertex] = 0;

        // added for shortest path problem
        int[] paths = new int[graph.getNumVertices() + 1];
        paths[startVertex] = 1; 

        // traverse through vertexes using bfs
        while (beg < end) {
            int head = queue[beg++]; // dequeue

            // explore through all possible neighbors in the current vertex
            for (int neighbor = 1; neighbor <= graph.getNumVertices(); neighbor++) {
                // modified BFS code for shortest path problem

                // add neighbors that are connected
                if (graph.connected(head, neighbor)) {
                    // keep track of the distance and shortest paths from the starting vertex to each vertex
                    if (dist[neighbor] == Integer.MAX_VALUE) {
                        queue[end++] = neighbor;
                        dist[neighbor] = dist[head] + 1; // distance between the head and the neighbor
                        //seen[neighbor] = true;
                        paths[neighbor] = paths[head]; // calculate from head to neighbor
                    // increment count of the shortest path to the neighbor by the count of shorest path to the current vertex
                    } else if (dist[neighbor] == dist[head] + 1) {
                        paths[neighbor] += paths[head]; 
                    }
                }
            }
        }

        System.out.println(paths[endVertex]);
    }

    public static GraphMatrix stdinAlgo() {
        Scanner scanner = new Scanner(System.in);
        String[] firstInput = scanner.nextLine().split(" "); // <number of vertices> <number of edges>
        String[] startEndInput = scanner.nextLine().split(" "); // <number of vertices> <number of edges>
        GraphMatrix graph = new GraphMatrix(Integer.parseInt(firstInput[0])); // number of vertices
        startVertex = Integer.parseInt(startEndInput[0]);
        endVertex = Integer.parseInt(startEndInput[1]);
       
        for (int i = 0; i < Integer.parseInt(firstInput[1]); i++) { // number of edges (connections)
            String[] edge = scanner.nextLine().split(" ");
            graph.connectUndirected(Integer.parseInt(edge[0]), Integer.parseInt(edge[1]));
        }
        scanner.close();
        return graph;
    }

    public static GraphMatrix fileTest(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String[] firstInput = reader.readLine().split(" "); // <number of vertices> <number of edges>
        String[] startEndInput = reader.readLine().split(" "); // <number of vertices> <number of edges>
        GraphMatrix graph = new GraphMatrix(Integer.parseInt(firstInput[0])); // number of vertices
        startVertex = Integer.parseInt(startEndInput[0]);
        endVertex = Integer.parseInt(startEndInput[1]);
    
        for (int i = 0; i < Integer.parseInt(firstInput[1]); i++) { // number of edges (connections)
            String[] edge = reader.readLine().split(" ");
            graph.connectUndirected(Integer.parseInt(edge[0]), Integer.parseInt(edge[1]));
        }
        reader.close();

        return graph;
    }

    
    public static void main(String[] args) throws IOException {
        // GraphMatrix graph = fileTest("NumPathsTests/input11.txt"); // 4
        // numPathsProblem(graph);
        // graph = fileTest("NumPathsTests/input12.txt"); // 5
        GraphMatrix graph = stdinAlgo();
        numPathsProblem(graph);
    }
}
