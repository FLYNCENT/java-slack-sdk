package com.slack.api.model.kotlin_extension.block.dsl

import com.slack.api.model.kotlin_extension.block.BlockLayoutBuilder

@BlockLayoutBuilder
interface BlockDsl {
  fun blockId(blockId: String)
}
