package com.slack.api.model.kotlin_extension.block.element

import com.slack.api.model.block.composition.OptionGroupObject
import com.slack.api.model.block.composition.OptionObject
import com.slack.api.model.block.element.MultiStaticSelectElement
import com.slack.api.model.kotlin_extension.block.BlockLayoutBuilder
import com.slack.api.model.kotlin_extension.block.Builder
import com.slack.api.model.kotlin_extension.block.composition.container.MultiOptionContainer
import com.slack.api.model.kotlin_extension.block.composition.container.MultiOptionGroupObjectContainer
import com.slack.api.model.kotlin_extension.block.composition.container.SingleConfirmationDialogContainer
import com.slack.api.model.kotlin_extension.block.composition.dsl.ConfirmationDialogDsl
import com.slack.api.model.kotlin_extension.block.composition.dsl.OptionGroupObjectDsl
import com.slack.api.model.kotlin_extension.block.composition.dsl.OptionObjectDsl
import com.slack.api.model.kotlin_extension.block.element.container.SingleActionIdContainer
import com.slack.api.model.kotlin_extension.block.element.container.SinglePlaceholderContainer
import com.slack.api.model.kotlin_extension.block.element.dsl.ActionIdDsl
import com.slack.api.model.kotlin_extension.block.element.dsl.PlaceholderDsl

@BlockLayoutBuilder
class MultiStaticSelectElementBuilder private constructor(
        private val placeholderContainer: SinglePlaceholderContainer,
        private val actionIdContainer: SingleActionIdContainer,
        private val confirmationDialogContainer: SingleConfirmationDialogContainer
) : Builder<MultiStaticSelectElement>,
        PlaceholderDsl by placeholderContainer,
        ActionIdDsl by actionIdContainer,
        ConfirmationDialogDsl by confirmationDialogContainer {
    var options: List<OptionObject>? = null
    var optionGroups: List<OptionGroupObject>? = null
    var initialOptions: List<OptionObject>? = null
    var maxSelectedItems: Int? = null

    constructor() : this(SinglePlaceholderContainer(), SingleActionIdContainer(), SingleConfirmationDialogContainer())

    fun options(builder: OptionObjectDsl.() -> Unit) {
        options = MultiOptionContainer().apply(builder).underlying
    }

    fun optionGroups(builder: OptionGroupObjectDsl.() -> Unit) {
        optionGroups = MultiOptionGroupObjectContainer().apply(builder).underlying
    }

    fun initialOptions(builder: OptionObjectDsl.() -> Unit) {
        initialOptions = MultiOptionContainer().apply(builder).underlying
    }

    fun maxSelectedItems(max: Int) {
        maxSelectedItems = max
    }

    override fun build(): MultiStaticSelectElement {
        return MultiStaticSelectElement.builder()
                .placeholder(placeholderContainer.underlying)
                .actionId(actionIdContainer.underlying)
                .options(options)
                .optionGroups(optionGroups)
                .initialOptions(initialOptions)
                .confirm(confirmationDialogContainer.underlying)
                .maxSelectedItems(maxSelectedItems)
                .build()
    }
}