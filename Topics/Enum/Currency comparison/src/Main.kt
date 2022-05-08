enum class Currency(val currency: String) {
    Germany("Euro"),
    Mali("CFA franc"),
    Dominica("Eastern Caribbean dollar"),
    Canada("Canadian dollar"),
    Spain("Euro"),
    Australia("Australian dollar"),
    Brazil("Brazilian real"),
    Senegal("CFA franc"),
    France("Euro"),
    Grenada("Eastern Caribbean dollar"),
    Kiribati("Australian dollar")
}
fun isExists(name: String): Boolean {
    for (country in Currency.values()) {
        if (country.name == name) {
            return true
        }
    }
    return false
}
fun main() {
    val (a, b) = readln().split(" ")

    print(
        isExists(a) && isExists(b) && Currency.valueOf(a).currency == Currency.valueOf(b).currency
    )
}