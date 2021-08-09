package com.nrkei.training.oo.orderable

interface Orderable<T> {
    fun isBetterThan(other: T): Boolean
}

@Suppress("ComplexMethod")
fun <S: Orderable<S>> List<S>.bestOrNull(): S? {
    if (this.isEmpty()) return null
    return this.reduce { champion, challenger -> if (challenger.isBetterThan(champion)) challenger else champion }
}
