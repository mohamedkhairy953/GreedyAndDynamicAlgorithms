package huffman_coding

data class HuffmanNode(
    val character: Char,
    val frequency: Double,
    val leftChild: HuffmanNode? = null,
    val rightChild: HuffmanNode? = null
)