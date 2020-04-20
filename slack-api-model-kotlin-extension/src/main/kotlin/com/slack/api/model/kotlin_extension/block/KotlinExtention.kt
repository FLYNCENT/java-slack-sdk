package com.slack.api.model.kotlin_extension.block

import com.slack.api.model.block.LayoutBlock
import com.slack.api.model.kotlin_extension.block.dsl.BlocksDsl
import com.slack.api.model.kotlin_extension.block.container.MultiLayoutBlockContainer

fun withBlocks(builder: BlocksDsl.() -> Unit): List<LayoutBlock> {
  return MultiLayoutBlockContainer().apply(builder).underlying
}
