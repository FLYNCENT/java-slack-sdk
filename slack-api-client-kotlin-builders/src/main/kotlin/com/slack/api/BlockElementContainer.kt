package com.slack.api

import com.slack.api.model.block.element.BlockElement

@SlackAPIBuilder
interface BlockElementContainer {
    fun button(
            actionID: String? = null,
            url: String? = null,
            value: String? = null,
            style: ButtonBuilder.ButtonStyle = ButtonBuilder.ButtonStyle.NO_STYLINGS,
            buildButton: ButtonBuilder.() -> Unit
    )
}

class SingleBlockElementContainerImpl : BlockElementContainer {
    var constructedBlockElement: BlockElement? = null

    override fun button(actionID: String?, url: String?, value: String?, style: ButtonBuilder.ButtonStyle, buildButton: ButtonBuilder.() -> Unit) {
        constructedBlockElement = ButtonBuilder(actionID, url, value, style).apply(buildButton).build()
    }
}