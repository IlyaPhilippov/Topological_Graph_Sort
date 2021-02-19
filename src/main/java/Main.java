import java.util.*;

public class Main {
    public static void main(String[] args) {

    }
    public static boolean GraphCheck(Graph graph) {
        boolean check = true;
        for (Graph.Vertex element : graph.getVertices()) {
            List<Graph.Vertex> list1;
            list1 = graph.getDirectedConnections(element);
            for (Graph.Vertex vertex : list1) {
                List<Graph.Vertex> list2;
                list2 = graph.getDirectedConnections(vertex);
                for (Graph.Vertex vertex2 : list2) {
                    if (vertex2 == element) {
                        check = false;
                        break;
                    }
                }
            }
        }
        return check;
    }
    public static String topologicalSort(Graph graph) {
        if (!GraphCheck(graph)){
            throw new IllegalArgumentException();
        }
        StringBuilder builder = new StringBuilder();
        Deque<Graph.Vertex> deque = new LinkedList<>();
        Map<Graph.Vertex, Boolean> visited = new HashMap<>();
        graph.getVertices().forEach(vertex -> visited.put(vertex, false));
        for (Graph.Vertex vertex : graph.getVertices()) {
            if (!visited.get(vertex)) {
                recursiveTopologicalSort(vertex, visited, deque, graph);
            }
        }
        while (!deque.isEmpty())
            builder.append(deque.pop()).append(" ");
        return builder.toString();
    }

    private static void recursiveTopologicalSort(Graph.Vertex v, Map<Graph.Vertex, Boolean> visited, Deque<Graph.Vertex> deque, Graph graph) {
        visited.put(v, true);
        for (Graph.Vertex directedConnection : graph.getDirectedConnections(v)) {
            if (!visited.get(directedConnection))
                recursiveTopologicalSort(directedConnection, visited, deque, graph);
        }

        deque.push(v);
    }
}
// Трудоемскоть - N^3 + N +  N*log(N)
// Ресурсоемксоть - N^3 + N +  N*log(N)

