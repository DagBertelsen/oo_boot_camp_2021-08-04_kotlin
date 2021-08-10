package com.nrkei.training.oo.graph

class Node {
    companion object {
        private const val UNREACHABLE = Double.POSITIVE_INFINITY
    }
    private val neighbors = mutableListOf<Node>()

    infix fun to(neighbor: Node) = neighbor.also { neighbors.add(neighbor) }

    infix fun canReach(destination: Node) = hopCount(destination, noVisitedNodes) != UNREACHABLE

    infix fun hopCount(destination: Node) = hopCount(destination, noVisitedNodes).also { result ->
        require(result != UNREACHABLE) { "Destination is unreachable" }
    }.toInt()

    private fun hopCount(destination: Node, visitedNodes: List<Node>): Double {
        if (this == destination) return 0.0
        if (this in visitedNodes || neighbors.isEmpty()) return UNREACHABLE
        return neighbors.minOf { neighbor -> neighbor.hopCount(destination, visitedNodes + this) + 1}
    }

    private val noVisitedNodes = emptyList<Node>()
}
