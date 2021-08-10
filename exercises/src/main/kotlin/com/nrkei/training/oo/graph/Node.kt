package com.nrkei.training.oo.graph

class Node {
    companion object {
        private const val UNREACHABLE = -1
    }
    private val neighbors = mutableListOf<Node>()

    infix fun to(neighbor: Node) = neighbor.also { neighbors.add(neighbor) }

    infix fun canReach(destination: Node) = hopCount(destination, noVisitedNodes) != UNREACHABLE

    infix fun hopCount(destination: Node) = hopCount(destination, noVisitedNodes).also { result ->
        require(result != UNREACHABLE) { "Destination is unreachable" }
    }

    private fun hopCount(destination: Node, visitedNodes: List<Node>): Int {
        if (this == destination) return 0
        if (this in visitedNodes) return UNREACHABLE
        var champion = UNREACHABLE
        neighbors.forEach { neighbor ->
            neighbor.hopCount(destination, visitedNodes + this).also { challenger ->
                if(challenger != UNREACHABLE && (champion == UNREACHABLE || challenger + 1 < champion)) champion = challenger + 1
            }
        }
        return champion
    }

    private val noVisitedNodes = emptyList<Node>()
}
