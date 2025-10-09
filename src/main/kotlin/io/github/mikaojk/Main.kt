package io.github.mikaojk

import kotlin.math.abs
import kotlin.random.Random

const val GRID_SIZE = 5
var treasureX = 0
var treasureY = 0
var playerX = 0
var playerY = 0
var moves = 0
const val PLAYER_EMOJI = "\uD83D\uDC4B"

fun main() {
    treasureX = Random.nextInt(GRID_SIZE)
    treasureY = Random.nextInt(GRID_SIZE)
    playerX = GRID_SIZE / 2
    playerY = GRID_SIZE / 2
    moves = 0

    println("\n\n🏝️ Welcome to Treasure Hunt!")
    println("Find the hidden treasure on a $GRID_SIZE x $GRID_SIZE grid.")
    println("Use commands: N, E, W, S, or north, south, east, west to move.")

    displayGrid()
    playGame()
}

fun playGame() {
    while (true) {
        print("Enter your move: ")
        val move = readlnOrNull()?.trim()?.lowercase() ?: ""
        if (makeMove(move)) {
            moves++
            displayGrid()
            if (playerX == treasureX && playerY == treasureY) {
                println("🎉 You found the treasure in $moves moves! Congratulations!")
                break
            } else {
                giveHint()
            }
        } else {
            println("❌ Invalid move. Try other moves.")
        }
    }
}


fun makeMove(direction: String): Boolean {
    return when (direction) {
        "north", "n" -> {
            if (playerY > 0) {
                playerY--
                true
            } else false
        }

        "south", "s" -> {
            if (playerY < GRID_SIZE - 1) {
                playerY++
                true
            } else false
        }

        "east", "e" -> {
            if (playerX < GRID_SIZE - 1) {
                playerX++
                true
            } else false
        }

        "west", "w" -> {
            if (playerX > 0) {
                playerX--
                true
            } else false
        }

        else -> false
    }
}

fun displayGrid() {
    println("\nCurrent Grid:")
    for (y in 0 until GRID_SIZE) {
        for (x in 0 until GRID_SIZE) {
            if (x == playerX && y == playerY) {
                print("[$PLAYER_EMOJI] ")
            } else {
                print("[⬛] ")
            }
        }
        println()
    }
    println()
}

fun giveHint() {
    val distance = abs(playerX - treasureX) + abs(playerY - treasureY)
    when (distance) {
        1, 2 -> println("🔥 Great! You're getting closer!")
        3, 4 -> println("🌤️ Watch out! You're moving farther.")
        else -> println("❄️ Damn.. You're farther.")
    }
}
