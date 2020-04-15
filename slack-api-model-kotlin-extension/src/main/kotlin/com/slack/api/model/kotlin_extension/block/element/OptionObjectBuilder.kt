package com.slack.api.model.kotlin_extension.block.element

import com.slack.api.model.kotlin_extension.block.BlockLayoutBuilder
import com.slack.api.model.block.composition.OptionObject
import com.slack.api.model.block.composition.PlainTextObject
import com.slack.api.model.kotlin_extension.block.Builder
import com.slack.api.model.kotlin_extension.block.composition.container.SingleTextObjectContainer
import com.slack.api.model.kotlin_extension.block.composition.dsl.TextObjectDsl

// same name with the object + "Builder" suffix
@BlockLayoutBuilder
class OptionObjectBuilder(
  private var value: String?,
  private var url: String?,
  private var description: PlainTextObject? = null,
  private val textContainer: SingleTextObjectContainer = SingleTextObjectContainer()
) : Builder<OptionObject>, TextObjectDsl {

  init {
    if (url?.length ?: 0 > 3000) {
      throw IllegalArgumentException("Option URL cannot be longer than 3000 characters.")
    }
  }

  fun description(text: String, emoji: Boolean? = null) {
    if (text.length > 75) {
      throw IllegalArgumentException("Option description cannot be longer than 75 characters.")
    }
    description = PlainTextObject(text, emoji)
  }

  override fun plainText(text: String, emoji: Boolean?) {
    textContainer.plainText(text, emoji)
  }

  override fun markdownText(text: String, verbatim: Boolean?) {
    textContainer.markdownText(text, verbatim)
  }

  override fun build(): OptionObject {
    return OptionObject.builder()
      .description(description)
      .text(textContainer.underlying)
      .value(value)
      .url(url)
      .build()
  }
}