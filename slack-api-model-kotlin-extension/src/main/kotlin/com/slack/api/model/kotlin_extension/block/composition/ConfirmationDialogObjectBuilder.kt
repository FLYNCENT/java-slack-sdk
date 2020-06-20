package com.slack.api.model.kotlin_extension.block.composition

import com.slack.api.model.block.composition.ConfirmationDialogObject
import com.slack.api.model.block.composition.PlainTextObject
import com.slack.api.model.kotlin_extension.block.BlockLayoutBuilder
import com.slack.api.model.kotlin_extension.block.Builder
import com.slack.api.model.kotlin_extension.block.composition.container.SingleTextObjectContainer
import com.slack.api.model.kotlin_extension.block.composition.dsl.TextObjectDsl

// same name with the object + "Builder" suffix
@BlockLayoutBuilder
class ConfirmationDialogObjectBuilder private constructor(
        private val textContainer: SingleTextObjectContainer
) : Builder<ConfirmationDialogObject>, TextObjectDsl by textContainer {

    constructor() : this(SingleTextObjectContainer())

    private var title: PlainTextObject? = null
    private var confirm: PlainTextObject? = null
    private var deny: PlainTextObject? = null

    /**
     * Fills the title field with a plain text object.
     *
     * Defines the dialog's title. Maximum length for this field is 100 characters.
     *
     * @see <a href="https://api.slack.com/reference/block-kit/composition-objects#confirm">Confirmation dialog object documentation</a>
     */
    fun title(text: String, emoji: Boolean? = null) {
        title = PlainTextObject(text, emoji)
    }

    /**
     * Fills the confirm field with a plain text object.
     *
     * The text of the button that confirms the action. Maximum length for the text in this field is 30 characters.
     *
     * @see <a href="https://api.slack.com/reference/block-kit/composition-objects#confirm">Confirmation dialog object documentation</a>
     */
    fun confirm(text: String, emoji: Boolean? = null) {
        confirm = PlainTextObject(text, emoji)
    }

    /**
     * Fills the deny field with a plain text object.
     *
     * The text of the button that cancels the action. Maximum length for the text in this field is 30 characters.
     *
     * @see <a href="https://api.slack.com/reference/block-kit/composition-objects#confirm">Confirmation dialog object documentation</a>
     */
    fun deny(text: String, emoji: Boolean? = null) {
        deny = PlainTextObject(text, emoji)
    }

    override fun build(): ConfirmationDialogObject {
        return ConfirmationDialogObject.builder()
                .title(title)
                .text(textContainer.underlying)
                .confirm(confirm)
                .deny(deny)
                .build()
    }
}