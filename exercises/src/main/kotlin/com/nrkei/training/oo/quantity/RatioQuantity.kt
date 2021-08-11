package com.nrkei.training.oo.quantity

import com.nrkei.training.oo.quantity.Unit.Companion.NO_UNIT

// Understands a specific measurement
open class RatioQuantity internal constructor(amount: Number, unit: Unit)
    : IntervalQuantity(amount, unit), Comparable<RatioQuantity> {

    open operator fun unaryPlus() = this

    open operator fun unaryMinus() = RatioQuantity(-amount, unit)

    open operator fun plus(other: RatioQuantity) = RatioQuantity(this.amount + convertedAmount(other), this.unit)

    open operator fun plus(other: Zero) = this

    open operator fun minus(other: RatioQuantity) = this + -other

    open operator fun minus(other: Zero) = this

    override fun compareTo(other: RatioQuantity): Int {
        if (this == other) return 0
        return this.amount.compareTo(convertedAmount(other))
    }

    object Zero: RatioQuantity(0, NO_UNIT) {
        override operator fun unaryPlus() = this
        override operator fun unaryMinus() = this
        override operator fun plus(other: Zero) = other
        override operator fun plus(other: RatioQuantity) = other
        override operator fun minus(other: Zero) = other
        override operator fun minus(other: RatioQuantity) = -other
    }
}
