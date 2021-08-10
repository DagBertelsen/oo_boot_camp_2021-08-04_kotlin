package com.nrkei.training.oo.graph

class Node {
    private val links = mutableListOf<Link>()

    infix fun canReach(destination: Node) = paths(destination).isNotEmpty()

    infix fun hopCount(destination: Node) = path(destination, Path::hopCount).hopCount()

    infix fun cost(destination: Node) = path(destination).cost()

    infix fun path(destination: Node) = path(destination, Path::cost)

    infix fun paths(destination: Node) = paths(destination, noVisitedNodes)

    fun paths() = paths(noVisitedNodes)

    @Suppress("ComplexMethod")
    internal fun paths(destination: Node, visitedNodes: List<Node>): List<Path> {
        if (this == destination) return listOf(Path())
        if (this in visitedNodes) return emptyList()
        return links.flatMap { link -> link.paths(destination, visitedNodes + this) }
    }

    internal fun paths(visitedNodes: List<Node>): List<Path> {
        if (this in visitedNodes) return emptyList()
        return (links.flatMap { link -> link.paths(visitedNodes + this) }) + Path()
    }

    private fun path(destination: Node, strategy: PathStrategy) = paths(destination)
        .minByOrNull { strategy(it).toDouble() }
        ?: throw IllegalArgumentException("Destination is unreachable")

    private val noVisitedNodes = emptyList<Node>()

    infix fun cost(amount: Number) = LinkBuilder(amount.toDouble())

    inner class LinkBuilder internal constructor(private val cost: Double) {
        infix fun to(neighbor: Node) = neighbor.also {
            links.add(Link(neighbor, cost))
        }
    }
}
