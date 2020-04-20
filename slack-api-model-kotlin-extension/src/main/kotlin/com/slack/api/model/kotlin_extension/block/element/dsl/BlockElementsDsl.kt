package com.slack.api.model.kotlin_extension.block.element.dsl

import com.slack.api.model.block.composition.PlainTextObject
import com.slack.api.model.kotlin_extension.block.BlockLayoutBuilder
import com.slack.api.model.kotlin_extension.block.element.ButtonElementBuilder
import com.slack.api.model.kotlin_extension.block.element.ButtonStyle
import com.slack.api.model.kotlin_extension.block.element.ChannelsSelectElementBuilder
import com.slack.api.model.kotlin_extension.block.element.CheckboxesElementBuilder

// same name with the object + "Dsl" suffix
@BlockLayoutBuilder
interface BlockElementsDsl {

  fun button(
    actionId: String? = null,
    url: String? = null,
    value: String? = null,
    style: ButtonStyle? = null,
    text: PlainTextObject? = null,
    builder: ButtonElementBuilder.() -> Unit
  )

  fun checkboxes(
    actionId: String? = null,
    builder: CheckboxesElementBuilder.() -> Unit)

  fun channelsSelect(
    actionId: String? = null,
    initialChannel: String? = null,
    responseUrlEnabled: Boolean? = null,
    builder: ChannelsSelectElementBuilder.() -> Unit
  )
  // TODO add more block elements if the POC is successful
}
