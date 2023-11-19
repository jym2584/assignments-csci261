import java.util.Scanner;
/**
 * 
 * 
 * Getting Java heap space errors with test 5
 * 
 * ===== Test 5
 * /usr/local/jdk/bin/java -ea -Xss16M -classpath .:../../submit_java/Prerequisites/ Prerequisites < input-1.5 > OUT.5
 * 
 * Your output did not match the correct output.
 * Your output:
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 *         at GraphMatrixDirected.<init>(GraphMatrixDirected.java:12)
 *         at Prerequisites.stdinAlgo(Prerequisites.java:64)
 *         at Prerequisites.main(Prerequisites.java:88)
 * ----------
 * Correct output:
 * 256
 * ----------
 * Differences (OUT.5 is yours; answer-1.5 is correct):
 * *** OUT.5       2023-11-18 22:57:49.522297000 -0500
 * --- answer-1.5  2021-08-12 12:47:37.804745000 -0400
 * ***************
 * *** 1,4 ****
 * ! Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 * !       at GraphMatrixDirected.<init>(GraphMatrixDirected.java:12)
 * !       at Prerequisites.stdinAlgo(Prerequisites.java:64)
 * !       at Prerequisites.main(Prerequisites.java:88)
 * --- 1 ----
 * ! 256
 * 
 * 
 * Files being saved:
 * GraphMatrixDirected.class GraphMatrixDirected.java Prerequisites.class Prerequisites.java
 * 
 * hmwk5-3 has been submitted WITH ERRORS.
 */
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
        // initialize data structures
        int[] chainLength = new int[graph.getNumVertices() + 1];
        int[] queue = new int[graph.getNumVertices() + 1];
        int beg = 0, end = 0;


        // Topological sorting
        for (int i = 1; i <= graph.getNumVertices(); i++) {
            if (preReqCount[i] == 0) {
                queue[end++] = i;
            }
        }

        // traverse through vertexes using bfs
        while (beg < end) {
            int head = queue[beg++]; // dequeue
            
            // explore through all possible neighbors in the current vertex
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

        // Find the maximum length in the chainLength array
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