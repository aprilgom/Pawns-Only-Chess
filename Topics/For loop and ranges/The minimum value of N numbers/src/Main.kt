fun main() {
    val n = readln().toInt()
    var smallest = Integer.MAX_VALUE
    repeat(n){
        val inp = readln().toInt()
        if (inp < smallest) {
            smallest = inp
        }
    }
    println(smallest)
}