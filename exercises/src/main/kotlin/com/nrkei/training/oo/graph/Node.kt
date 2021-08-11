package com.nrkei.training.oo.graph

import com.nrkei.training.oo.graph.Path.Companion.filterBy
import com.nrkei.training.oo.quantity.RatioQuantity
import com.nrkei.training.oo.quantity.Unit.Companion.units

class Node {
    private val links = mutableListOf<Link>()

    infix fun canReach(destination: Node) = paths(destination).isNotEmpty()

    infix fun hopCount(destination: Node) = path(destination, Path::hopCount).hopCount()

    infix fun cost(destination: Node) = path(destination).cost()

    infix fun path(destination: Node) = path(destination, Path::cost)

    infix fun paths(destination: Node) = paths().filterBy(destination)

    fun paths() = paths(noVisitedNodes)

    internal fun paths(visitedNodes: List<Node>): List<Path> {
        if (this in visitedNodes) return emptyList()
        return (links.flatMap { link -> link.paths(visitedNodes + this) }) + Path(this)
    }

    private fun path(destination: Node, strategy: PathStrategy) = paths(destination)
        .minByOrNull { strategy(it) }
        ?: throw IllegalArgumentException("Destination is unreachable")

    private val noVisitedNodes = emptyList<Node>()

    infix fun cost(amount: Number) = LinkBuilder(amount.units)

    infix fun cost(amount: RatioQuantity) = LinkBuilder(amount)

    inner class LinkBuilder internal constructor(private val cost: RatioQuantity) {
        infix fun to(neighbor: Node) = neighbor.also {
            links.add(Link(neighbor, cost))
        }
    }
}
