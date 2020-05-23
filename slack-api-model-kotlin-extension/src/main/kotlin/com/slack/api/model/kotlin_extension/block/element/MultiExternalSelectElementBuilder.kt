package com.slack.api.model.kotlin_extension.block.element

import com.slack.api.model.block.composition.OptionObject
import com.slack.api.model.block.element.MultiExternalSelectElement
import com.slack.api.model.kotlin_extension.block.BlockLayoutBuilder
import com.slack.api.model.kotlin_extension.block.Builder
import com.slack.api.model.kotlin_extension.block.composition.container.MultiOptionContainer
import com.slack.api.model.kotlin_extension.block.composition.container.SingleConfirmationDialogContainer
import com.slack.api.model.kotlin_extension.block.composition.dsl.ConfirmationDialogDsl
import com.slack.api.model.kotlin_extension.block.composition.dsl.OptionObjectDsl
import com.slack.api.model.kotlin_extension.block.element.container.SingleActionIdContainer
import com.slack.api.model.kotlin_extension.block.element.container.SinglePlaceholderContainer
import com.slack.api.model.kotlin_extension.block.element.dsl.ActionIdDsl
import com.slack.api.model.kotlin_extension.block.element.dsl.PlaceholderDsl

@BlockLayoutBuilder
class MultiExternalSelectElementBuilder private constructor(
        private val placeholderContainer: SinglePlaceholderContainer,
        private val actionIdContainer: SingleActionIdContainer,
        private val confirmationDialogContainer: SingleConfirmationDialogContainer
) : Builder<MultiExternalSelectElement>,
        PlaceholderDsl by placeholderContainer,
        ActionIdDsl by actionIdContainer,
        ConfirmationDialogDsl by confirmationDialogContainer {
    private var initialOptions: List<OptionObject>? = null
    private var minQueryLength: Int? = null
    private var maxSelectedItems: Int? = null

    constructor() : this(SinglePlaceholderContainer(), SingleActionIdContainer(), SingleConfirmationDialogContainer())

    fun initialOptions(builder: OptionObjectDsl.() -> Unit) {
        initialOptions = MultiOptionContainer().apply(builder).underlying
    }

    fun minQueryLength(length: Int) {
        minQueryLength = length
    }

    fun maxSelectedItems(max: Int) {
        maxSelectedItems = max
    }

    override fun build(): MultiExternalSelectElement {
        return MultiExternalSelectElement.builder()
                .placeholder(placeholderContainer.underlying)
                .actionId(actionIdContainer.underlying)
                .initialOptions(initialOptions)
                .minQueryLength(minQueryLength)
                .maxSelectedItems(maxSelectedItems)
                .confirm(confirmationDialogContainer.underlying)
                .build()
    }
}