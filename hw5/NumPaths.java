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
        boolean[] seen = new boolean[graph.getNumVertices() + 1];
        int[] dist = new int[graph.getNumVertices() + 1];
        int[] count = new int[graph.getNumVertices() + 1]; // added for shortest path problem
        for (int i = 1; i < seen.length; i++) {
            seen[i] = false;
            dist[i] = Integer.MAX_VALUE;
        }

        int[] queue = new int[graph.getNumVertices() + 1];
        int beg = 0, end = 0;

        // initialize starting vertex
        queue[end++] = startVertex;
        seen[startVertex] = true;
        dist[startVertex] = 0;
        count[startVertex] = 1; // added for shortest path problem

        // traverse through vertexes using bfs
        while (beg < end) {
            int head = queue[beg++]; // dequeue

            // explore through all possible neighbors in the current vertex
            for (int neighbor = 1; neighbor <= graph.getNumVertices(); neighbor++) {
                // add neighbors that are both connected and seen
                if (graph.connected(head, neighbor) && !seen[neighbor]) {
                    queue[end++] = neighbor;
                    dist[neighbor] = dist[head] + 1; // distance between the head and the neighbor
                    seen[neighbor] = true;
                    count[neighbor] += count[head]; // added for shortest path problem; calculate shortest path
                // added for shortest path problem; checks if the current neighbor is at a distance that is exactly one more than the distance to the head vertex from the queue
                } else if (dist[head] + 1 == dist[neighbor]) {
                    count[neighbor] += count[head];
                }
            }
        }

        System.out.println(count[endVertex]);
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

    
    public static void main(String[] args) {
        GraphMatrix graph = stdinAlgo();
        numPathsProblem(graph);
    }
}
