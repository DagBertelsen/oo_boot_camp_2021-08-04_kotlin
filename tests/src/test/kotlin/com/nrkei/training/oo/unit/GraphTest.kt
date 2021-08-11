package com.nrkei.training.oo.unit

import com.nrkei.training.oo.graph.Node
import com.nrkei.training.oo.quantity.Unit.Companion.hops
import com.nrkei.training.oo.quantity.Unit.Companion.units
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
        assertEquals(0.hops, B hopCount B)
        assertEquals(1.hops, B hopCount A)
        assertEquals(1.hops, B hopCount F)
        assertEquals(2.hops, B hopCount D)
        assertEquals(3.hops, C hopCount F)
        assertThrows<IllegalArgumentException> { G hopCount B }
        assertThrows<IllegalArgumentException> { A hopCount B }
        assertThrows<IllegalArgumentException> { B hopCount G }
    }

    @Test internal fun cost() {
        assertEquals(0.units, B cost B)
        assertEquals(5.units, B cost A)
        assertEquals(4.units, B cost F)
        assertEquals(7.units, B cost D)
        assertEquals(10.units, C cost F)
        assertThrows<IllegalArgumentException> { G cost B }
        assertThrows<IllegalArgumentException> { A cost B }
        assertThrows<IllegalArgumentException> { B cost G }
    }

    @Test fun path() {
        assertPath(A, A, 0, 0)
        assertPath(B, A, 1, 5)
        assertPath(B, F, 1, 4)
        assertPath(B, D, 2, 7)
        assertPath(C, F, 4, 10)
        assertThrows<IllegalArgumentException> { G path B }
        assertThrows<IllegalArgumentException> { A path B }
        assertThrows<IllegalArgumentException> { B path G }
    }

    @Test fun `all paths between two Nodes`() {
        assertEquals(1, (A paths A).count())
        assertEquals(1, (B paths A).count())
        assertEquals(1, (B paths F).count())
        assertEquals(2, (B paths D).count())
        assertEquals(3, (C paths F).count())
        assertEquals(0, (G paths B).count())
        assertEquals(0, (B paths G).count())
        assertEquals(0, (A paths B).count())
    }

    @Test fun `all paths from one Nodes`() {
        assertEquals(1, A.paths().count())
        assertEquals(9, B.paths().count())
        assertEquals(15, C.paths().count())
        assertEquals(6, D.paths().count())
        assertEquals(7, E.paths().count())
        assertEquals(1, F.paths().count())
        assertEquals(1, G.paths().count())
    }

    private fun assertPath(source: Node, destination: Node, expectedHopCount: Int, expectedCost: Number) {
        (source path destination).also { path ->
            assertEquals(expectedHopCount.hops, path.hopCount())
            assertEquals(expectedCost.units, path.cost())
        }
    }
}
