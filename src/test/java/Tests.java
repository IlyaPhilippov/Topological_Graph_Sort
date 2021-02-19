import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Tests {
        @Test
        public void test1() {
            GraphBuilder builder = new GraphBuilder();
            Graph.Vertex vertex0 = builder.addVertex("0");
            Graph.Vertex vertex1 = builder.addVertex("1");
            Graph.Vertex vertex2 = builder.addVertex("2");
            Graph.Vertex vertex3 = builder.addVertex("3");
            Graph.Vertex vertex4 = builder.addVertex("4");
            Graph.Vertex vertex5 = builder.addVertex("5");
            builder.addConnection(vertex5, vertex0, 1);
            builder.addConnection(vertex5, vertex2, 1);
            builder.addConnection(vertex4, vertex0, 1);
            builder.addConnection(vertex4, vertex1, 1);
            builder.addConnection(vertex2, vertex3, 1);
            builder.addConnection(vertex3, vertex1, 1);
            Graph graph = builder.build();
            assertEquals("5 4 2 3 1 0", Main.topologicalSort(graph).trim());
        }

        @Test
        public void test2() {
            GraphBuilder builder = new GraphBuilder();
            Graph.Vertex vertex1 = builder.addVertex("1");
            Graph.Vertex vertex2 = builder.addVertex("2");
            Graph.Vertex vertex3 = builder.addVertex("3");
            Graph.Vertex vertex4 = builder.addVertex("4");
            builder.addConnection(vertex4, vertex2, 1);
            builder.addConnection(vertex4, vertex3, 1);
            builder.addConnection(vertex1, vertex4, 1);
            builder.addConnection(vertex3, vertex2,1);
            Graph graph = builder.build();
            assertEquals("1 4 3 2", Main.topologicalSort(graph).trim());
        }

        @Test
        public void test3() {
            GraphBuilder builder = new GraphBuilder();
            Graph.Vertex vertex1 = builder.addVertex("1");
            Graph.Vertex vertex2 = builder.addVertex("2");
            Graph.Vertex vertex3 = builder.addVertex("3");
            Graph.Vertex vertex4 = builder.addVertex("4");
            builder.addConnection(vertex1, vertex2, 1);
            builder.addConnection(vertex1, vertex3, 1);
            builder.addConnection(vertex2, vertex1, 1);
            builder.addConnection(vertex2, vertex4,1);
            builder.addConnection(vertex3, vertex1, 1);
            builder.addConnection(vertex3, vertex4,1);
            builder.addConnection(vertex4, vertex2, 1);
            builder.addConnection(vertex4, vertex3,1);
            Graph graph = builder.build();
            assertThrows(IllegalArgumentException.class, () -> Main.topologicalSort(graph));
        }
}
