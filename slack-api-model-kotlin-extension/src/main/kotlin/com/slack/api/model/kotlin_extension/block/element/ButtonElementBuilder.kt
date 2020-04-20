package com.slack.api.model.kotlin_extension.block.element

import com.slack.api.model.kotlin_extension.block.BlockLayoutBuilder
import com.slack.api.model.block.composition.ConfirmationDialogObject
import com.slack.api.model.block.composition.PlainTextObject
import com.slack.api.model.block.element.ButtonElement
import com.slack.api.model.kotlin_extension.block.Builder
import com.slack.api.model.kotlin_extension.block.composition.ConfirmationDialogObjectBuilder
import com.slack.api.model.kotlin_extension.block.element.dsl.BlockElementDsl

// same name with the object + "Builder" suffix
@BlockLayoutBuilder
class ButtonElementBuilder(
  private var actionId: String? = null,
  private var url: String? = null,
  private var value: String? = null,
  private var style: ButtonStyle? = null,
  private var text: PlainTextObject? = null,
  private var confirm: ConfirmationDialogObject? = null
) : Builder<ButtonElement>, BlockElementDsl {

  init {
    if (actionId?.length ?: 0 > 255) {
      throw IllegalArgumentException("Button actionId cannot be longer than 255 characters.")
    }
    if (url?.length ?: 0 > 3000) {
      throw java.lang.IllegalArgumentException("Button URL cannot be longer than 3000 characters.")
    }
    if (value?.length ?: 0 > 2000) {
      throw IllegalArgumentException("Button value cannot be longer than 2000 characters.")
    }
  }

 override fun actionId(actionId: String) {
    this.actionId = actionId
  }

  fun url(url: String) {
    this.url = url
  }

  fun value(value: String) {
    this.value = value
  }

  fun style(style: ButtonStyle) {
    this.style = style
  }

  fun plainText(buttonText: String, emoji: Boolean? = null) {
    if (buttonText.length > 75) {
      throw IllegalArgumentException("Button text length cannot be longer than 75 characters")
    }
    text = PlainTextObject(buttonText, emoji)
  }

  fun confirm(builder: ConfirmationDialogObjectBuilder.() -> Unit) {
    confirm = ConfirmationDialogObjectBuilder().apply(builder).build()
  }

  override fun build(): ButtonElement {
    return ButtonElement.builder()
      .actionId(actionId)
      .url(url)
      .value(value)
      .text(text)
      .style(style?.value)
      .confirm(confirm)
      .build()
  }
}