package com.slack.api.model.kotlin_extension.block.dsl

import com.slack.api.model.kotlin_extension.block.BlockLayoutBuilder
import com.slack.api.model.kotlin_extension.block.composition.dsl.TextObjectDsl

@BlockLayoutBuilder
interface BlockFieldsDsl {
  fun fields(fields: TextObjectDsl.() -> Unit)
}
