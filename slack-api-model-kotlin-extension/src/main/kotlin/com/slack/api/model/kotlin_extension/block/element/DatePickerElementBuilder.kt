package com.slack.api.model.kotlin_extension.block.element

import com.slack.api.model.block.element.DatePickerElement
import com.slack.api.model.kotlin_extension.block.BlockLayoutBuilder
import com.slack.api.model.kotlin_extension.block.Builder
import com.slack.api.model.kotlin_extension.block.composition.container.SingleConfirmationDialogContainer
import com.slack.api.model.kotlin_extension.block.composition.dsl.ConfirmationDialogDsl
import com.slack.api.model.kotlin_extension.block.element.container.SingleActionIdContainer
import com.slack.api.model.kotlin_extension.block.element.container.SinglePlaceholderContainer
import com.slack.api.model.kotlin_extension.block.element.dsl.ActionIdDsl
import com.slack.api.model.kotlin_extension.block.element.dsl.PlaceholderDsl

@BlockLayoutBuilder
class DatePickerElementBuilder private constructor(
        private val actionIdContainer: SingleActionIdContainer,
        private val confirmationDialogContainer: SingleConfirmationDialogContainer,
        private val placeholderContainer: SinglePlaceholderContainer
) : Builder<DatePickerElement>,
        ActionIdDsl by actionIdContainer,
        ConfirmationDialogDsl by confirmationDialogContainer,
        PlaceholderDsl by placeholderContainer {
    private var initialDate: String? = null

    constructor() : this(SingleActionIdContainer(), SingleConfirmationDialogContainer(), SinglePlaceholderContainer())

    fun initialDate(date: String) {
        initialDate = date
    }

    override fun build(): DatePickerElement {
        return DatePickerElement.builder()
                .actionId(actionIdContainer.underlying)
                .placeholder(placeholderContainer.underlying)
                .initialDate(initialDate)
                .confirm(confirmationDialogContainer.underlying)
                .build()
    }
}