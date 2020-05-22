package com.slack.api.model.kotlin_extension.block.element

import com.slack.api.model.block.composition.OptionObject
import com.slack.api.model.block.element.CheckboxesElement
import com.slack.api.model.kotlin_extension.block.BlockLayoutBuilder
import com.slack.api.model.kotlin_extension.block.Builder
import com.slack.api.model.kotlin_extension.block.composition.container.SingleConfirmationDialogContainer
import com.slack.api.model.kotlin_extension.block.composition.dsl.ConfirmationDialogDsl
import com.slack.api.model.kotlin_extension.block.element.container.MultiOptionContainer
import com.slack.api.model.kotlin_extension.block.element.container.SingleActionIdContainer
import com.slack.api.model.kotlin_extension.block.element.dsl.ActionIdDsl
import com.slack.api.model.kotlin_extension.block.element.dsl.OptionObjectDsl

// same name with the object + "Builder" suffix
@BlockLayoutBuilder
class CheckboxesElementBuilder private constructor(
        private val actionIdContainer: SingleActionIdContainer,
        private val confirmationDialogContainer: SingleConfirmationDialogContainer
) : Builder<CheckboxesElement>, ActionIdDsl by actionIdContainer, ConfirmationDialogDsl by confirmationDialogContainer {
    private var options: List<OptionObject>? = null
    private var initialOptions: List<OptionObject>? = null

    constructor() : this(SingleActionIdContainer(), SingleConfirmationDialogContainer())

    fun options(builder: OptionObjectDsl.() -> Unit) {
        options = MultiOptionContainer().apply(builder).underlying
    }

    fun initialOptions(builder: OptionObjectDsl.() -> Unit) {
        initialOptions = MultiOptionContainer().apply(builder).underlying
    }

    override fun build(): CheckboxesElement {
        return CheckboxesElement.builder()
                .actionId(actionIdContainer.underlying)
                .options(options)
                .initialOptions(initialOptions)
                .confirm(confirmationDialogContainer.underlying)
                .build()
    }
}