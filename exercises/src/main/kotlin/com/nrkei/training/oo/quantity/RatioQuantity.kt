package com.nrkei.training.oo.quantity

import com.nrkei.training.oo.quantity.Unit.Companion.NO_UNIT

// Understands a specific measurement
class RatioQuantity internal constructor(amount: Number, unit: Unit) : IntervalQuantity(amount, unit) {

    operator fun unaryPlus() = this

    operator fun unaryMinus() = RatioQuantity(-amount, unit)

    operator fun plus(other: RatioQuantity) = RatioQuantity(this.amount + convertedAmount(other), this.unit)

    operator fun plus(other: Zero) = this

    operator fun minus(other: RatioQuantity) = this + -other

    object Zero: IntervalQuantity(0, NO_UNIT) {
        operator fun unaryPlus() = this
        operator fun unaryMinus() = this
        operator fun plus(other: Zero) = this
        operator fun plus(other: RatioQuantity) = other
        operator fun minus(other: Zero) = this
        operator fun minus(other: RatioQuantity) = -other
    }
}
