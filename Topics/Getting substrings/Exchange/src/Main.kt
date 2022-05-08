fun main() {
    val str = readln()
    val sb = StringBuilder()
    sb.append(str[str.length - 1])
    sb.append(str.substring(1, str.length - 1))
    sb.append(str[0])
    print(sb)
}