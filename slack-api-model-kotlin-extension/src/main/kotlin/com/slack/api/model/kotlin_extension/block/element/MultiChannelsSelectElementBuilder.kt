package com.slack.api.model.kotlin_extension.block.element

import com.slack.api.model.block.element.MultiChannelsSelectElement
import com.slack.api.model.kotlin_extension.block.BlockLayoutBuilder
import com.slack.api.model.kotlin_extension.block.Builder
import com.slack.api.model.kotlin_extension.block.composition.container.SingleConfirmationDialogContainer
import com.slack.api.model.kotlin_extension.block.composition.dsl.ConfirmationDialogDsl
import com.slack.api.model.kotlin_extension.block.element.container.SingleActionIdContainer
import com.slack.api.model.kotlin_extension.block.element.container.SinglePlaceholderContainer
import com.slack.api.model.kotlin_extension.block.element.dsl.ActionIdDsl
import com.slack.api.model.kotlin_extension.block.element.dsl.PlaceholderDsl

@BlockLayoutBuilder
class MultiChannelsSelectElementBuilder private constructor(
        private val actionIdContainer: SingleActionIdContainer,
        private val confirmationDialogContainer: SingleConfirmationDialogContainer,
        private val placeholderContainer: SinglePlaceholderContainer
) : Builder<MultiChannelsSelectElement>,
        ActionIdDsl by actionIdContainer,
        ConfirmationDialogDsl by confirmationDialogContainer,
        PlaceholderDsl by placeholderContainer {
    private var initialChannels: List<String>? = null
    private var maxSelectedItems: Int? = null

    constructor() : this(SingleActionIdContainer(), SingleConfirmationDialogContainer(), SinglePlaceholderContainer())

    fun initialChannels(vararg channels: String) {
        initialChannels = channels.toList()
    }

    fun maxSelectedItems(maxItems: Int) {
        maxSelectedItems = maxItems
    }

    override fun build(): MultiChannelsSelectElement {
        return MultiChannelsSelectElement.builder()
                .placeholder(placeholderContainer.underlying)
                .actionId(actionIdContainer.underlying)
                .initialChannels(initialChannels)
                .confirm(confirmationDialogContainer.underlying)
                .maxSelectedItems(maxSelectedItems)
                .build()
    }
}