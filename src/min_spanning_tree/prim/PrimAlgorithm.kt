package min_spanning_tree.prim

import java.util.*

data class Edge(val v: Vertex, val w: Vertex, val cost: Int)
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

fun primAlgorithmByHeaps(edges: List<Edge>, vertices: List<Vertex>) {
    val s = vertices.first()
    val heap: PriorityQueue<Vertex> = PriorityQueue { v1, v2 -> v1.key - v2.key }
    val visited = mutableListOf(s)
    val mstEdges = mutableListOf<Edge>()
    s.edges.forEach {
        if (it.v == s)
            it.w.key = it.cost
        else
            it.v.key = it.cost
    }
    vertices.forEach {
        if (it != s) {
            heap.offer(it)
        }
    }
    while (heap.isNotEmpty()) {
        val polled = heap.poll()
        visited.add(polled)
        mstEdges.add(edges.find { (it.v == s && it.w == polled) || (it.w == s && it.v == polled) }!!)
        polled.edges.forEach {
            if (it.v == polled) {
                if (it.w.key > it.cost) {
                    heap.remove( it.w)
                    it.w.key = it.cost
                    heap.offer(it.w)
                }
            }
            else {
                if (it.v.key > it.cost){
                    heap.remove( it.w)
                    it.v.key = it.cost
                    heap.offer(it.v)
                }
            }
        }
    }
}