package com.nrkei.training.oo.graph

import com.nrkei.training.oo.graph.Link.Companion.totalCost

// Understands a route from one Node to another Node
abstract class Path {
    abstract fun cost(): Double
    abstract fun hopCount(): Int
    internal open fun prepend(link: Link) {}  // Ignore by default

    class ActualPath internal constructor() : Path() {
        private val links = mutableListOf<Link>()

        override fun prepend(link: Link) = links.add(0, link)

        override fun cost() = links.totalCost()

        override fun hopCount() = links.size
    }

    object None : Path() {
        override fun cost() = Double.POSITIVE_INFINITY

        override fun hopCount() = Int.MAX_VALUE
    }
}

internal typealias PathStrategy = (Path) -> Number