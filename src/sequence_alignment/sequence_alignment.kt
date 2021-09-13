package sequence_alignment

data class Item(val value: Int, val size: Int)

fun sequenceAlignment(x: String, y: String, penalties: Map<String, Int>): List<Item> {
    val xChars = x.toCharArray()
    val yChars = y.toCharArray()
    val m = xChars.size
    val n = yChars.size
    val solutions: Array<Array<Int>> =
        arrayOf(
            Array(size = m + 1, init = { 0 }),
            Array(size = n + 1, init = { 0 })
        )

    for (i in 0..m)
        solutions[i][0] = i * (penalties["gab"] ?: 0)
    for (j in 0..m)
        solutions[0][j] = j * (penalties["gab"] ?: 0)

    for (i in 1..m)
        for (j in 1..n) {
            val firstCase = solutions[i - 1][j - 1] + (penalties["x$i-y$j"] ?: 0)
            val secondCase = solutions[i - 1][j] + (penalties["gap"] ?: 0)
            val thirdCase = solutions[i][j - 1] + (penalties["gap"] ?: 0)
            solutions[i][j] = minOf(minOf(firstCase, secondCase), thirdCase)
        }
    return reconstruct(solutions, xChars, yChars, penalties)
}

fun reconstruct(solutions: Array<Array<Int>>, x: CharArray, y: CharArray, penalties: Map<String, Int>): List<Item> {
    val result = mutableListOf<Item>()
    var m = x.size
    var n = y.size
    while (m > 0 && n > 0) {
        val s = solutions[m][n]
        when (s) {
            solutions[m - 1][n - 1] + (penalties["x$m-y$n"] ?: 0) -> {
                //todo mismatch
                m -= 1
                n -= 1
            }
            solutions[m][n - 1] + (penalties["gap"] ?: 0) -> {
                //todo xgap
                n -= 1
            }
            solutions[m - 1][n] + (penalties["gap"] ?: 0) -> {
                //todo ygap
                m -= 1
            }
        }
    }

    return result
}
