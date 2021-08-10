package com.nrkei.training.oo.graph

import com.nrkei.training.oo.graph.Link.Companion.FEWEST_HOPS
import com.nrkei.training.oo.graph.Link.Companion.LEAST_COST

class Node {
    companion object {
        private const val UNREACHABLE = Double.POSITIVE_INFINITY
    }

    private val links = mutableListOf<Link>()

    infix fun canReach(destination: Node) = cost(destination, noVisitedNodes, LEAST_COST) != UNREACHABLE

    infix fun hopCount(destination: Node) = cost(destination,FEWEST_HOPS).toInt()

    infix fun cost(destination: Node) = cost(destination,LEAST_COST)

    private fun cost(destination: Node, strategy: CostStrategy) =
        cost(destination, noVisitedNodes, strategy).also { result ->
            require(result != UNREACHABLE) { "Destination is unreachable" }
        }

    internal fun cost(destination: Node, visitedNodes: List<Node>, strategy: CostStrategy): Double {
        if (this == destination) return 0.0
        if (this in visitedNodes || links.isEmpty()) return UNREACHABLE
        return links.minOf { link -> link.cost(destination, visitedNodes + this, strategy) }
    }

    infix fun path(destination: Node) =
        path(destination, noVisitedNodes) ?: throw IllegalArgumentException("Destination is unreachable")

    internal fun path(destination: Node, visitedNodes: List<Node>): Path? {
        if (this == destination) return Path()
        if (this in visitedNodes) return null
        return links
            .map { link -> link.path(destination, visitedNodes + this) }
            .minByOrNull { it?.cost() ?: Double.POSITIVE_INFINITY }
    }

    private val noVisitedNodes = emptyList<Node>()

    infix fun cost(amount: Number) = LinkBuilder(amount.toDouble())

    inner class LinkBuilder internal constructor(private val cost: Double) {
        infix fun to(neighbor: Node) = neighbor.also {
            links.add(Link(neighbor, cost))
        }
    }
}
