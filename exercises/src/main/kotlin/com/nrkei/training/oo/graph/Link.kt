package com.nrkei.training.oo.graph

// Understands a connection from one Node to another Node
internal class Link(private val target: Node, private val cost: Double) {
    companion object {
        internal fun List<Link>.totalCost() = this.sumOf { it.cost }
    }

    internal fun path(destination: Node, visitedNodes: List<Node>, strategy: PathStrategy) =
        target.path(destination, visitedNodes, strategy).also { it.prepend(this) }

    internal fun paths(destination: Node, visitedNodes: List<Node>) =
        target.paths(destination, visitedNodes).onEach { it.prepend(this) }
}
