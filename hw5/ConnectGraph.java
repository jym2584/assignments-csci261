import java.util.Scanner;

public class ConnectGraph {
    public static void connectGraphProblem(GraphMatrix graph) {
        boolean[] visited = new boolean[graph.getNumVertices() + 1];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }
        int connected = 0;

        for (int vertex = 1; vertex <= graph.getNumVertices(); vertex++) {
            if (!visited[vertex]) {
                graph.visitDFS(vertex, visited);
                connected += 1;
            }
        }

        System.out.println(connected - 1);
        
    }


    public static GraphMatrix stdinAlgo() {
        Scanner scanner = new Scanner(System.in);
        String[] firstInput = scanner.nextLine().split(" "); // <number of vertices> <number of edges>
        GraphMatrix graph = new GraphMatrix(Integer.parseInt(firstInput[0])); // number of vertices
       
        for (int i = 0; i < Integer.parseInt(firstInput[1]); i++) { // number of edges (connections)
            String[] edge = scanner.nextLine().split(" ");
            graph.connectUndirected(Integer.parseInt(edge[0]), Integer.parseInt(edge[1]));
        }
        scanner.close();
        return graph;
    }

    public static void main(String[] args) {
        GraphMatrix graph = stdinAlgo();
        connectGraphProblem(graph);
    }
}