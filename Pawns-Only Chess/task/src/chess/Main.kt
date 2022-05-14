package chess

import java.lang.Exception
import kotlin.math.abs

class Game {
    companion object {
        val board: Board = Board()
    }

    fun getInput(side: Char): Movement {
        val input = readln()
        val validInputRegex = Regex("[a-h][1-8][a-h][1-8]")
        try {
            if (!input.matches(validInputRegex)) {
                throw error("Invalid Input")
            }
        } catch (e: Exception) {
            print("Invalid Input")
        }
        val srcY = '8' - input[1]
        val srcX = input[0] - 'a'
        val destY = '8' - input[3]
        val destX = input[2] - 'a'
        return Movement(srcY, srcX, destY, destX, side)
    }

}

class Board {
    private val board = MutableList<MutableList<Char>>(8){MutableList<Char>(8){' '}}
    init{
        for (x in 0..7) {
            board[1][x] = 'W'
            board[6][x] = 'B'
        }
    }
    fun movePawn(movement: Movement) {
        try {
            if (board[movement.srcY][movement.srcX] != movement.side) {
                throw error(0)
            }
        } catch (e: Exception) {
            print(
                "No ${
                when(movement.side){
                    'W' -> "white"
                    'B' -> "black"
                    else -> "no side"
                }} pawn at ${'a' + movement.srcX}${'8' - movement.srcY}"
            )
        }
        board[movement.srcY][movement.srcX] = ' '
        board[movement.destY][movement.destX] = movement.side
    }
    fun print(){
        for (y in 0..7) {
            println("  +"+"---+".repeat(8))
            print("${8 - y} |")
            for(x in 0..7){
                print(" ${board[y][x]} |")
            }
            print("\n")
        }
        println("  +"+"---+".repeat(8))
        println("    a   b   c   d   e   f   g   h")
    }
}

object RuleChecker {
}

class Rule (val isOk: (Movement, Char) -> Boolean, val errorOp: () -> String){
    fun check(movement: Movement, side: Char){
        try {
            if (isOk(movement, side))
                throw error(0)
        } catch (e: Exception){
            errorOp()
        }
    }
}

/*
fun checkMoveValid(movement: Movement, pMovement: Movement) {
    val isBackward = movement.side == 'W' && movement.destY - movement.srcY > 0 ||
            movement.side == 'B' && movement.destY - movement.srcY < 0
    val isNotOnStartLine = movement.side == 'W' && movement.srcY != 6 ||
            movement.side == 'B' && movement.srcY != 1
    val isEnemyOnDest = movement.side == 'W' && Board[movement.destY][movement.destX] == 'B' ||
            movement.side == 'B' && Board[movement.destY][movement.destX] == 'W'
    val isEnpassant = pMovement.distY == 2 && movement.destX == pMovement.destX && (side == 'W' && prevMove.destY == movement.destY + 1 || side == 'B' && prevMove.destY == movement.destY - 1)
}
 */

object PawnFactory {

    private val ruleList: MutableList<Rule> = mutableListOf<Rule>()

    fun addRule(rule: Rule) {
        ruleList.add(rule)
    }

    fun createPawn(side: Char): Piece {
        return Piece(side, ruleList)
    }

}

class Piece(val side: Char,val ruleList: MutableList<Rule>) {

    fun check(movement: Movement, side:Char){
        ruleList.forEach {
            it.check(movement, side)
        }
    }

    fun addRule(rule: Rule) {
        ruleList.add(rule)
    }

    fun sideEqualTo(side: Char): Boolean {
        return this.side == side
    }
}

class Player (private val name: String, private val side: Char){
    val pawns = MutableList<Pawn>(8){it -> Pawn(if (side == 'W') 1 else 6,it,side)}
}

data class Movement(val srcY: Int, val srcX: Int, val destY: Int, val destX: Int, val side: Char){
    val diffY = destY - srcY
    val diffX = destX - srcX
    val distY = abs(diffY)
    val distX = abs(diffX)
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
        Board.print()
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


fun checkInputValid(input: String, prevInput: String, board: MutableList<MutableList<Char>>, side: Char): String{
    if (input == "exit") {
        return "valid"
    }
    val (srcY, srcX, destY, destX) = parseInput(input)
    val (psrcY, _, pdestY, pdestX) = parseInput(prevInput)
    val distY = Math.abs(destY - srcY)
    val distX = Math.abs(destX - srcX)
    val pdistY = Math.abs(pdestY - psrcY)


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