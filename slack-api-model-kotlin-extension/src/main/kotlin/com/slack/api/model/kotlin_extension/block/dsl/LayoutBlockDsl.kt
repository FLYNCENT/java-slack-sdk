package com.slack.api.model.kotlin_extension.block.dsl

import com.slack.api.model.kotlin_extension.block.BlockLayoutBuilder
import com.slack.api.model.kotlin_extension.block.ActionsBlockBuilder
import com.slack.api.model.kotlin_extension.block.SectionBlockBuilder

// same name with the object + "Dsl" suffix
@BlockLayoutBuilder
interface LayoutBlockDsl {
  fun section(blockId: String? = null, builder: SectionBlockBuilder.() -> Unit)
  fun divider(blockId: String? = null)
  fun actions(blockId: String? = null, builder: ActionsBlockBuilder.() -> Unit)
  // TODO add more layout block methods later if POC is successful
}
