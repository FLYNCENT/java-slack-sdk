package com.slack.api.model.kotlin_extension.block

import com.slack.api.model.block.ActionsBlock
import com.slack.api.model.block.composition.PlainTextObject
import com.slack.api.model.kotlin_extension.block.dsl.BlockDsl
import com.slack.api.model.kotlin_extension.block.element.*
import com.slack.api.model.kotlin_extension.block.element.dsl.BlockElementsDsl
import com.slack.api.model.kotlin_extension.block.element.container.MultiBlockElementContainer

// same name with the object + "Builder" suffix
@BlockLayoutBuilder
class ActionsBlockBuilder(
  private var blockId: String?
) : Builder<ActionsBlock>, BlockDsl, BlockElementsDsl {

  private val elementsContainer = MultiBlockElementContainer()

  override fun blockId(blockId: String) {
    this.blockId = blockId
  }

  override fun button(
    actionId: String?,
    url: String?,
    value: String?,
    style: ButtonStyle?,
    text: PlainTextObject?,
    builder: ButtonElementBuilder.() -> Unit) {
    elementsContainer.button(
      actionId = actionId,
      url = url,
      value = value,
      style = style,
      builder = builder
    )
  }

  override fun checkboxes(
    actionId: String?,
    builder:
    CheckboxesElementBuilder.() -> Unit) {
    elementsContainer.checkboxes(
      actionId,
      builder
    )
  }

  override fun channelsSelect(
    initialChannel: String?,
    actionId: String?,
    responseUrlEnabled: Boolean?,
    builder: ChannelsSelectElementBuilder.() -> Unit) {
    elementsContainer.channelsSelect(initialChannel, actionId, responseUrlEnabled, builder)
  }

  override fun build(): ActionsBlock {
    return ActionsBlock.builder()
      .blockId(blockId)
      .elements(elementsContainer.underlying)
      .build()
  }
}