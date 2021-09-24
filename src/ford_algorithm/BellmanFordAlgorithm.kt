package ford_algorithm

import Vertex
import kotlin.math.min

class DirectedEdge(val h: Vertex, val t: Vertex, val cost: Int)
data class DirectedVertex(
    val inEdges: List<DirectedEdge>,
    val outEdges: List<DirectedEdge>,
    var key: Int = Int.MAX_VALUE,
    val name: String
) {
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

fun shortestPath(
    vertices: List<DirectedVertex>,
    startPosition: Int
) {
    val n = vertices.size
    val A: Array<Array<Int>> =
        arrayOf(
            Array(size = n + 1, init = { Int.MAX_VALUE }),
            Array(size = n, init = { Int.MAX_VALUE })
        )
    A[0][startPosition] = 0
    var isStable=true
    for (i in 1..n) {
        isStable=true
        for (v in 1..vertices.size) {
            A[i][v] = min(A[i - 1][v], getMinIncomingEdgeCost(i, vertices, vertices[v], A))

            if(A[i][v] != A[i-1][v])
                isStable=false
        }
    }
    if(isStable){
        println(A)
    }else{
        "Negative cycles"
    }
}

fun getMinIncomingEdgeCost(i: Int, vertices: List<DirectedVertex>, vertex: DirectedVertex, A: Array<Array<Int>>): Int {
    return vertex.inEdges.map { A[i - 1][vertices.indexOf(it.h)] + it.cost }.minOrNull() ?: Int.MAX_VALUE
}
