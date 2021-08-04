package com.nrkei.training.oo.unit

import com.nrkei.training.oo.probability.Chance
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

// Ensures Chance operates correctly
internal class ChanceTest {

    companion object {
        private val IMPOSSIBLE = Chance(0)
        private val UNLIKELY = Chance(0.25)
        private val EQUALLY_LIKELY = Chance(0.5)
        private val LIKELY = Chance(0.75)
        private val CERTAIN = Chance(1)
    }

    @Test
    fun equality() {
        assertEquals(LIKELY, Chance(0.75))
        assertNotEquals(LIKELY, Chance(0.25))
        assertNotEquals(LIKELY, Any())
        assertNotEquals(LIKELY, null)
    }

    @Test
    fun sets() {
        assertTrue(LIKELY in hashSetOf(Chance(0.75)))
        assertEquals(1, hashSetOf(LIKELY, Chance(0.75)).size)
    }

}