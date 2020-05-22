package com.slack.api.model.kotlin_extension.block.element.dsl

import com.slack.api.model.kotlin_extension.block.BlockLayoutBuilder
import com.slack.api.model.kotlin_extension.block.element.ButtonElementBuilder
import com.slack.api.model.kotlin_extension.block.element.ChannelsSelectElementBuilder
import com.slack.api.model.kotlin_extension.block.element.CheckboxesElementBuilder

// same name with the object + "Dsl" suffix
@BlockLayoutBuilder
interface BlockElementDsl {

    fun button(builder: ButtonElementBuilder.() -> Unit)

    fun checkboxes(builder: CheckboxesElementBuilder.() -> Unit)

    fun channelsSelect(builder: ChannelsSelectElementBuilder.() -> Unit)
    // TODO add more block elements if the POC is successful
}
