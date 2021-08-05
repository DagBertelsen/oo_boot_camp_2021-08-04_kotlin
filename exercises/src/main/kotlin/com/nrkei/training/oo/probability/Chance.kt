package com.nrkei.training.oo.probability

import kotlin.math.absoluteValue

// Understands the likelihood of something specific occurring
class Chance(likelihoodAsFraction: Number) {
    companion object {
        private const val CERTAIN_FRACTION = 1.0
        private const val EPSILON = 1e-10
    }
    private val fraction = likelihoodAsFraction.toDouble()

    @Suppress("ComplexMethod")
    override fun equals(other: Any?) =
        this === other || other is Chance && this.equals(other)

    private fun equals(other: Chance) = (this.fraction - other.fraction).absoluteValue < EPSILON

    override fun hashCode() = (fraction / EPSILON).toLong().hashCode()

    operator fun not() = Chance(CERTAIN_FRACTION - fraction)

    infix fun and(other: Chance) = Chance(this.fraction * other.fraction)

}
