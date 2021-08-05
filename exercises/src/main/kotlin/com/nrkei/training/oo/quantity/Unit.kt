package com.nrkei.training.oo.quantity

// Understands a specific metric
class Unit {
    companion object {

        private val TEASPOON = Unit()
        private val TABLESPOON = Unit(3, TEASPOON)
        private val OUNCE = Unit(2, TABLESPOON)
        private val CUP = Unit(8, OUNCE)
        private val PINT = Unit(2, CUP)
        private val QUART = Unit(2, PINT)
        private val GALLON = Unit(4, QUART)

        val Number.teaspoons get() = Quantity(this, TEASPOON)
        val Number.tablespoons get() = Quantity(this, TABLESPOON)
        val Number.ounces get() = Quantity(this, OUNCE)
        val Number.cups get() = Quantity(this, CUP)
        val Number.pints get() = Quantity(this, PINT)
        val Number.quarts get() = Quantity(this, QUART)
        val Number.gallons get() = Quantity(this, GALLON)
    }

    private val baseUnitRatio: Double

    private constructor() {
        baseUnitRatio = 1.0
    }

    private constructor(relativeRatio: Number, relativeUnit: Unit) {
        baseUnitRatio = relativeRatio.toDouble() * relativeUnit.baseUnitRatio
    }

    internal fun convertedAmount(otherAmount: Double, other: Unit) =
        otherAmount * other.baseUnitRatio / this.baseUnitRatio

    internal fun hashCode(amount: Double) = (amount * baseUnitRatio).hashCode()
}
