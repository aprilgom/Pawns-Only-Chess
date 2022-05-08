fun main() {
    val n = readln().toInt()
    val first = n / 100
    val second = n % 100 / 10
    val third = n % 10
    println(third * 100 + second * 10 + first)
}