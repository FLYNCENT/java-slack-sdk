package com.slack.api.model.kotlin_extension.block.element

import com.slack.api.model.block.composition.ConfirmationDialogObject
import com.slack.api.model.block.composition.PlainTextObject
import com.slack.api.model.block.element.ChannelsSelectElement
import com.slack.api.model.kotlin_extension.block.BlockLayoutBuilder
import com.slack.api.model.kotlin_extension.block.Builder
import com.slack.api.model.kotlin_extension.block.composition.ConfirmationDialogObjectBuilder

// same name with the object + "Builder" suffix
@BlockLayoutBuilder
class ChannelsSelectElementBuilder(
        private var actionId: String?,
        private var initialChannel: String?,
        private var responseUrlEnabled: Boolean?,
        private var placeholder: PlainTextObject? = null,
        private var confirm: ConfirmationDialogObject? = null
) : Builder<ChannelsSelectElement> {

    fun placeholder(text: String, emoji: Boolean? = null) {
        placeholder = PlainTextObject(text, emoji)
    }

    fun confirm(builder: ConfirmationDialogObjectBuilder.() -> Unit) {
        confirm = ConfirmationDialogObjectBuilder().apply(builder).build()
    }

    override fun build(): ChannelsSelectElement {
        return ChannelsSelectElement.builder()
                .actionId(actionId)
                .placeholder(placeholder)
                .initialChannel(initialChannel)
                .confirm(confirm)
                .responseUrlEnabled(responseUrlEnabled)
                .build()
    }
}