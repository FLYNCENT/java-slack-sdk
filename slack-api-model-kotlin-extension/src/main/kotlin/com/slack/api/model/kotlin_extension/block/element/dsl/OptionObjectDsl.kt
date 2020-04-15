package com.slack.api.model.kotlin_extension.block.element.dsl

import com.slack.api.model.kotlin_extension.block.BlockLayoutBuilder
import com.slack.api.model.kotlin_extension.block.element.OptionObjectBuilder

// same name with the object + "Dsl" suffix
@BlockLayoutBuilder
interface OptionObjectDsl {
  fun option(
    value: String? = null,
    url: String? = null,
    builder: OptionObjectBuilder.() -> Unit)
}
