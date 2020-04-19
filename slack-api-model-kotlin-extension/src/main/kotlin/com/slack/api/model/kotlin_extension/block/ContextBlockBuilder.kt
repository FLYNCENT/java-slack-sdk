package com.slack.api.model.kotlin_extension.block

import com.slack.api.model.block.ContextBlock
import com.slack.api.model.kotlin_extension.block.container.MultiContextBlockElementContainer
import com.slack.api.model.kotlin_extension.block.dsl.ContextBlockElementDsl

@BlockLayoutBuilder
class ContextBlockBuilder private constructor(
        private var blockID: String?,
        private val elementsContainer: MultiContextBlockElementContainer
) : Builder<ContextBlock>, ContextBlockElementDsl by elementsContainer {

    constructor(blockID: String?) : this(blockID, MultiContextBlockElementContainer())

    override fun build(): ContextBlock {
        return ContextBlock.builder()
                .blockId(blockID)
                .elements(elementsContainer.underlying)
                .build()
    }
}