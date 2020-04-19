package com.slack.api.model.kotlin_extension.block.dsl

import com.slack.api.model.kotlin_extension.block.*

// same name with the object + "Dsl" suffix
@BlockLayoutBuilder
interface LayoutBlockDsl {
    fun section(blockId: String? = null, builder: SectionBlockBuilder.() -> Unit)
    fun divider(blockId: String? = null)
    fun actions(blockId: String? = null, builder: ActionsBlockBuilder.() -> Unit)
    fun context(blockId: String? = null, builder: ContextBlockBuilder.() -> Unit)
    fun file(blockId: String? = null, externalId: String? = null, source: FileSource? = null)
    fun image(fallback: String? = null, imageUrl: String? = null, imageWidth: Int? = null, imageHeight: Int? = null, imageBytes: Int? = null, altText: String? = null, blockId: String? = null, builder: ImageBlockBuilder.() -> Unit)
}
