package com.slack.api.model.kotlin_extension.block.element.dsl

import com.slack.api.model.kotlin_extension.block.BlockLayoutBuilder

@BlockLayoutBuilder
interface PlaceholderDsl {
    fun placeholder(text: String, emoji: Boolean? = null)
}