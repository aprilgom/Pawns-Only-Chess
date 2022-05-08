fun main() {
    val n = readln().toInt()
    var status = when(n){
        in -1..0 -> "no army"
        in 1..4 -> "few"
        in 5..9 -> "several"
        in 10..19 -> "pack"
        in 20..49 -> "lots"
        in 50..99 -> "horde"
        in 100..249 -> "throng"
        in 250..499 -> "swarm"
        in 500..999 -> "zounds"
        else -> "legion"
    }
    print(status)
}