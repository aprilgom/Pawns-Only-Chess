fun main() {
    val report = readLine()!!
    //write your code here.
    val regex = Regex(".. wrong answers")
    print(!report.matches(regex))
}