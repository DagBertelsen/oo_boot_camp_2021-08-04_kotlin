package com.nrkei.training.oo.unit

import com.nrkei.training.oo.quantity.Unit.Companion.cups
import com.nrkei.training.oo.quantity.Unit.Companion.gallons
import com.nrkei.training.oo.quantity.Unit.Companion.pints
import com.nrkei.training.oo.quantity.Unit.Companion.tablespoons
import com.nrkei.training.oo.quantity.Unit.Companion.teaspoons
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class QuantityTest {

    @Test fun `equality of like Units`() {
        assertEquals(8.tablespoons, 8.tablespoons)
        assertNotEquals(8.tablespoons, 6.tablespoons)
        assertNotEquals(8.tablespoons, Any())
        assertNotEquals(8.tablespoons, null)
    }

    @Test fun `equality of different Units`() {
        assertEquals(8.tablespoons, 0.5.cups)
        assertEquals(768.teaspoons, 1.gallons)
        assertNotEquals(8.tablespoons, 8.pints)
    }

    @Test
    fun `Chance in sets`() {
        assertTrue(8.tablespoons in hashSetOf(8.tablespoons))
        assertEquals(1, hashSetOf(8.tablespoons, 8.tablespoons).size)
    }

    @Test fun hash() {
        assertEquals(8.tablespoons.hashCode(), 8.tablespoons.hashCode())
        assertEquals(8.tablespoons.hashCode(), 0.5.cups.hashCode())
    }
}
