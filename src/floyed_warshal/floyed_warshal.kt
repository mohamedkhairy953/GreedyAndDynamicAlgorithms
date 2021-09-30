package floyed_warshal

import Vertex
import ford_algorithm.getMinIncomingEdgeCost
import kotlin.math.min

fun floyedWarshal(
    vertices: List<Vertex>
) {
    val n = vertices.size
    val A = arrayOf(
        arrayOf(intArrayOf(0, 0), intArrayOf(0)),
        arrayOf(intArrayOf(0, 1), intArrayOf(0)),
        arrayOf(intArrayOf(1, 0), intArrayOf(0)),
        arrayOf(intArrayOf(1, 1), intArrayOf(1))
    )

    baseCases(vertices, A)
    for (k in 1..n)
        for (v in 1..n)
            for (w in 1..n) {
                A[k][v][w] =
                    min(A[k - 1][v][w], (A[k - 1][v][k] + A[k - 1][k][w]))
            }

    for (v in 1..n)
        if (A[n][v][v]<0)
            print("Negative Cycles")



}

private fun baseCases(vertices: List<Vertex>, A: Array<Array<IntArray>>) {
    for (v in vertices)
        for (w in vertices) {
            when {
                v == w -> {
                    A[0][v.key][w.key] = 0
                }
                v.edges.find { it.w == w } != null -> {
                    A[0][v.key][w.key] = v.edges.find { it.w == w }!!.cost
                }
                else -> {
                    A[0][v.key][w.key] = Int.MAX_VALUE
                }
            }
        }
}
