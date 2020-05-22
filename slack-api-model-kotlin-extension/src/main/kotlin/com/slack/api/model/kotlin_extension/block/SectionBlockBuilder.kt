package com.slack.api.model.kotlin_extension.block

import com.slack.api.model.block.SectionBlock
import com.slack.api.model.block.composition.TextObject
import com.slack.api.model.kotlin_extension.block.composition.container.MultiTextObjectContainer
import com.slack.api.model.kotlin_extension.block.composition.container.SingleTextObjectContainer
import com.slack.api.model.kotlin_extension.block.composition.dsl.TextObjectDsl
import com.slack.api.model.kotlin_extension.block.container.SingleBlockIdContainer
import com.slack.api.model.kotlin_extension.block.dsl.BlockIdDsl
import com.slack.api.model.kotlin_extension.block.element.container.SingleBlockElementContainer
import com.slack.api.model.kotlin_extension.block.element.dsl.BlockElementDsl

// same name with the object + "Builder" suffix
@BlockLayoutBuilder
class SectionBlockBuilder private constructor(
        private val textContainer: SingleTextObjectContainer,
        private val accessoryContainer: SingleBlockElementContainer,
        private val blockIdContainer: SingleBlockIdContainer
) : Builder<SectionBlock>, TextObjectDsl by textContainer, BlockElementDsl by accessoryContainer, BlockIdDsl by blockIdContainer {

    constructor() : this(SingleTextObjectContainer(), SingleBlockElementContainer(), SingleBlockIdContainer())

    // Need to separate "fields" and "fieldsContainer" because the delegate makes the list non-null by default
    private var fields: MutableList<TextObject>? = null
    private val fieldsContainer = MultiTextObjectContainer()

    fun fields(builder: TextObjectDsl.() -> Unit) {
        fields = fieldsContainer.apply(builder).underlying
    }

    override fun build(): SectionBlock {
        return SectionBlock.builder()
                .blockId(blockIdContainer.underlying)
                .fields(fields)
                .accessory(accessoryContainer.underlying)
                .text(textContainer.underlying)
                .build()
    }
}
