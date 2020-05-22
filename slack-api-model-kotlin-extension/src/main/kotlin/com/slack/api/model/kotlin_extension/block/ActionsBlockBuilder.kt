package com.slack.api.model.kotlin_extension.block

import com.slack.api.model.block.ActionsBlock
import com.slack.api.model.kotlin_extension.block.container.SingleBlockIdContainer
import com.slack.api.model.kotlin_extension.block.dsl.BlockIdDsl
import com.slack.api.model.kotlin_extension.block.element.container.MultiBlockElementContainer
import com.slack.api.model.kotlin_extension.block.element.dsl.BlockElementDsl

// same name with the object + "Builder" suffix
@BlockLayoutBuilder
class ActionsBlockBuilder private constructor(
        private val blockIdContainer: SingleBlockIdContainer,
        private val elementsContainer: MultiBlockElementContainer
) : Builder<ActionsBlock>, BlockIdDsl by blockIdContainer, BlockElementDsl by elementsContainer {

    constructor() : this(SingleBlockIdContainer(), MultiBlockElementContainer())

    override fun build(): ActionsBlock {
        return ActionsBlock.builder()
                .blockId(blockIdContainer.underlying)
                .elements(elementsContainer.underlying)
                .build()
    }
}