package io.github.mikaojk

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


internal class MainTest {

    @BeforeEach
    fun setup() {
        treasureX = 2
        treasureY = 2
        playerX = 2
        playerY = 2
        moves = 0
    }

    @Test
    fun testMakeMoveNorth() {
        playerY = 2
        val result = makeMove("north")
        assertTrue(result)
        assertEquals(1, playerY)
    }

    @Test
    fun testMakeMoveSouth() {
        playerY = 2
        val result = makeMove("south")
        assertTrue(result)
        assertEquals(3, playerY)
    }

    @Test
    fun testMakeMoveEast() {
        playerX = 2
        val result = makeMove("east")
        assertTrue(result)
        assertEquals(3, playerX)
    }

    @Test
    fun testMakeMoveWest() {
        playerX = 2
        val result = makeMove("west")
        assertTrue(result)
        assertEquals(1, playerX)
    }

    @Test
    fun testMakeMoveInvalidBoundary() {
        playerY = 0
        assertFalse(makeMove("north"))
        playerY = GRID_SIZE - 1
        assertFalse(makeMove("south"))
        playerX = 0
        assertFalse(makeMove("west"))
        playerX = GRID_SIZE - 1
        assertFalse(makeMove("east"))
    }

    @Test
    fun testMakeMoveInvalidCommand() {
        assertFalse(makeMove("jump"))
        assertFalse(makeMove(""))
    }

    @Test
    fun testGiveHintFarther() {
        playerX = 0; playerY = 0; treasureX = 4; treasureY = 4
        val output = captureOutput { giveHint() }
        assertTrue(output.contains("❄️ Damn.. You're farther."))
    }

    @Test
    fun testGiveHintCloser() {
        playerX = 2; playerY = 1; treasureX = 2; treasureY = 2
        val output = captureOutput { giveHint() }
        assertTrue(output.contains("🔥 Great! You're getting closer!"))
    }

    @Test
    fun testGiveHintWatchOut() {
        playerX = 2; playerY = 0; treasureX = 2; treasureY = 4
        val output = captureOutput { giveHint() }
        assertTrue(output.contains("🌤️ Watch out! You're moving farther."))
    }

    private fun captureOutput(block: () -> Unit): String {
        val stream = java.io.ByteArrayOutputStream()
        val oldOut = System.out
        System.setOut(java.io.PrintStream(stream))
        try {
            block()
        } finally {
            System.setOut(oldOut)
        }
        return stream.toString()
    }
}
