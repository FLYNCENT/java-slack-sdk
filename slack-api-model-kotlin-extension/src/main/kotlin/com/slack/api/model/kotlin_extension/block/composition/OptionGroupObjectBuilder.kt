package com.slack.api.model.kotlin_extension.block.composition

import com.slack.api.model.block.composition.OptionGroupObject
import com.slack.api.model.block.composition.OptionObject
import com.slack.api.model.block.composition.PlainTextObject
import com.slack.api.model.kotlin_extension.block.BlockLayoutBuilder
import com.slack.api.model.kotlin_extension.block.Builder
import com.slack.api.model.kotlin_extension.block.composition.container.MultiOptionContainer
import com.slack.api.model.kotlin_extension.block.composition.dsl.OptionObjectDsl

@BlockLayoutBuilder
class OptionGroupObjectBuilder private constructor(
        private val optionContainer: MultiOptionContainer
) : Builder<OptionGroupObject>, OptionObjectDsl by optionContainer {
    private var label: PlainTextObject? = null

    constructor() : this(MultiOptionContainer())

    /**
     * Fills the label field of the option group object with a plain text object.
     *
     * The label shown above this group of options. Maximum length for the text in this field is 75 characters.
     *
     * @see <a href="https://api.slack.com/reference/block-kit/composition-objects#option_group">Option group object documentation</a>
     */
    fun label(text: String, emoji: Boolean? = null) {
        label = PlainTextObject(text, emoji)
    }

    fun options(options: List<OptionObject>) {
        this.optionContainer.underlying.addAll(options)
    }

    override fun build(): OptionGroupObject {
        return OptionGroupObject.builder()
                .label(label)
                .options(optionContainer.underlying)
                .build()
    }
}