package com.slack.api.model.kotlin_extension.block.element.container

import com.slack.api.model.block.element.BlockElement
import com.slack.api.model.kotlin_extension.block.element.ButtonElementBuilder
import com.slack.api.model.kotlin_extension.block.element.ChannelsSelectElementBuilder
import com.slack.api.model.kotlin_extension.block.element.CheckboxesElementBuilder
import com.slack.api.model.kotlin_extension.block.element.dsl.BlockElementDsl

/**
 * Supports a BlockElementContainer holding exactly one block element
 */
class SingleBlockElementContainer : BlockElementDsl {
    var underlying: BlockElement? = null

    override fun button(builder: ButtonElementBuilder.() -> Unit) {
        underlying = ButtonElementBuilder().apply(builder).build()
    }

    override fun checkboxes(builder: CheckboxesElementBuilder.() -> Unit) {
        underlying = CheckboxesElementBuilder().apply(builder).build()
    }

    override fun channelsSelect(builder: ChannelsSelectElementBuilder.() -> Unit) {
        underlying = ChannelsSelectElementBuilder().apply(builder).build()
    }
}