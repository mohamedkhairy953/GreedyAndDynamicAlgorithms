package job_sequencing

import java.util.Comparator

data class Job(val length: Int, val weight: Int)

class DiffComparator : Comparator<Job> {
    override fun compare(a: Job?, b: Job?): Int {
        if (a == null && b == null) return 0
        val firstDiff = a!!.weight - a.length
        val secondDiff = b!!.weight - b.length
        return if (firstDiff == secondDiff) a.weight.compareTo(b.weight)
        else
            firstDiff.compareTo(secondDiff)
    }

}

class RatioComparator : Comparator<Job> {
    override fun compare(a: Job?, b: Job?): Int {
        if ((a == null && b == null) || a!!.length == 0 || b!!.length == 0) return 0

        val firstDiff = a.weight / a.length
        val secondDiff = b.weight / b.length
        return if (firstDiff == secondDiff) a.weight.compareTo(b.weight)
        else
            firstDiff.compareTo(secondDiff)
    }

}

fun getSumOfWeightedCompletionTime(jobs: List<Job>, comparator: Comparator<Job>): Int {
    val sortedWith = jobs.sortedWith(comparator)
    var completionTime = 0
    var sumOfWeightedCompletionTime = 0
    sortedWith.forEach {
        completionTime += it.length
        sumOfWeightedCompletionTime += completionTime * it.weight
    }

    return sumOfWeightedCompletionTime
}