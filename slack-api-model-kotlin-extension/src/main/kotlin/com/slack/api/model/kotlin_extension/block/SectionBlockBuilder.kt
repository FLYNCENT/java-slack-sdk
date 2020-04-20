package com.slack.api.model.kotlin_extension.block

import com.slack.api.model.block.SectionBlock
import com.slack.api.model.block.composition.PlainTextObject
import com.slack.api.model.block.composition.TextObject
import com.slack.api.model.kotlin_extension.block.composition.container.MultiTextObjectContainer
import com.slack.api.model.kotlin_extension.block.composition.container.SingleTextObjectContainer
import com.slack.api.model.kotlin_extension.block.composition.dsl.TextObjectDsl
import com.slack.api.model.kotlin_extension.block.dsl.BlockDsl
import com.slack.api.model.kotlin_extension.block.dsl.BlockFieldsDsl
import com.slack.api.model.kotlin_extension.block.element.*
import com.slack.api.model.kotlin_extension.block.element.dsl.BlockElementsDsl
import com.slack.api.model.kotlin_extension.block.element.container.SingleBlockElementContainer

// same name with the object + "Builder" suffix
@BlockLayoutBuilder
class SectionBlockBuilder(
  private var blockId: String?
) : Builder<SectionBlock>, BlockDsl, BlockFieldsDsl, TextObjectDsl, BlockElementsDsl {
  // Need to separate "fields" and "fieldsContainer" because the delegate makes the list non-null by default
  private var fields: MutableList<TextObject>? = null
  private val fieldsContainer = MultiTextObjectContainer()
  private val textContainer = SingleTextObjectContainer()
  private val accessoryContainer = SingleBlockElementContainer()

  override fun blockId(blockId: String) {
    this.blockId = blockId
  }

  override fun fields(builder: TextObjectDsl.() -> Unit) {
    fields = fieldsContainer.apply(builder).underlying
  }

  override fun plainText(text: String, emoji: Boolean?) {
    textContainer.plainText(text, emoji)
  }

  override fun markdownText(text: String, verbatim: Boolean?) {
    textContainer.markdownText(text, verbatim)
  }
  override fun button(
    actionId: String?,
    url: String?,
    value: String?,
    style: ButtonStyle?,
    text: PlainTextObject?,
    builder: ButtonElementBuilder.() -> Unit) =
    accessoryContainer.button(
      actionId = actionId,
      url = url,
      value = value,
      style = style,
      text = text,
      builder = builder
    )

  override fun checkboxes(
    actionId: String?,
    builder: CheckboxesElementBuilder.() -> Unit) {
    accessoryContainer.checkboxes(actionId, builder)
  }

  override fun channelsSelect(
    actionId: String?,
    initialChannel: String?,
    responseUrlEnabled: Boolean?,
    builder: ChannelsSelectElementBuilder.() -> Unit) {
    accessoryContainer.channelsSelect(
      actionId,
      initialChannel,
      responseUrlEnabled,
      builder
    )
  }

  override fun build(): SectionBlock {
    return SectionBlock.builder()
      .blockId(blockId)
      .fields(fields)
      .accessory(accessoryContainer.underlying)
      .text(textContainer.underlying)
      .build()
  }

}
