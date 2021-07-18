/*
 * Copyright (c) 2021 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.oo_boot_camp.unit

import com.nrkei.oo_boot_camp.rectangle.Rectangle
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Ensures Rectangle operates correctly
internal class RectangleTest {

    @Test fun area() {
        assertEquals(24.0, Rectangle(4.0, 6.0).area())
    }
}