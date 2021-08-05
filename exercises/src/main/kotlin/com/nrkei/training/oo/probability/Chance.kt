package com.nrkei.training.oo.probability

class Chance(likelihoodAsFraction: Number) {
    companion object {
        private const val CERTAIN_FRACTION = 1.0
    }
    private val fraction = likelihoodAsFraction.toDouble()

    override fun equals(other: Any?) =
        this === other || other is Chance && this.equals(other)

    private fun equals(other: Chance) = this.fraction == other.fraction

    override fun hashCode() = fraction.hashCode()

    operator fun not() = Chance(CERTAIN_FRACTION - fraction)

}
