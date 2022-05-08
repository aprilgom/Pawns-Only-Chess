fun main() {
    val n = readln().toInt()
    var max = -1
    repeat(n){
        val k = readln().toInt()
        if (k % 4 == 0 && k > max) {
            max = k
        }
    }
    println(max)
}