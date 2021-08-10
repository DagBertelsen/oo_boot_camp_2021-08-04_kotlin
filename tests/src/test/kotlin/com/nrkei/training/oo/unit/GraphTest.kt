package com.nrkei.training.oo.unit

import com.nrkei.training.oo.graph.Node
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

// Ensures graph operations work correctly
internal class GraphTest {
    companion object {
        private val A = Node()
        private val B = Node()
        private val C = Node()
        private val D = Node()
        private val E = Node()
        private val F = Node()
        private val G = Node()

        init {
            B cost 5 to A
            B cost 6 to C cost 7 to D cost 2 to E cost 3 to B cost 4 to F
            C cost 1 to D
            C cost 8 to E
        }
    }

    @Test
    internal fun `can reach`() {
        assertTrue(B canReach B)
        assertTrue(B canReach A)
        assertTrue(B canReach F)
        assertTrue(B canReach D)
        assertTrue(C canReach F)
        assertFalse(G canReach B)
        assertFalse(A canReach B)
        assertFalse(B canReach G)
    }

    @Test internal fun `hop count`() {
        assertEquals(0, B hopCount B)
        assertEquals(1, B hopCount A)
        assertEquals(1, B hopCount F)
        assertEquals(2, B hopCount D)
        assertEquals(3, C hopCount F)
        assertThrows<IllegalArgumentException> { G hopCount B }
        assertThrows<IllegalArgumentException> { A hopCount B }
        assertThrows<IllegalArgumentException> { B hopCount G }
    }

    @Test internal fun cost() {
        assertEquals(0.0, B cost B)
        assertEquals(5.0, B cost A)
        assertEquals(4.0, B cost F)
        assertEquals(7.0, B cost D)
        assertEquals(10.0, C cost F)
        assertThrows<IllegalArgumentException> { G cost B }
        assertThrows<IllegalArgumentException> { A cost B }
        assertThrows<IllegalArgumentException> { B cost G }
    }
}
