package com.nrkei.training.oo.quantity

import kotlin.math.absoluteValue

// Understands a specific measurement
class Quantity internal constructor(amount: Number, private val unit: Unit) {
    private val amount = amount.toDouble()

    override fun equals(other: Any?) = this === other || other is Quantity && this.equals(other)

    private fun equals(other: Quantity) = this.isCompatible(other) &&
            (this.amount - convertedAmount(other)).absoluteValue < Unit.EPSILON

    private fun isCompatible(other: Quantity) = this.unit.isCompatible(other.unit)

    private fun convertedAmount(other: Quantity) = this.unit.convertedAmount(other.amount, other. unit)

    override fun hashCode() = unit.hashCode(amount)

    operator fun unaryPlus() = this

    operator fun unaryMinus() = Quantity(-amount, unit)

    operator fun plus(other: Quantity) = Quantity(this.amount + convertedAmount(other), this.unit)

    operator fun minus(other: Quantity) = this + -other
}
