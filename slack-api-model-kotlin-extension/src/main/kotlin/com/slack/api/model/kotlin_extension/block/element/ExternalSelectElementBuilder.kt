package com.slack.api.model.kotlin_extension.block.element

import com.slack.api.model.block.composition.OptionObject
import com.slack.api.model.block.element.ExternalSelectElement
import com.slack.api.model.kotlin_extension.block.BlockLayoutBuilder
import com.slack.api.model.kotlin_extension.block.Builder
import com.slack.api.model.kotlin_extension.block.composition.OptionObjectBuilder
import com.slack.api.model.kotlin_extension.block.composition.container.SingleConfirmationDialogContainer
import com.slack.api.model.kotlin_extension.block.composition.dsl.ConfirmationDialogDsl
import com.slack.api.model.kotlin_extension.block.element.container.SingleActionIdContainer
import com.slack.api.model.kotlin_extension.block.element.container.SinglePlaceholderContainer
import com.slack.api.model.kotlin_extension.block.element.dsl.ActionIdDsl
import com.slack.api.model.kotlin_extension.block.element.dsl.PlaceholderDsl

@BlockLayoutBuilder
class ExternalSelectElementBuilder private constructor(
        private val actionIdContainer: SingleActionIdContainer,
        private val confirmationDialogContainer: SingleConfirmationDialogContainer,
        private val placeholderContainer: SinglePlaceholderContainer
) : Builder<ExternalSelectElement>,
        ActionIdDsl by actionIdContainer,
        ConfirmationDialogDsl by confirmationDialogContainer,
        PlaceholderDsl by placeholderContainer {
    private var initialOption: OptionObject? = null
    private var minQueryLength: Int? = null

    constructor() : this(SingleActionIdContainer(), SingleConfirmationDialogContainer(), SinglePlaceholderContainer())

    fun initialOption(builder: OptionObjectBuilder.() -> Unit) {
        initialOption = OptionObjectBuilder().apply(builder).build()
    }

    fun minQueryLength(length: Int) {
        minQueryLength = length
    }

    override fun build(): ExternalSelectElement {
        return ExternalSelectElement.builder()
                .placeholder(placeholderContainer.underlying)
                .actionId(actionIdContainer.underlying)
                .initialOption(initialOption)
                .minQueryLength(minQueryLength)
                .confirm(confirmationDialogContainer.underlying)
                .build()
    }
}