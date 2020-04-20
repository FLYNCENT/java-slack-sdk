package com.slack.api.model.kotlin_extension.block.element.dsl

import com.slack.api.model.kotlin_extension.block.BlockLayoutBuilder

@BlockLayoutBuilder
interface BlockElementDsl {
  fun actionId(actionId: String)
}
