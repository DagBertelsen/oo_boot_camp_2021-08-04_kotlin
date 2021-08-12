package com.nrkei.training.oo.quantity

import com.nrkei.training.oo.quantity.Unit.Companion.NO_UNIT

abstract class RatioQuantity(amount: Number, unit: Unit)
    : IntervalQuantity(amount, unit), Comparable<RatioQuantity> {
    abstract operator fun unaryPlus(): RatioQuantity

    abstract operator fun unaryMinus(): RatioQuantity

    abstract operator fun plus(other: SimpleRatioQuantity): RatioQuantity

    abstract operator fun plus(other: Zero): RatioQuantity

    abstract operator fun minus(other: SimpleRatioQuantity): RatioQuantity

    abstract operator fun minus(other: Zero): RatioQuantity

    object Zero: RatioQuantity(0, NO_UNIT) {
        override operator fun unaryPlus() = this
        override operator fun unaryMinus() = this
        override operator fun plus(other: Zero) = other
        override operator fun plus(other: SimpleRatioQuantity) = other
        override operator fun minus(other: Zero) = other
        override operator fun minus(other: SimpleRatioQuantity) = -other
        override fun compareTo(other: RatioQuantity) = 0.0.compareTo(other.amount)
    }
}

// Understands a specific measurement
open class SimpleRatioQuantity internal constructor(amount: Number, unit: Unit)
    : RatioQuantity(amount, unit) {

    override operator fun unaryPlus() = this

    override operator fun unaryMinus() = SimpleRatioQuantity(-amount, unit)

    override operator fun plus(other: SimpleRatioQuantity) =
        SimpleRatioQuantity(this.amount + convertedAmount(other), this.unit)

    override operator fun plus(other: Zero) = this

    override operator fun minus(other: SimpleRatioQuantity) = this + -other

    override operator fun minus(other: Zero) = this

    override fun compareTo(other: RatioQuantity): Int {
        if (this == other) return 0
        return this.amount.compareTo(convertedAmount(other))
    }
}
