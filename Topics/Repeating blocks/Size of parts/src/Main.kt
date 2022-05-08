fun main() {
    var larger = 0
    var smaller = 0
    var perfect = 0
    val n = readln().toInt()
    repeat(n) {
        val inp = readln().toInt()
        if (inp == -1) {
            smaller ++
        } else if (inp == 1) {
            larger ++
        } else {
            perfect ++
        }
    }
    println("$perfect $larger $smaller")
}