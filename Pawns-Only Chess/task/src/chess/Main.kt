package chess

import kotlin.math.abs

object Game {
    var pMovement = Movement(-1,-1,-1,-1,'B')
    fun
}
object Board {
    private val data = MutableList<MutableList<Char>>(8){MutableList<Char>(8){' '}}
    init{
        for (x in 0..7) {
            data[1][x] = 'W'
            data[6][x] = 'B'
        }
    }
    fun movePawn(movement: Movement) {
        Board.data[movement.srcY][movement.srcX] = ' '
        Board.data[movement.destY][movement.destX] = movement.side
    }
    fun print(){
        for (y in 0..7) {
            println("  +"+"---+".repeat(8))
            print("${8 - y} |")
            for(x in 0..7){
                print(" ${data[y][x]} |")
            }
            print("\n")
        }
        println("  +"+"---+".repeat(8))
        println("    a   b   c   d   e   f   g   h")
    }
}
object Rule {
    fun checkMoveValid(movement: Movement, pMovement: Movement) {
        val isBackward = movement.side == 'W' && movement.destY - movement.srcY > 0 ||
                movement.side == 'B' && movement.destY - movement.srcY < 0
        val isNotOnStartLine = movement.side == 'W' && movement.srcY != 6 ||
                movement.side == 'B' && movement.srcY != 1
        val isEnemyOnDest = movement.side == 'W' && Game.board[movement.destY][movement.destX] == 'B' ||
                movement.side == 'B' && Game.board[movement.destY][movement.destX] == 'W'
        val isEnpassant = pMovement.distY == 2 && movement.destX == pMovement.destX && (side == 'W' && prevMove.destY == movement.destY + 1 || side == 'B' && prevMove.destY == movement.destY - 1)
    }
}

class Player (private val name: String, private val side: Char){
    val pawns = MutableList<Pawn>(8){it -> Pawn(if (side == 'W') 1 else 6,it,side)}
}

class Pawn(var y: Int, var x: Int, val side: Char) {
    init{
        Game.board[y][x] = side
    }
}

data class Movement(val srcY: Int, val srcX: Int, val destY: Int, val destX: Int, val side: Char){
    val distY = abs(destY - srcY)
    val distX = abs(destX - srcX)
}

fun main() {
    println("Pawns-Only Chess")
    println("First Player's name:")
    val firstName = readln()
    println("Second Player's name:")
    val secondName = readln()

    var whiteInput = ""
    var blackInput = "a0a0"
    while (true) {
        printBoard(board)
        print("$firstName's turn:")
        whiteInput = readln()
        var whiteValidMsg = checkInputValid(whiteInput, blackInput, board, 'W')
        while (whiteValidMsg != "valid") {
            println(whiteValidMsg)
            print("$firstName's turn:")
            whiteInput = readln()
            whiteValidMsg = checkInputValid(whiteInput, blackInput, board, 'W')
        }
        if (whiteInput == "exit") {
            break
        }
        movePawn(whiteInput, blackInput, board)

        printBoard(board)

        print("$secondName's turn:")
        blackInput = readln()
        var blackValidMsg = checkInputValid(blackInput, whiteInput, board, 'B')
        while (blackValidMsg != "valid") {
            println(blackValidMsg)
            print("$secondName's turn:")
            blackInput = readln()
            blackValidMsg = checkInputValid(blackInput, whiteInput, board, 'B')
        }
        if (blackInput == "exit") {
            break
        }
        movePawn(blackInput, whiteInput, board)
    }
    print("Bye!")
}

fun parseInput(input: String): List<Int>{
    val srcY = '8' - input[1]
    val srcX = input[0] - 'a'
    val destY = '8' - input[3]
    val destX = input[2] - 'a'
    return listOf(srcY, srcX, destY, destX)
}

fun checkInputValid(input: String, prevInput: String, board: MutableList<MutableList<Char>>, side: Char): String{
    if (input == "exit") {
        return "valid"
    }
    val (srcY, srcX, destY, destX) = parseInput(input)
    val (psrcY, _, pdestY, pdestX) = parseInput(prevInput)
    val distY = Math.abs(destY - srcY)
    val distX = Math.abs(destX - srcX)
    val pdistY = Math.abs(pdestY - psrcY)

    val validInputRegex = Regex("[a-h][1-8][a-h][1-8]")
    return when{
        !input.matches(validInputRegex) -> "Invalid Input"
        board[srcY][srcX] != side -> "No ${
            when(side){
                'W' -> "white"
                'B' -> "black"
                else -> "fuck"
        }} pawn at ${input[0]}${input[1]}"

        !(distY in 1..2 && distX in 0..1) ||
                isBackward ||
                isNotOnStartLine && distY == 2 ||
                distX == 1 && !(distY == 1 && (isEnemyOnDest || isEnpassant)) ||
                distX == 0 && board[destY][destX] != ' '
        -> "Invalid Input"
        else ->  "valid"
    }
}

fun movePawn(input: String, prevInput: String, board: MutableList<MutableList<Char>>){
    val (srcY, srcX, destY, destX) = parseInput(input)
    val side = board[srcY][srcX]
    board[destY][destX] = board[srcY][srcX]
    board[srcY][srcX] = ' '

    val (psrcY, _, pdestY, pdestX) = parseInput(prevInput)
    val pdistY = Math.abs(pdestY - psrcY)
    val isEnpassant = pdistY == 2 && destX == pdestX && (side == 'W' && pdestY == destY + 1 || side == 'B' && pdestY == destY - 1)
    if (isEnpassant) {
        board[pdestY][pdestX] = ' '
    }
}