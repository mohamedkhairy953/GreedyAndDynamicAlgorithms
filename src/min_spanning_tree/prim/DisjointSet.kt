package min_spanning_tree.prim

class DisjointSet(val size:Int) {
    private val parent = IntArray(size)
    private val rank = ByteArray(size)
    var count = size
        private set

    init {
        for (i in parent.indices) {
            parent[i] = i
        }
    }

    public fun connected(v: Int, w: Int): Boolean {
        return find(v) == find(w)
    }

    public fun find(v: Int): Int {
        if (parent[v] == v)
            return v
        find(parent[v])
        return -1
    }

    public fun union(v: Int, w: Int) {
        val rootV = find(v)
        val rootW = find(w)
        if (rootV == rootW) return
        if (rank[rootV] > rank[rootW]) {
            parent[rootW] = rootV
        } else if (rank[rootW] > rank[rootV]) {
            parent[rootV] = rootW
        } else {
            parent[rootV] = rootW
            rank[rootW]++
        }
        count--
    }
}