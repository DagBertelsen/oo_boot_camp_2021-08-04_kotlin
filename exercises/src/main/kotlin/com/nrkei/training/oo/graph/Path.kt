package com.nrkei.training.oo.graph

import com.nrkei.training.oo.graph.Link.Companion.totalCost

// Understands a route from one Node to another Node
class Path internal constructor(private val destination: Node) {
    companion object {
        internal fun List<Path>.filterBy(destination: Node) =
            this.filter { it.destination == destination }
    }
    private val links = mutableListOf<Link>()

    internal fun prepend(link: Link) = links.add(0, link)

    fun cost() = links.totalCost()

    fun hopCount() = links.size
}

internal typealias PathStrategy = (Path) -> Number
