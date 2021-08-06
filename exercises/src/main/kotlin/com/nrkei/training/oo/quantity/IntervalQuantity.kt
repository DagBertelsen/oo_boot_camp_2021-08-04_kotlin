package com.nrkei.training.oo.quantity

import kotlin.math.absoluteValue

// Understands a specific measurement
open class IntervalQuantity internal constructor(amount: Number, protected val unit: Unit) {
    protected val amount = amount.toDouble()

    override fun equals(other: Any?) = this === other || other is IntervalQuantity && this.equals(other)

    private fun equals(other: IntervalQuantity) = this.isCompatible(other) &&
            (this.amount - convertedAmount(other)).absoluteValue < Unit.EPSILON

    private fun isCompatible(other: IntervalQuantity) = this.unit.isCompatible(other.unit)

    internal fun convertedAmount(other: IntervalQuantity) = this.unit.convertedAmount(other.amount, other. unit)

    override fun hashCode() = unit.hashCode(amount)
}
