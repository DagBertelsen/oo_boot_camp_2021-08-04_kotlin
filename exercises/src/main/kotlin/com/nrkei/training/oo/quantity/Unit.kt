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

        private val INCH = Unit()
        private val FOOT = Unit(12, INCH)
        private val YARD = Unit(3, FOOT)
        private val FATHOM = Unit(6, FOOT)
        private val CHAIN = Unit(22, YARD)
        private val FURLONG = Unit(10, CHAIN)
        private val MILE = Unit(8, FURLONG)
        private val LEAGUE = Unit(3, MILE)

        val Number.inches get() = Quantity(this, INCH)
        val Number.feet get() = Quantity(this, FOOT)
        val Number.yards get() = Quantity(this, YARD)
        val Number.fathoms get() = Quantity(this, FATHOM)
        val Number.chains get() = Quantity(this, CHAIN)
        val Number.furlongs get() = Quantity(this, FURLONG)
        val Number.miles get() = Quantity(this, MILE)
        val Number.leagues get() = Quantity(this, LEAGUE)
    }

    private val baseUnit: Unit
    private val baseUnitRatio: Double

    private constructor() {
        baseUnit = this
        baseUnitRatio = 1.0
    }

    private constructor(relativeRatio: Number, relativeUnit: Unit) {
        baseUnit = relativeUnit.baseUnit
        baseUnitRatio = relativeRatio.toDouble() * relativeUnit.baseUnitRatio
    }

    internal fun convertedAmount(otherAmount: Double, other: Unit) =
        (otherAmount * other.baseUnitRatio / this.baseUnitRatio).also {
            require(this.isCompatible(other)) { "Cannot perform arithmetic on incompatible units" }
        }

    internal fun hashCode(amount: Double) = (amount * baseUnitRatio).hashCode()

    internal fun isCompatible(other: Unit) = this.baseUnit == other.baseUnit
}
