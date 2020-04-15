package com.slack.api.model.kotlin_extension.block.composition

import com.slack.api.model.kotlin_extension.block.BlockLayoutBuilder
import com.slack.api.model.block.composition.ConfirmationDialogObject
import com.slack.api.model.block.composition.PlainTextObject
import com.slack.api.model.kotlin_extension.block.Builder
import com.slack.api.model.kotlin_extension.block.composition.container.SingleTextObjectContainer
import com.slack.api.model.kotlin_extension.block.composition.dsl.TextObjectDsl

// same name with the object + "Builder" suffix
@BlockLayoutBuilder
class ConfirmationDialogObjectBuilder : Builder<ConfirmationDialogObject>, TextObjectDsl {
  // this one can be immutable
  private val textContainer = SingleTextObjectContainer()
  // intentionally all fields are mutable
  private var title: PlainTextObject? = null
  private var confirm: PlainTextObject? = null
  private var deny: PlainTextObject? = null

  fun title(text: String, emoji: Boolean? = null) {
    title = PlainTextObject(text, emoji)
  }

  override fun plainText(text: String, emoji: Boolean?) {
    textContainer.plainText(text, emoji)
  }

  override fun markdownText(text: String, verbatim: Boolean?) {
    textContainer.markdownText(text, verbatim)
  }

  fun confirm(text: String, emoji: Boolean? = null) {
    confirm = PlainTextObject(text, emoji)
  }

  fun deny(text: String, emoji: Boolean? = null) {
    deny = PlainTextObject(text, emoji)
  }

  override fun build(): ConfirmationDialogObject {
    return ConfirmationDialogObject.builder()
      .title(title)
      .text(textContainer.underlying)
      .confirm(confirm)
      .deny(deny)
      .build()
  }
}