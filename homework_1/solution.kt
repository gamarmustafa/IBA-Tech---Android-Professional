fun main() {
    val listInts = listOf(1, 3, 5, 4, 2, 5)
    val resultInts = listInts.uniqueOrdered()
    println(resultInts)

    val listStrs = listOf('a', 'B', 'A', 'b', 'A', 'a')
    val resultStrs = listStrs.uniqueOrdered()
    println(resultStrs)
}

// here I used Comparable instead of Any, so I can use sorted() method
fun <T : Comparable<T>> List<T>.uniqueOrdered(): List<T> {
    return this.distinct().sorted()
}