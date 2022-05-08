fun main() {
    val n = readln().toInt()
    var countA = 0
    var countB = 0
    var countC = 0
    var countD = 0
    repeat(n) {
        val score = readln().toInt()
        if (score == 5) {
            countA++
        } else if (score == 4) {
            countB++
        } else if (score == 3) {
            countC++
        } else if (score == 2) {
            countD++
        }
    }
    print("$countD $countC $countB $countA")
}