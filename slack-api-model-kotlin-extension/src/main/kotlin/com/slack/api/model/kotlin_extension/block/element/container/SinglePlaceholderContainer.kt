package com.slack.api.model.kotlin_extension.block.element.container

import com.slack.api.model.block.composition.PlainTextObject
import com.slack.api.model.kotlin_extension.block.element.dsl.PlaceholderDsl

class SinglePlaceholderContainer : PlaceholderDsl {
    var underlying: PlainTextObject? = null

    override fun placeholder(text: String, emoji: Boolean?) {
        underlying = PlainTextObject(text, emoji)
    }
}