package com.slack.api.model.kotlin_extension.block.element

import com.slack.api.model.block.composition.OptionObject
import com.slack.api.model.block.composition.PlainTextObject
import com.slack.api.model.kotlin_extension.block.BlockLayoutBuilder
import com.slack.api.model.kotlin_extension.block.Builder
import com.slack.api.model.kotlin_extension.block.composition.container.SingleTextObjectContainer
import com.slack.api.model.kotlin_extension.block.composition.dsl.TextObjectDsl

// same name with the object + "Builder" suffix
@BlockLayoutBuilder
class OptionObjectBuilder private constructor(
        private var value: String?,
        private var url: String?,
        private val textContainer: SingleTextObjectContainer
) : Builder<OptionObject>, TextObjectDsl by textContainer {

    constructor(value: String?, url: String?) : this(value, url, SingleTextObjectContainer())

    private var description: PlainTextObject? = null

    fun description(text: String, emoji: Boolean? = null) {
        description = PlainTextObject(text, emoji)
    }

    override fun build(): OptionObject {
        return OptionObject.builder()
                .description(description)
                .text(textContainer.underlying)
                .value(value)
                .url(url)
                .build()
    }
}