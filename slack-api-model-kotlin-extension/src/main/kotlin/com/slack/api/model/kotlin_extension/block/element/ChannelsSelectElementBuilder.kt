package com.slack.api.model.kotlin_extension.block.element

import com.slack.api.model.block.composition.PlainTextObject
import com.slack.api.model.block.element.ChannelsSelectElement
import com.slack.api.model.kotlin_extension.block.BlockLayoutBuilder
import com.slack.api.model.kotlin_extension.block.Builder
import com.slack.api.model.kotlin_extension.block.composition.container.SingleConfirmationDialogContainer
import com.slack.api.model.kotlin_extension.block.composition.dsl.ConfirmationDialogDsl
import com.slack.api.model.kotlin_extension.block.element.container.SingleActionIdContainer
import com.slack.api.model.kotlin_extension.block.element.dsl.ActionIdDsl

// same name with the object + "Builder" suffix
@BlockLayoutBuilder
class ChannelsSelectElementBuilder private constructor(
        private val actionIdContainer: SingleActionIdContainer,
        private val confirmationDialogContainer: SingleConfirmationDialogContainer
) : Builder<ChannelsSelectElement>, ActionIdDsl by actionIdContainer, ConfirmationDialogDsl by confirmationDialogContainer {
    private var initialChannel: String? = null
    private var responseUrlEnabled: Boolean? = null
    private var placeholder: PlainTextObject? = null

    constructor() : this(SingleActionIdContainer(), SingleConfirmationDialogContainer())

    fun initialChannel(channel: String) {
        initialChannel = channel
    }

    fun responseUrlEnabled(enabled: Boolean) {
        responseUrlEnabled = enabled
    }

    fun placeholder(text: String, emoji: Boolean? = null) {
        placeholder = PlainTextObject(text, emoji)
    }

    override fun build(): ChannelsSelectElement {
        return ChannelsSelectElement.builder()
                .actionId(actionIdContainer.underlying)
                .placeholder(placeholder)
                .initialChannel(initialChannel)
                .confirm(confirmationDialogContainer.underlying)
                .responseUrlEnabled(responseUrlEnabled)
                .build()
    }
}