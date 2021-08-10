package com.nrkei.training.oo.graph

// Understands a connection from one Node to another Node
internal class Link(private val target: Node, private val cost: Double) {
    companion object {
        internal val LEAST_COST = { cost: Double -> cost }
        internal val FEWEST_HOPS = { _: Double -> 1.0 }

        internal fun List<Link>.totalCost() = this.sumOf { it.cost }
    }

    internal fun cost(destination: Node, visitedNodes: List<Node>, strategy: CostStrategy) =
        target.cost(destination, visitedNodes, strategy) + strategy(cost)

    internal fun path(destination: Node, visitedNodes: List<Node>) =
        target.path(destination, visitedNodes)?.also { it.prepend(this) }
}

internal typealias CostStrategy = (Double) -> Double