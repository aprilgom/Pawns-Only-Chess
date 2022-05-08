fun main() {
    val input = readLine()!!
    when {
        input.isEmpty() -> println("")
        input.first() == 'i' -> {
            val output = input.drop(1)
            println(output.toInt() + 1)
        }
        input.first() == 's' -> {
            val output = input.drop(1)
            println(output.reversed())
        }
        else -> println(input)
    }
}