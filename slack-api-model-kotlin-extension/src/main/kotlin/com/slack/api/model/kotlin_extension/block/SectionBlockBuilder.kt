package com.slack.api.model.kotlin_extension.block

import com.slack.api.model.block.SectionBlock
import com.slack.api.model.block.composition.TextObject
import com.slack.api.model.kotlin_extension.block.composition.container.MultiTextObjectContainer
import com.slack.api.model.kotlin_extension.block.composition.container.SingleTextObjectContainer
import com.slack.api.model.kotlin_extension.block.composition.dsl.TextObjectDsl
import com.slack.api.model.kotlin_extension.block.element.container.SingleBlockElementContainer
import com.slack.api.model.kotlin_extension.block.element.dsl.BlockElementDsl

// same name with the object + "Builder" suffix
@BlockLayoutBuilder
class SectionBlockBuilder private constructor(
        private var blockId: String?,
        private val textContainer: SingleTextObjectContainer,
        private val accessoryContainer: SingleBlockElementContainer
) : Builder<SectionBlock>, TextObjectDsl by textContainer, BlockElementDsl by accessoryContainer {

    constructor(blockId: String?) : this(blockId, SingleTextObjectContainer(), SingleBlockElementContainer())
    // Need to separate "fields" and "fieldsContainer" because the delegate makes the list non-null by default
    private var fields: MutableList<TextObject>? = null
    private val fieldsContainer = MultiTextObjectContainer()

    fun fields(builder: TextObjectDsl.() -> Unit) {
        fields = fieldsContainer.apply(builder).underlying
    }

    override fun build(): SectionBlock {
        return SectionBlock.builder()
                .blockId(blockId)
                .fields(fields)
                .accessory(accessoryContainer.underlying)
                .text(textContainer.underlying)
                .build()
    }
}
