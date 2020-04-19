package com.slack.api.model.kotlin_extension.block.element

import com.slack.api.model.block.composition.ConfirmationDialogObject
import com.slack.api.model.block.composition.PlainTextObject
import com.slack.api.model.block.element.ButtonElement
import com.slack.api.model.kotlin_extension.block.BlockLayoutBuilder
import com.slack.api.model.kotlin_extension.block.Builder
import com.slack.api.model.kotlin_extension.block.composition.ConfirmationDialogObjectBuilder

// same name with the object + "Builder" suffix
@BlockLayoutBuilder
class ButtonElementBuilder(
        private var actionId: String? = null,
        private var url: String? = null,
        private var value: String? = null,
        private var style: ButtonStyle? = null,
        private var confirm: ConfirmationDialogObject? = null
) : Builder<ButtonElement> {
    private var text: PlainTextObject? = null

    fun plainText(buttonText: String, emoji: Boolean? = null) {
        text = PlainTextObject(buttonText, emoji)
    }

    fun confirm(builder: ConfirmationDialogObjectBuilder.() -> Unit) {
        confirm = ConfirmationDialogObjectBuilder().apply(builder).build()
    }

    override fun build(): ButtonElement {
        return ButtonElement.builder()
                .actionId(actionId)
                .url(url)
                .value(value)
                .text(text)
                .style(style?.value)
                .confirm(confirm)
                .build()
    }
}