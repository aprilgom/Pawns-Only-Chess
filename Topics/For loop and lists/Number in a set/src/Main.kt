fun main() {
    val N = readln().toInt()
    val list = MutableList(N) { readln().toInt() }
    val M = readln().toInt()
    if (list.contains(M)) {
        print("YES")
    } else {
        print("NO")
    }

    // write your code here
}