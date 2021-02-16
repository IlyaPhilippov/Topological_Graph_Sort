class GraphBuilder {

    data class VertexImpl(private val nameField: String) : Graph.Vertex {
        override fun getName() = nameField

        override fun toString() = name
    }

    data class EdgeImpl(
            private val weightField: Int,
            private val _begin: Graph.Vertex,
            private val _end: Graph.Vertex
    ) : Graph.Edge {
        override fun getBegin() = _begin

        override fun getEnd() = _end

        override fun getWeight() = weightField
    }

    private val vertices = mutableMapOf<String, Graph.Vertex>()

    private val connections = mutableMapOf<Graph.Vertex, Set<EdgeImpl>>()

    private fun addVertex(v: Graph.Vertex) {
        vertices[v.name] = v
    }

    fun addVertex(name: String): Graph.Vertex {
        return VertexImpl(name).apply {
            addVertex(this)
        }
    }

    fun addConnection(begin: Graph.Vertex, end: Graph.Vertex, weight: Int = 1) {
        val edge = EdgeImpl(weight, begin, end)
        connections[begin] = connections[begin]?.let { it + edge } ?: setOf(edge)
        connections[end] = connections[end]?.let { it + edge } ?: setOf(edge)
    }

    fun build(): Graph = object : Graph {

        override fun get(name: String): Graph.Vertex? = this@GraphBuilder.vertices[name]

        override fun getVertices(): Set<Graph.Vertex> = this@GraphBuilder.vertices.values.toSet()

        override fun getEdges(): Set<Graph.Edge> {
            return connections.values.flatten().toSet()
        }

        override fun getDirectedConnections(v: Graph.Vertex): List<Graph.Vertex> {
            val edges = connections[v] ?: emptySet()
            var result = mutableListOf<Graph.Vertex>()
            for (edge in edges) {
                if (v == edge.begin) {
                    result.add(edge.end)
                }
            }
            return result;
        }


        override fun getConnections(v: Graph.Vertex): Map<Graph.Vertex, Graph.Edge> {
            val edges = connections[v] ?: emptySet()
            val result = mutableMapOf<Graph.Vertex, Graph.Edge>()
            for (edge in edges) {
                when (v) {
                    edge.begin -> result[edge.end] = edge
                    edge.end -> result[edge.begin] = edge
                }
            }
            return result
        }
    }
}