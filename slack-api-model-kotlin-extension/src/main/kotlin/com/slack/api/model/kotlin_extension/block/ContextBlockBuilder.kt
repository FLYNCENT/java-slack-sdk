package com.slack.api.model.kotlin_extension.block

import com.slack.api.model.block.ContextBlock
import com.slack.api.model.kotlin_extension.block.container.MultiContextBlockElementContainer
import com.slack.api.model.kotlin_extension.block.container.SingleBlockIdContainer
import com.slack.api.model.kotlin_extension.block.dsl.BlockIdDsl
import com.slack.api.model.kotlin_extension.block.dsl.ContextBlockElementDsl

@BlockLayoutBuilder
class ContextBlockBuilder private constructor(
        private val blockIdContainer: SingleBlockIdContainer,
        private val elementsContainer: MultiContextBlockElementContainer
) : Builder<ContextBlock>, BlockIdDsl by blockIdContainer, ContextBlockElementDsl by elementsContainer {

    constructor() : this(SingleBlockIdContainer(), MultiContextBlockElementContainer())

    override fun build(): ContextBlock {
        return ContextBlock.builder()
                .blockId(blockIdContainer.underlying)
                .elements(elementsContainer.underlying)
                .build()
    }
}