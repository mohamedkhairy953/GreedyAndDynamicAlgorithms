package min_spanning_tree.prim

fun kruskal(vertices: List<Vertex>, edges: List<Edge>) {
    val selectedEdges = mutableListOf<Edge>()
    val ds = DisjointSet(vertices.size)
    val sortedEdges = edges.sortedWith { e1, e2 -> e1.cost - e2.cost }
    sortedEdges.forEach {
        if (!ds.connected(it.v.key, it.w.key)) {
            selectedEdges.add(it)
            ds.union(it.v.key, it.w.key)
        }
    }


}