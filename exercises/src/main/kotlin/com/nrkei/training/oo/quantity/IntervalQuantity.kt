package com.nrkei.training.oo.quantity

import com.nrkei.training.oo.orderable.Orderable
import kotlin.math.absoluteValue

// Understands a specific measurement
open class IntervalQuantity internal constructor(amount: Number, protected val unit: Unit)
    : Orderable<IntervalQuantity> {
    protected val amount = amount.toDouble()

    @Suppress("ComplexMethod")
    override fun equals(other: Any?) = this === other || other is IntervalQuantity && this.equals(other)

    private fun equals(other: IntervalQuantity) = this.isCompatible(other) &&
            (this.amount - convertedAmount(other)).absoluteValue < Unit.EPSILON

    private fun isCompatible(other: IntervalQuantity) = this.unit.isCompatible(other.unit)

    internal fun convertedAmount(other: IntervalQuantity) = this.unit.convertedAmount(other.amount, other. unit)

    override fun hashCode() = unit.hashCode(amount)

    override fun isBetterThan(other: IntervalQuantity) = this.amount > convertedAmount(other)
}
