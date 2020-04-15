package com.slack.api.model.kotlin_extension.block.element.container

import com.slack.api.model.block.composition.OptionObject
import com.slack.api.model.kotlin_extension.block.element.OptionObjectBuilder
import com.slack.api.model.kotlin_extension.block.element.dsl.OptionObjectDsl

/**
 * Supports an OptionContainer that can have one to many options.
 */
class MultiOptionContainer : OptionObjectDsl {
  val options = mutableListOf<OptionObject>()

  override fun option(value: String?, url: String?, builder: OptionObjectBuilder.() -> Unit) {
    options += OptionObjectBuilder(value, url).apply(builder).build()
  }
}