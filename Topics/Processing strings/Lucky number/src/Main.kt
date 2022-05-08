fun main() {
    val ls = readln().toCharArray().toList()
    var firstHalfSum = 0
    for (n in ls.subList(0,ls.size/2)) {
        firstHalfSum += n - '0'
    }
    var secondHalfSum = 0
    for (n in ls.subList(ls.size/2,ls.size)) {
        secondHalfSum += n - '0'
    }
    if (firstHalfSum == secondHalfSum) {
        print("YES")
    } else {
        print("NO")
    }
}