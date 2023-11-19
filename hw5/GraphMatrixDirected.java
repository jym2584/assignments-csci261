import java.util.Arrays;

/**
 * Array representation of a graph. Assumes there exists vertices that precedes the value of the max vertex.
 */
public class GraphMatrixDirected {
    private int[][] matrix;
    private int size;
    
    public GraphMatrixDirected(int maxVertexValue) {
        this.size = maxVertexValue;
        this.matrix = new int[this.size + 1][this.size + 1];
    }
    
    public int getNumVertices() {
        return size;
    }

    /**
     * Adds a directional connection from vertex a to vertex b
     * @param a
     * @param b
     */
    public void connectDirected(int a, int b) {
        this.matrix[a][b] = 1;
    }


    public boolean connected(int a, int b) {
        return this.matrix[a][b] == 1;
    }

    /**
     * BFS Algorithm; psuedocode from class
     * @param startVertex vertex to start searching from
     * @return distance between the starting vertex and all other vertices
     */
    public int[] bfs(int startVertex) {
        // initialize data structures
        boolean[] seen = new boolean[this.size + 1];
        int[] dist = new int[this.size + 1];
        dist[0] = Integer.MAX_VALUE;
        for (int i = 1; i < seen.length; i++) {
            seen[i] = false;
            dist[i] = Integer.MAX_VALUE;
        }

        int[] queue = new int[this.size + 1];
        int beg = 0, end = 0;

        // initialize starting vertex
        queue[end++] = startVertex;
        seen[startVertex] = true;
        dist[startVertex] = 0;

        // traverse through vertexes using bfs
        while (beg < end) {
            int head = queue[beg++]; // dequeue

            // explore through all possible neighbors in the current vertex
            for (int neighbor = 1; neighbor <= this.size; neighbor++) {

                // add neighbors that are both connected and seen
                if (this.connected(head, neighbor) && !seen[neighbor]) {
                    queue[end++] = neighbor;
                    dist[neighbor] = dist[head] + 1; // distance between the head and the neighbor
                    seen[neighbor] = true;
                }
            }
        }

        return dist;
    }


    /**
     * DFS Algorithm; psuedocode from class
     * @param startVertex vertex to start searching from
     * @return array indicating whether each vertex is reachable from the starting vertex
     */
    public boolean[] dfs(int startVertex) {
        boolean[] visited = new boolean[this.size + 1];
        visitDFS(startVertex, visited);
        return visited;
    }

    /**
     * Recursive helper algorithm to traverse through the graph using DFS; psuedocode from class
     * @param vertex vertex to start searching from
     * @param visited array of visited vertices
     */
    public void visitDFS(int vertex, boolean[] visited) {
        visited[vertex] = true;
        for (int neighbor = 1; neighbor <= this.size; neighbor++) {
            if (this.connected(vertex, neighbor) && !visited[neighbor]) {
                visitDFS(neighbor, visited);
            }
        }
    }

    public static void test() {
        // test
        GraphMatrixDirected graph = new GraphMatrixDirected(9);

        /**    
         *       3 <-----------|
         *       ^             |
         *       |             |
         *       1 ---> 2 ---> 4                  9
         *                     ^                  
         *                     |
         *                     5 -----> 6 ------> 8
         *                     ^                  |
         *                     |                  v
         *                     |----------------- 7
         */
        graph.connectDirected(1, 2);
        graph.connectDirected(1, 3);
        graph.connectDirected(2, 4);
        graph.connectDirected(4, 3);
        graph.connectDirected(5, 4);
        graph.connectDirected(5, 6);
        graph.connectDirected(6, 8);
        graph.connectDirected(8, 7);
        graph.connectDirected(7, 5);
        // 9 is unreachable

        System.out.println("[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]");
        System.out.println(Arrays.toString(graph.bfs(1)));
        System.out.println(Arrays.toString(graph.dfs(1)));
    }
    public static void main(String[] args) {
        //test();

        // throws java heap space error
        GraphMatrixDirected graph = new GraphMatrixDirected(100000);

    }
}
