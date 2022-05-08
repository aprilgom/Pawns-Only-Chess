fun main() {
    var i = readln().toInt()
    print("$i ")
    while (i != 1) {
        if (i % 2 == 0) {
            i /= 2
        } else {
            i *= 3
            i++
        }
        print("$i ")
    }
}