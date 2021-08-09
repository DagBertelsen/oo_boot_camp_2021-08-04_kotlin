package com.nrkei.training.oo.unit

import com.nrkei.training.oo.orderable.bestOrNull
import com.nrkei.training.oo.probability.Chance
import com.nrkei.training.oo.rectangle.Rectangle
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

// Ensures Orderable behavior implemented correctly in other classes
internal class OrderableTest {

    @Test
    fun `rectangle with largest area`() {
        assertEquals(36.0, listOf(Rectangle(2, 3), Rectangle.square(6.0), Rectangle(5, 7.0)).bestOrNull()?.area())
        assertNull(emptyList<Rectangle>().bestOrNull())
    }

    @Test internal fun `least likely chance`() {
        assertEquals(Chance(0), listOf(Chance(0.1), Chance(0.7), Chance(0), Chance(0.9)).bestOrNull())
        assertNull(emptyList<Chance>().bestOrNull())
    }
}
