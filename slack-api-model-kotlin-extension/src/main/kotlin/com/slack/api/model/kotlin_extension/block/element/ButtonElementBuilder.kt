package com.slack.api.model.kotlin_extension.block.element

import com.slack.api.model.block.composition.PlainTextObject
import com.slack.api.model.block.element.ButtonElement
import com.slack.api.model.kotlin_extension.block.BlockLayoutBuilder
import com.slack.api.model.kotlin_extension.block.Builder
import com.slack.api.model.kotlin_extension.block.composition.container.SingleConfirmationDialogContainer
import com.slack.api.model.kotlin_extension.block.composition.dsl.ConfirmationDialogDsl
import com.slack.api.model.kotlin_extension.block.element.container.SingleActionIdContainer
import com.slack.api.model.kotlin_extension.block.element.dsl.ActionIdDsl

// same name with the object + "Builder" suffix
@BlockLayoutBuilder
class ButtonElementBuilder private constructor(
        private val actionIdContainer: SingleActionIdContainer,
        private val confirmationDialogContainer: SingleConfirmationDialogContainer
) : Builder<ButtonElement>, ActionIdDsl by actionIdContainer, ConfirmationDialogDsl by confirmationDialogContainer {
    private var text: PlainTextObject? = null
    private var url: String? = null
    private var value: String? = null
    private var style: ButtonStyle? = null

    constructor() : this(SingleActionIdContainer(), SingleConfirmationDialogContainer())

    fun plainText(buttonText: String, emoji: Boolean? = null) {
        text = PlainTextObject(buttonText, emoji)
    }

    fun url(text: String) {
        url = text
    }

    fun value(text: String) {
        value = text
    }

    fun style(style: ButtonStyle) {
        this.style = style
    }

    override fun build(): ButtonElement {
        return ButtonElement.builder()
                .actionId(actionIdContainer.underlying)
                .url(url)
                .value(value)
                .text(text)
                .style(style?.value)
                .confirm(confirmationDialogContainer.underlying)
                .build()
    }
}