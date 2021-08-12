package com.nrkei.training.oo.quantity

// Understands a specific metric
class Unit {
    companion object {
        internal const val EPSILON = 1e-10

        private val TEASPOON = Unit()
        private val TABLESPOON = Unit(3, TEASPOON)
        private val OUNCE = Unit(2, TABLESPOON)
        private val CUP = Unit(8, OUNCE)
        private val PINT = Unit(2, CUP)
        private val QUART = Unit(2, PINT)
        private val GALLON = Unit(4, QUART)

        val Number.teaspoons get() = SimpleRatioQuantity(this, TEASPOON)
        val Number.tablespoons get() = SimpleRatioQuantity(this, TABLESPOON)
        val Number.ounces get() = SimpleRatioQuantity(this, OUNCE)
        val Number.cups get() = SimpleRatioQuantity(this, CUP)
        val Number.pints get() = SimpleRatioQuantity(this, PINT)
        val Number.quarts get() = SimpleRatioQuantity(this, QUART)
        val Number.gallons get() = SimpleRatioQuantity(this, GALLON)

        private val INCH = Unit()
        private val FOOT = Unit(12, INCH)
        private val YARD = Unit(3, FOOT)
        private val FATHOM = Unit(6, FOOT)
        private val CHAIN = Unit(22, YARD)
        private val FURLONG = Unit(10, CHAIN)
        private val MILE = Unit(8, FURLONG)
        private val LEAGUE = Unit(3, MILE)

        val Number.inches get() = SimpleRatioQuantity(this, INCH)
        val Number.feet get() = SimpleRatioQuantity(this, FOOT)
        val Number.yards get() = SimpleRatioQuantity(this, YARD)
        val Number.fathoms get() = SimpleRatioQuantity(this, FATHOM)
        val Number.chains get() = SimpleRatioQuantity(this, CHAIN)
        val Number.furlongs get() = SimpleRatioQuantity(this, FURLONG)
        val Number.miles get() = SimpleRatioQuantity(this, MILE)
        val Number.leagues get() = SimpleRatioQuantity(this, LEAGUE)

        private val CELSIUS = Unit()
        private val FAHRENHEIT = Unit(5/9.0, 32, CELSIUS)
        private val GAS_MARK = Unit(125/9.0, -218.0/25, CELSIUS)
        private val KELVIN = Unit(1, 273.15, CELSIUS)
        private val RANKINE = Unit(5/9.0, 491.67, CELSIUS)

        val Number.celsius get() = IntervalQuantity(this, CELSIUS)
        val Number.fahrenheit get() = IntervalQuantity(this, FAHRENHEIT)
        val Number.gasMark get() = IntervalQuantity(this, GAS_MARK)
        val Number.kelvin get() = IntervalQuantity(this, KELVIN)
        val Number.rankine get() = IntervalQuantity(this, RANKINE)

        private val SECOND = Unit()
        private val MINUTE = Unit(60, SECOND)
        private val HOUR = Unit(60, MINUTE)
        private val DAY = Unit(24, HOUR)
        private val WEEK = Unit(7, DAY)

        val Number.seconds get() = SimpleRatioQuantity(this, SECOND)
        val Number.minutes get() = SimpleRatioQuantity(this, MINUTE)
        val Number.hours get() = SimpleRatioQuantity(this, HOUR)
        val Number.days get() = SimpleRatioQuantity(this, DAY)
        val Number.weeks get() = SimpleRatioQuantity(this, WEEK)

        private val UNIT = Unit()
        private val PERCENTAGE = Unit(1/100.0, UNIT)

        val Number.units get() = SimpleRatioQuantity(this, UNIT)
        val Number.hops get() = SimpleRatioQuantity(this, UNIT)
        val Number.percent get() = SimpleRatioQuantity(this, PERCENTAGE)

        internal val NO_UNIT = Unit()

        val universal_zero = RatioQuantity.Zero
    }

    private val baseUnit: Unit
    private val baseUnitRatio: Double
    private val offset: Double

    private constructor() {
        baseUnit = this
        baseUnitRatio = 1.0
        offset = 0.0
    }

    private constructor(relativeRatio: Number, offset: Number, relativeUnit: Unit) {
        baseUnit = relativeUnit.baseUnit
        baseUnitRatio = relativeRatio.toDouble() * relativeUnit.baseUnitRatio
        this.offset = offset.toDouble()
    }

    private constructor(relativeRatio: Number, relativeUnit: Unit) : this(relativeRatio, 0.0, relativeUnit)

    internal fun convertedAmount(otherAmount: Double, other: Unit) =
        ((otherAmount - other.offset) * other.baseUnitRatio / this.baseUnitRatio + this.offset).also {
            require(this.isCompatible(other)) { "Cannot perform arithmetic on incompatible units" }
        }

    internal fun hashCode(amount: Double) = ((amount - offset) * baseUnitRatio / EPSILON).toLong().hashCode()

    @Suppress("ComplexMethod")
    internal fun isCompatible(other: Unit) = this == NO_UNIT || other == NO_UNIT || this.baseUnit == other.baseUnit
}
