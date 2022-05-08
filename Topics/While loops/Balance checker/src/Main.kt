fun main() {
    var balance = readln().toInt()
    val purchases = readln().split(" ").map{it.toInt()}
    val sum = purchases.sum()
    if (balance >= sum) {
        println("Thank you for choosing us to manage your account! Your balance is ${balance - sum}.")
        return
    }

    for (p in purchases) {
        if (balance - p < 0) {
            println("Error, insufficient funds for the purchase. Your balance is $balance, but you need $p.")
            break;
        }
        balance -= p
    }
}