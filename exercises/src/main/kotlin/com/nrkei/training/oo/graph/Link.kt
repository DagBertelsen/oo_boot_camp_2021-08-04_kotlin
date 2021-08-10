package com.nrkei.training.oo.graph

// Understands a connection from one Node to another Node
internal class Link(private val target: Node, private val cost: Double) {
    companion object {
        internal val LEAST_COST = { cost: Double -> cost }
    }

    internal fun hopCount(destination: Node, visitedNodes: List<Node>) =
        target.hopCount(destination, visitedNodes) + 1

    internal fun cost(destination: Node, visitedNodes: List<Node>, strategy: CostStrategy) =
        target.cost(destination, visitedNodes, strategy) + strategy(cost)
}

internal typealias CostStrategy = (Double) -> Double