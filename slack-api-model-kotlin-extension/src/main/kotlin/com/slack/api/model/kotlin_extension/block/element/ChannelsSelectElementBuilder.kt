package com.slack.api.model.kotlin_extension.block.element

import com.slack.api.model.kotlin_extension.block.BlockLayoutBuilder
import com.slack.api.model.block.composition.ConfirmationDialogObject
import com.slack.api.model.block.composition.PlainTextObject
import com.slack.api.model.block.element.ChannelsSelectElement
import com.slack.api.model.kotlin_extension.block.Builder
import com.slack.api.model.kotlin_extension.block.composition.ConfirmationDialogObjectBuilder

// same name with the object + "Builder" suffix
@BlockLayoutBuilder
class ChannelsSelectElementBuilder(
  private var actionId: String?,
  private var initialChannel: String?,
  private var responseUrlEnabled: Boolean?,
  private var placeholder: PlainTextObject? = null,
  private var confirm: ConfirmationDialogObject? = null
) : Builder<ChannelsSelectElement> {

  init {
    if (actionId?.length ?: 0 > 255) {
      throw IllegalArgumentException("Channel select actionId cannot be longer than 255 characters.")
    }
  }

  fun placeholder(text: String, emoji: Boolean? = null) {
    if (text.length > 150) {
      throw IllegalArgumentException("Channel select placeholder text cannot be longer than 150 characters.")
    }
    placeholder = PlainTextObject(text, emoji)
  }

  fun confirm(builder: ConfirmationDialogObjectBuilder.() -> Unit) {
    confirm = ConfirmationDialogObjectBuilder().apply(builder).build()
  }

  override fun build(): ChannelsSelectElement {
    return ChannelsSelectElement.builder()
      .actionId(actionId)
      .placeholder(placeholder)
      .initialChannel(initialChannel)
      .confirm(confirm)
      .responseUrlEnabled(responseUrlEnabled)
      .build()
  }
}