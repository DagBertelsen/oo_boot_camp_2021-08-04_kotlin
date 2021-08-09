/*
 * Copyright (c) 2021 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.rectangle

// Understands a four-sided polygon with sides at right angles
class Rectangle(width: Number, height: Number) {
    private val width = width.toDouble()
    private val height = height.toDouble()

    init {
        require(width.toDouble() > 0 && height.toDouble() > 0 )
        { "Dimensions must be greater than zero" }
    }

    companion object {
        fun square(side: Number) = Rectangle(side, side)
    }

    fun area() = width * height
    val area get() = area()

    fun perimeter() = 2 * (width + height)

}
