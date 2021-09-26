package optBst

data class Key(val name: String, val cost: Double, val freq: Double)

fun optBst(
    keys: List<Key>
) {
    val n = keys.size
    val A: Array<Array<Double>> =
        arrayOf(
            Array(size = n + 1, init = { Double.MAX_VALUE }),
            Array(size = n + 1, init = { Double.MAX_VALUE })
        )
    for (i in 1..n + 1) {
        A[i][i - 1] = 0.0
    }
    for (s in 0 until n) {
        for (i in 1..n - s) {
            val j = i + s
            A[i][j] = getMinCostOfAllPossibleRoots(i, j, A) + getKeysCosts(i, j, keys)

        }

    }
    println(A[1][n])

}

fun getKeysCosts(i: Int, j: Int, keys: List<Key>): Double {
    var sum = 0.0
    for (r in i..j) {
        sum += keys[r].freq * keys[r].cost
    }
    return sum
}

fun getMinCostOfAllPossibleRoots(i: Int, j: Int, A: Array<Array<Double>>): Double {
    var minCost = Double.MAX_VALUE
    for (r in i..j) {
        val c = A[i][r - 1] + A[r + 1][j]
        if (c < minCost) {
            minCost = c
        }

    }
    return minCost
}
