package com.slack.api.model.kotlin_extension.block.element.container

import com.slack.api.model.block.composition.PlainTextObject
import com.slack.api.model.block.element.BlockElement
import com.slack.api.model.kotlin_extension.block.element.*
import com.slack.api.model.kotlin_extension.block.element.dsl.BlockElementDsl

/**
 * Supports a BlockElementContainer holding exactly one block element
 */
class SingleBlockElementContainer : BlockElementDsl {
  var underlying: BlockElement? = null

  override fun button(
    actionId: String?,
    url: String?,
    value: String?,
    style: ButtonStyle?,
    text: PlainTextObject?,
    builder: ButtonElementBuilder.() -> Unit) {
    underlying = ButtonElementBuilder(
      actionId = actionId,
      url = url,
      value = value,
      style = style,
      text = text
    ).apply(builder).build()
  }

  override fun checkboxes(
    actionId: String?,
    builder: CheckboxesElementBuilder.() -> Unit) {
    underlying = CheckboxesElementBuilder(
      actionId = actionId
    ).apply(builder).build()
  }

  override fun channelsSelect(
    initialChannel: String?,
    actionId: String?,
    responseUrlEnabled: Boolean?,
    builder: ChannelsSelectElementBuilder.() -> Unit) {
    underlying = ChannelsSelectElementBuilder(
      initialChannel = initialChannel,
      actionId = actionId,
      responseUrlEnabled = responseUrlEnabled
    ).apply(builder).build()
  }
}