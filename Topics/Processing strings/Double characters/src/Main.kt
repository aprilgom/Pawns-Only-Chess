fun main() {
    val str = readln()
    val sb = StringBuilder()
    for (ch in str) {
        sb.append(ch)
        sb.append(ch)
    }
    print(sb)
}