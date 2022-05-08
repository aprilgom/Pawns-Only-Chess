fun main() {
    val year = readln().toInt()
    if (year % 400 == 0) {
        print("Leap")
        return
    }
    if (year % 100 == 0) {
        print("Regular")
        return
    }
    if (year % 4 == 0) {
        print("Leap")
        return
    }
    print("Regular")
}