import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

    }
    public static String topologicalSort(Graph graph) {
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

    private static void recursiveTopologicalSort(Graph.Vertex v, Map<Graph.Vertex, Boolean> visited, Deque deque, Graph graph) {
        visited.put(v, true);
        for (Graph.Vertex directedConnection : graph.getDirectedConnections(v)) {
            if (!visited.get(directedConnection))
                recursiveTopologicalSort(directedConnection, visited, deque, graph);
        }

        deque.push(v);
    }
}

