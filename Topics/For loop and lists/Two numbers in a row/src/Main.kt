fun main() {
    val N = readln().toInt()
    val ls = MutableList(N) {0 }
    for (i in 0..N-1) {
        ls[i] = readln().toInt()
    }
    val pm = readln().split(" ")
    val p = pm[0].toInt()
    val m = pm[1].toInt()

    val pIndices = findIndices(p,ls)
    val mIndices = findIndices(m,ls)

    var isPNextToM = false
    for (pi in pIndices) {
        for (mi in mIndices) {
            if (Math.abs(pi - mi) <= 1) {
                isPNextToM = true
                break
            }
        }
        if (isPNextToM) {
            break
        }
    }

    if (isPNextToM) {
        print("NO")
    } else {
        print("YES")
    }
}

fun findIndices(target: Int, list: MutableList<Int>): MutableList<Int>{
    val ret = MutableList(0) {0 }
    for (i in list.indices){
        if (target == list[i]) {
            ret.add(i)
        }
    }
    return ret
}