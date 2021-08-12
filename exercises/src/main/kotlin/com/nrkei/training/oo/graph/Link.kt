package com.nrkei.training.oo.graph

import com.nrkei.training.oo.quantity.RatioQuantity
import com.nrkei.training.oo.quantity.SimpleRatioQuantity
import com.nrkei.training.oo.quantity.Unit.Companion.universal_zero

// Understands a connection from one Node to another Node
internal class Link(private val target: Node, private val cost: SimpleRatioQuantity) {
    companion object {
        internal fun List<Link>.totalCost() =
            this.fold(universal_zero) { total: RatioQuantity, link -> total + link.cost }
    }

    internal fun paths(visitedNodes: List<Node>) =
        target.paths(visitedNodes).onEach { it.prepend(this) }
}
