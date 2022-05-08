fun main() {
    val A = readln().toInt()
    val B = readln().toInt()
    val H = readln().toInt()
    if(H > B){
        println("Excess")
    }else if(H < A){
        println("Deficiency")
    }else{
        println("Normal")
    }
}