package io.github.mikaojk

import kotlin.random.Random

private const val GRID_SIZE = 5
private var treasureX = 0
private var treasureY = 0
private var playerX = 0
private var playerY = 0
private var moves = 0
private const val PLAYER_EMOJI = "\uD83D\uDC4B"

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

private fun playGame() {
    while (true) {
        print("Enter your move: ")
        val move = readLine()?.trim()?.lowercase() ?: ""
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

private fun makeMove(direction: String): Boolean {
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

private fun displayGrid() {
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

private fun giveHint() {
    val distance = kotlin.math.abs(playerX - treasureX) + kotlin.math.abs(playerY - treasureY)
    when (distance) {
        1, 2 -> println("🔥 Great! You're getting closer!")
        3, 4 -> println("🌤️ Watch out! You're moving farther.")
        else -> println("❄️ Damn.. You're farther.")
    }
}
