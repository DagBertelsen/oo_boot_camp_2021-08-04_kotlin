package com.nrkei.training.oo.quantity

import kotlin.math.absoluteValue

// Understands a specific measurement
class RatioQuantity internal constructor(amount: Number, private val unit: Unit) {
    private val amount = amount.toDouble()

    override fun equals(other: Any?) = this === other || other is RatioQuantity && this.equals(other)

    private fun equals(other: RatioQuantity) = this.isCompatible(other) &&
            (this.amount - convertedAmount(other)).absoluteValue < Unit.EPSILON

    private fun isCompatible(other: RatioQuantity) = this.unit.isCompatible(other.unit)

    private fun convertedAmount(other: RatioQuantity) = this.unit.convertedAmount(other.amount, other. unit)

    override fun hashCode() = unit.hashCode(amount)

    operator fun unaryPlus() = this

    operator fun unaryMinus() = RatioQuantity(-amount, unit)

    operator fun plus(other: RatioQuantity) = RatioQuantity(this.amount + convertedAmount(other), this.unit)

    operator fun minus(other: RatioQuantity) = this + -other
}
