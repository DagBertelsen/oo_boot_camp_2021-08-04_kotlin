package com.nrkei.training.oo.orderable

interface Orderable<T> {
    fun isBetterThan(other: T): Boolean
}

fun <S: Orderable<S>> List<S>.bestOrNull(): S? {
    var champion: S? = null
    this.forEach { challenger ->
        if (champion == null || challenger.isBetterThan(champion!!)) champion = challenger
    }
    return champion
}