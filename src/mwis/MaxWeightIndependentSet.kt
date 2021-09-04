package mwis

import Edge
import kotlin.math.max


data class PathGraphVertex(val name: String, val edgeWeight: Int)

fun computePathGraphMWIS(vertices: List<PathGraphVertex>): Array<Int?> {
    val mwisArray = arrayOfNulls<Int>(vertices.size + 1)
    mwisArray[0] = 0
    mwisArray[1] = vertices[1].edgeWeight
    for (index in 2..vertices.size) {
        val weight1 = mwisArray[index - 1]!!
        val weight2 = mwisArray[index - 2]!! + vertices[index].edgeWeight
        mwisArray[index] = max(weight1, weight2)
    }
    return mwisArray
}

fun getWIS(arrOfMWIS: Array<Int>, vertices: List<PathGraphVertex>): List<PathGraphVertex> {
    val wis = mutableListOf<PathGraphVertex>()
    var i = vertices.lastIndex
    while (i >= 2) {
        if (arrOfMWIS[i - 1] >= arrOfMWIS[i - 2] + vertices[i].edgeWeight) {
            i -= 1
        } else {
            wis.add(vertices[i])
            i -= 2
        }
    }
    return wis
}