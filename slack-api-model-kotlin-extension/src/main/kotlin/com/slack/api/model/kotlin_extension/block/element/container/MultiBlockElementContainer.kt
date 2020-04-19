package com.slack.api.model.kotlin_extension.block.element.container

import com.slack.api.model.block.element.BlockElement
import com.slack.api.model.kotlin_extension.block.element.ButtonElementBuilder
import com.slack.api.model.kotlin_extension.block.element.ButtonStyle
import com.slack.api.model.kotlin_extension.block.element.ChannelsSelectElementBuilder
import com.slack.api.model.kotlin_extension.block.element.CheckboxesElementBuilder
import com.slack.api.model.kotlin_extension.block.element.dsl.BlockElementDsl

/**
 * Supports a BlockElementContainer that can hold one to many block elements
 */
class MultiBlockElementContainer : BlockElementDsl {
    val underlying = mutableListOf<BlockElement>()

    override fun button(
            actionId: String?,
            url: String?,
            value: String?,
            style: ButtonStyle?,
            builder: ButtonElementBuilder.() -> Unit) {
        underlying += ButtonElementBuilder(
                actionId = actionId,
                url = url,
                value = value,
                style = style
        ).apply(builder).build()
    }

    override fun checkboxes(
            actionId: String?,
            builder: CheckboxesElementBuilder.() -> Unit) {
        underlying += CheckboxesElementBuilder(
                actionId = actionId
        ).apply(builder).build()
    }

    override fun channelsSelect(
            actionId: String?,
            initialChannel: String?,
            responseUrlEnabled: Boolean?,
            builder: ChannelsSelectElementBuilder.() -> Unit) {
        underlying += ChannelsSelectElementBuilder(
                actionId = actionId,
                initialChannel = initialChannel,
                responseUrlEnabled = responseUrlEnabled
        ).apply(builder).build()
    }
}