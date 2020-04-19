package com.slack.api.model.kotlin_extension.block

import com.slack.api.model.block.ActionsBlock
import com.slack.api.model.kotlin_extension.block.element.container.MultiBlockElementContainer
import com.slack.api.model.kotlin_extension.block.element.dsl.BlockElementDsl

// same name with the object + "Builder" suffix
@BlockLayoutBuilder
class ActionsBlockBuilder private constructor(
        private var blockId: String?,
        private val elementsContainer: MultiBlockElementContainer
) : Builder<ActionsBlock>, BlockElementDsl by elementsContainer {

    constructor(blockId: String?) : this(blockId, MultiBlockElementContainer())

    override fun build(): ActionsBlock {
        return ActionsBlock.builder()
                .blockId(blockId)
                .elements(this.elementsContainer.underlying)
                .build()
    }
}