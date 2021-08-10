package com.nrkei.training.oo.graph

class Node {
    companion object {
        private const val UNREACHABLE = -1
    }
    private val neighbors = mutableListOf<Node>()

    infix fun to(neighbor: Node) = neighbor.also { neighbors.add(neighbor) }

    infix fun canReach(destination: Node) = canReach(destination, noVisitedNodes)

    infix fun hopCount(destination: Node) = hopCount(destination, noVisitedNodes).also { result ->
        require(result != UNREACHABLE) { "Destination is unreachable" }
    }

    private fun canReach(destination: Node, visitedNodes: MutableList<Node>): Boolean {
        if (this == destination) return true
        if (this in visitedNodes) return false
        visitedNodes.add(this)
        neighbors.forEach { neighbor ->
            if (neighbor.canReach(destination, visitedNodes)) return true
        }
        return false
    }

    private fun hopCount(destination: Node, visitedNodes: MutableList<Node>): Int {
        if (this == destination) return 0
        if (this in visitedNodes) return UNREACHABLE
        visitedNodes.add(this)
        var champion = UNREACHABLE
        neighbors.forEach { neighbor ->
            neighbor.hopCount(destination, visitedNodes).also { challenger ->
                if(challenger != UNREACHABLE && (champion == UNREACHABLE || challenger + 1 < champion)) champion = challenger + 1
            }
        }
        return champion
    }

    private val noVisitedNodes get() = mutableListOf<Node>()
}
