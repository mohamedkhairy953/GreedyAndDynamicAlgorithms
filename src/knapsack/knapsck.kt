package knapsack

data class Item(val value: Int, val size: Int)

fun knapsack(items: List<Item>, capacity: Int): List<Item> {

    val solutions: Array<Array<Int>> =
        arrayOf(
            Array(size = items.size + 1, init = { 0 }),
            Array(size = capacity + 1, init = { 0 })
        )

    for (c in 0..capacity)
        solutions[0][c] = 0

    for (i in 1..items.size)
        for (c in 0..capacity) {
            val s = items[i].size
            if (s > c) {
                solutions[i][c] = solutions[i - 1][c]
            } else {
                solutions[i][c] = maxOf(solutions[i - 1][c], (solutions[i - 1][c - s] + items[i].value))
            }
        }
    return reconstruct(solutions, capacity, items)
}

fun reconstruct(solutions: Array<Array<Int>>, capacity: Int, items: List<Item>): List<Item> {
    val result = mutableListOf<Item>()
    var remainingCapacity = capacity
    for (i in items.lastIndex downTo 1) {
        val size = items[i].size
        if (size <= remainingCapacity
            && (solutions[i - 1][remainingCapacity - size] + items[i].value > solutions[i - 1][remainingCapacity])
        ) {
            result.add(items[i])
            remainingCapacity -= size
        }
    }
    return result
}
