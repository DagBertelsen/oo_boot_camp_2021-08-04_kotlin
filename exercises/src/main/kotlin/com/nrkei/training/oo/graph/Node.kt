package com.nrkei.training.oo.graph

class Node {
    private val neighbors = mutableListOf<Node>()

    infix fun to(neighbor: Node) = neighbor.also { neighbors.add(neighbor) }

    infix fun canReach(destination: Node) = canReach(destination, noVisitedNodes)

    private fun canReach(destination: Node, visitedNodes: MutableList<Node>): Boolean {
        if (this == destination) return true
        if (this in visitedNodes) return false
        visitedNodes.add(this)
        neighbors.forEach { neighbor ->
            if (neighbor.canReach(destination, visitedNodes)) return true
        }
        return false
    }

    private val noVisitedNodes get() = mutableListOf<Node>()
}
