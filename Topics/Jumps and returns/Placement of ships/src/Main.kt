fun main() {
    val rows = MutableList<Int>(5) { it + 1 }
    val cols = MutableList<Int>(5) { it + 1 }
    repeat(3) {
        val (x, y) = readln().split(" ").map { it.toInt() }
        rows.remove(x)
        cols.remove(y)
    }
    println(rows.joinToString(" "))
    println(cols.joinToString(" "))
}