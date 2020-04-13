package com.slack.api

import com.slack.api.model.block.SectionBlock

@SlackAPIBuilder
class SectionBlockBuilder(
        private val blockID: String? = null
) : TextObjectContainer, BlockElementContainer {
    private val fieldsDelegate = MultiTextObjectContainerImpl()
    private val textContentDelegate = SingleTextObjectContainerImpl()
    private val accessoryDelegate = SingleBlockElementContainerImpl()

    fun fields(buildFields: TextObjectContainer.() -> Unit) {
        fieldsDelegate.buildFields()
    }

    override fun plainText(text: String, emoji: Boolean?) = textContentDelegate.plainText(text, emoji)
    override fun markdownText(text: String, verbatim: Boolean?) = textContentDelegate.markdownText(text, verbatim)
    override fun button(actionID: String?, url: String?, value: String?, style: ButtonBuilder.ButtonStyle, buildButton: ButtonBuilder.() -> Unit) =
            accessoryDelegate.button(actionID, url, value, style, buildButton)

    fun build(): SectionBlock {
        return SectionBlock(textContentDelegate.constructedTextObject, blockID, fieldsDelegate.addedTextObjects, accessoryDelegate.constructedBlockElement)
    }
}
