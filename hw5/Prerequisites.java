import java.util.Scanner;

public class Prerequisites {
    /**
     * Keeps track of the number of pre-requisites for each course
     */
    private static int[] preReqCount;

    /**
     * Pre-requisites problem using a modified BFS algorithm
     * @param graph
     */
    public static void prerequisitesProblem(GraphMatrixDirected graph) {
        int[] chainLength = new int[graph.getNumVertices() + 1];
        int[] queue = new int[graph.getNumVertices() + 1];
        int beg = 0, end = 0;


        // Topological sorting
        for (int i = 1; i <= graph.getNumVertices(); i++) {
            if (preReqCount[i] == 0) {
                queue[end++] = i;
            }
        }

        // Modified BFS algorithm to solve the pre-requisites problem
        while (beg < end) {
            int head = queue[beg++]; // dequeue

            for (int neighbor = 1; neighbor <= graph.getNumVertices(); neighbor++) {
                if (graph.connected(head, neighbor)) {
                    preReqCount[neighbor]--; // assumes that the neighbor has one pre-requisite fulfilled

                    // update neighbor's chain length
                    if (chainLength[neighbor] < chainLength[head] + 1) {
                        chainLength[neighbor] = chainLength[head] + 1;
                    }

                    // add neighbor to the queue if there are no pre-requisites
                    if (preReqCount[neighbor] == 0) {
                        queue[end++] = neighbor;
                    }
                }
            }
        }

        // Find the maximum length in the maxLength array
        int maxChain = 0;
        for (int i = 1; i <= graph.getNumVertices(); i++) {
            if (maxChain < chainLength[i]) {
                maxChain = chainLength[i];
            }
        }

        System.out.println(1 + maxChain); // include the course itself
    }

    public static GraphMatrixDirected stdinAlgo() {
        Scanner scanner = new Scanner(System.in);
        Integer maxVertexValue = Integer.parseInt(scanner.nextLine());
        GraphMatrixDirected graph = new GraphMatrixDirected(maxVertexValue);
        preReqCount = new int[maxVertexValue + 1];

        for (int i = 1; i <= maxVertexValue; i++) {
            String[] input = scanner.nextLine().split(" ");
            for (int j = 0; j < input.length; j++) {
                try {
                    int prereq = Integer.parseInt(input[j]);
                    if (prereq == 0) {
                        break;
                    }
                    graph.connectDirected(prereq, i);
                    preReqCount[i]++; // add pre-requisite count for the current course
                } catch (NumberFormatException e) {
                    // squash exception
                }
            }
        }

        scanner.close();
        return graph;
    }

    public static void main(String[] args) {
        GraphMatrixDirected graph = stdinAlgo();
        prerequisitesProblem(graph);
    }
}