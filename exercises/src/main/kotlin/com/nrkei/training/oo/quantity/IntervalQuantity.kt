package com.nrkei.training.oo.quantity

import kotlin.math.absoluteValue

// Understands a specific measurement
class IntervalQuantity internal constructor(amount: Number, private val unit: Unit) {
    private val amount = amount.toDouble()

    override fun equals(other: Any?) = this === other || other is IntervalQuantity && this.equals(other)

    private fun equals(other: IntervalQuantity) = this.isCompatible(other) &&
            (this.amount - convertedAmount(other)).absoluteValue < Unit.EPSILON

    private fun isCompatible(other: IntervalQuantity) = this.unit.isCompatible(other.unit)

    private fun convertedAmount(other: IntervalQuantity) = this.unit.convertedAmount(other.amount, other. unit)

    override fun hashCode() = unit.hashCode(amount)
}
