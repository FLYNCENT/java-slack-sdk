package com.slack.api.model.block.composition

import com.slack.api.SlackAPIBuilder

@SlackAPIBuilder
interface TextObjectContainer {
    fun plainText(text: String, emoji: Boolean? = null)
    fun markdownText(text: String, verbatim: Boolean? = null)
}

class SingleTextObjectContainerImpl : TextObjectContainer {
    var constructedTextObject: TextObject? = null

    override fun plainText(text: String, emoji: Boolean?) {
        constructedTextObject = PlainTextObject(text, emoji)
    }

    override fun markdownText(text: String, verbatim: Boolean?) {
        constructedTextObject = MarkdownTextObject(text, verbatim)
    }
}

class MultiTextObjectContainerImpl : TextObjectContainer {
    val addedTextObjects = mutableListOf<TextObject>()

    override fun plainText(text: String, emoji: Boolean?) {
        addedTextObjects += PlainTextObject(text, emoji)
    }

    override fun markdownText(text: String, verbatim: Boolean?) {
        addedTextObjects += MarkdownTextObject(text, verbatim)
    }
}