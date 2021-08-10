package com.nrkei.training.oo.graph

// Understands a connection from one Node to another Node
internal class Link(private val target: Node, private val cost: Double) {
    companion object {
        internal fun List<Link>.totalCost() = this.sumOf { it.cost }
    }

    internal fun paths(visitedNodes: List<Node>) =
        target.paths(visitedNodes).onEach { it.prepend(this) }
}
