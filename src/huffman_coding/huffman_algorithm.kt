package huffman_coding

import java.util.*
import kotlin.math.ceil

fun huffman(n: Int, chars: List<Char>, freqs: List<Double>): HuffmanNode {
    val q: PriorityQueue<HuffmanNode> = PriorityQueue { i, ii -> ceil(i.frequency - ii.frequency).toInt() }
    for (i in 0..n) {
        val node = HuffmanNode(chars[i], freqs[i])
        q.add(node)
    }

    while (q.size > 1) {
        val leastFreq = q.poll()
        val scdLeastFreq = q.poll()
        val parentNode = HuffmanNode(
            character = '-',
            frequency = leastFreq.frequency + scdLeastFreq.frequency,
            leftChild = leastFreq,
            rightChild = scdLeastFreq
        )
        q.add(parentNode)
    }

    return q.poll()
}