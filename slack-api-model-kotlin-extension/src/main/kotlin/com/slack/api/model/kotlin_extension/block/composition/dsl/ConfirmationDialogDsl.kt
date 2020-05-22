package com.slack.api.model.kotlin_extension.block.composition.dsl

import com.slack.api.model.kotlin_extension.block.BlockLayoutBuilder
import com.slack.api.model.kotlin_extension.block.composition.ConfirmationDialogObjectBuilder

@BlockLayoutBuilder
interface ConfirmationDialogDsl {
    fun confirm(builder: ConfirmationDialogObjectBuilder.() -> Unit)
}