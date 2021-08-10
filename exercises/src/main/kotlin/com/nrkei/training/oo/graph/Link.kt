package com.nrkei.training.oo.graph

// Understands a connection from one Node to another Node
internal class Link(private val target: Node, private val cost: Double) {

    internal fun hopCount(destination: Node, visitedNodes: List<Node>) =
        target.hopCount(destination, visitedNodes) + 1

    internal fun cost(destination: Node, visitedNodes: List<Node>) =
        target.cost(destination, visitedNodes) + cost
}