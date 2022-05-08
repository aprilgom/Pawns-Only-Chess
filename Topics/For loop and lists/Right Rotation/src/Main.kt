fun main() {
    val n = readln().toInt()
    val list = MutableList(n) { readln().toInt() }
    val rotatedList = rotateList(list, readln().toInt())
    println(rotatedList.joinToString(" "))
}
fun rotateList(list: MutableList<Int>, n: Int): MutableList<Int> {
    val ret = MutableList(0) { 0 }
    ret.addAll(list.subList(list.size - n % list.size, list.size))
    ret.addAll(list.subList(0, list.size - n % list.size))
    return ret
}