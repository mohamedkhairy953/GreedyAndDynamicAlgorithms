package min_spanning_tree.prim

data class Vertex(val edges: List<Edge>, var key: Int = Int.MAX_VALUE, val name: String) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Vertex

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}
