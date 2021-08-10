package com.nrkei.training.oo.graph

import com.nrkei.training.oo.graph.Path.ActualPath

class Node {
    private val links = mutableListOf<Link>()

    infix fun canReach(destination: Node) = path(destination, noVisitedNodes, Path::cost) is ActualPath

    infix fun hopCount(destination: Node) = path(destination, Path::hopCount).hopCount()

    infix fun cost(destination: Node) = path(destination).cost()

    infix fun path(destination: Node) = path(destination, Path::cost)

    private fun path(destination: Node, strategy: PathStrategy) =
        path(destination, noVisitedNodes, strategy)
            .also { result -> require(result is ActualPath) { "Destination is unreachable" } }

    @Suppress("ComplexMethod")
    internal fun path(destination: Node, visitedNodes: List<Node>, strategy: PathStrategy): Path {
        if (this == destination) return ActualPath()
        if (this in visitedNodes) return Path.None
        return links
            .map { link -> link.path(destination, visitedNodes + this, strategy) }
            .minByOrNull { strategy(it).toDouble() }
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
