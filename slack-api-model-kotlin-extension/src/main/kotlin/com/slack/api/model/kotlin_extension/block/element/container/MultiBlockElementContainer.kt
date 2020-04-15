package com.slack.api.model.kotlin_extension.block.element.container

import com.slack.api.model.block.composition.PlainTextObject
import com.slack.api.model.block.element.BlockElement
import com.slack.api.model.kotlin_extension.block.element.*
import com.slack.api.model.kotlin_extension.block.element.dsl.BlockElementDsl

/**
 * Supports a BlockElementContainer that can hold one to many block elements
 */
class MultiBlockElementContainer : BlockElementDsl {
  val underlying = mutableListOf<BlockElement>()

  override fun button(
    actionId: String?,
    url: String?,
    value: String?,
    style: ButtonStyle?,
    text: PlainTextObject?,
    builder: ButtonElementBuilder.() -> Unit) {
    underlying += ButtonElementBuilder(
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
    underlying += CheckboxesElementBuilder(
      actionId = actionId
    ).apply(builder).build()
  }

  override fun channelsSelect(
    actionId: String?,
    initialChannel: String?,
    responseUrlEnabled: Boolean?,
    builder: ChannelsSelectElementBuilder.() -> Unit) {
    underlying += ChannelsSelectElementBuilder(
      actionId = actionId,
      initialChannel = initialChannel,
      responseUrlEnabled = responseUrlEnabled
    ).apply(builder).build()
  }
}