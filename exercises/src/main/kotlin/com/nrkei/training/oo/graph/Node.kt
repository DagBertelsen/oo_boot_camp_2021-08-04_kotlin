package com.nrkei.training.oo.graph

import com.nrkei.training.oo.graph.Link.Companion.FEWEST_HOPS
import com.nrkei.training.oo.graph.Link.Companion.LEAST_COST
import com.nrkei.training.oo.graph.Path.ActualPath

class Node {
    companion object {
        private const val UNREACHABLE = Double.POSITIVE_INFINITY
    }

    private val links = mutableListOf<Link>()

    infix fun canReach(destination: Node) = path(destination, noVisitedNodes) is ActualPath

    infix fun hopCount(destination: Node) = cost(destination,FEWEST_HOPS).toInt()

    infix fun cost(destination: Node) = path(destination).cost()

    private fun cost(destination: Node, strategy: CostStrategy) =
        cost(destination, noVisitedNodes, strategy).also { result ->
            require(result != UNREACHABLE) { "Destination is unreachable" }
        }

    internal fun cost(destination: Node, visitedNodes: List<Node>, strategy: CostStrategy): Double {
        if (this == destination) return 0.0
        if (this in visitedNodes || links.isEmpty()) return UNREACHABLE
        return links.minOf { link -> link.cost(destination, visitedNodes + this, strategy) }
    }

    infix fun path(destination: Node) = path(destination, noVisitedNodes).also { result ->
        require(result is ActualPath) { "Destination is unreachable" }
    }

    internal fun path(destination: Node, visitedNodes: List<Node>): Path {
        if (this == destination) return ActualPath()
        if (this in visitedNodes) return Path.None
        return links
            .map { link -> link.path(destination, visitedNodes + this) }
            .minByOrNull { it.cost() }
            ?: Path.None
    }

    private val noVisitedNodes = emptyList<Node>()

    infix fun cost(amount: Number) = LinkBuilder(amount.toDouble())

    inner class LinkBuilder internal constructor(private val cost: Double) {
        infix fun to(neighbor: Node) = neighbor.also {
            links.add(Link(neighbor, cost))
        }
    }
}
