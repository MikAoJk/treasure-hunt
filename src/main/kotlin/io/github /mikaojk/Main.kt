package io.github.mikaojk

import io.github.mikaojk.game.playGame
import io.github.mikaojk.player.Player

fun main() {

    val battlefields = 5
    val playerOne = Player(name = "Computer", soilders = 100, wins = 0)
    val playerTwo = Player(name = "Joakim", soilders = 100, wins = 0)

    playGame(playerOne, playerTwo, battlefields)
}
