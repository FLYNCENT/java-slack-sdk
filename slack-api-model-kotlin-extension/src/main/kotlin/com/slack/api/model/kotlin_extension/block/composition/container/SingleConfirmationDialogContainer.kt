package com.slack.api.model.kotlin_extension.block.composition.container

import com.slack.api.model.block.composition.ConfirmationDialogObject
import com.slack.api.model.kotlin_extension.block.composition.ConfirmationDialogObjectBuilder
import com.slack.api.model.kotlin_extension.block.composition.dsl.ConfirmationDialogDsl

class SingleConfirmationDialogContainer : ConfirmationDialogDsl {
    var underlying: ConfirmationDialogObject? = null

    override fun confirm(builder: ConfirmationDialogObjectBuilder.() -> Unit) {
        underlying = ConfirmationDialogObjectBuilder().apply(builder).build()
    }
}