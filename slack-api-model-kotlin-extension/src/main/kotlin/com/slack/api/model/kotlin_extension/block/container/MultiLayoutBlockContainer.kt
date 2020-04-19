package com.slack.api.model.kotlin_extension.block.container

import com.slack.api.model.block.DividerBlock
import com.slack.api.model.block.FileBlock
import com.slack.api.model.block.LayoutBlock
import com.slack.api.model.kotlin_extension.block.*
import com.slack.api.model.kotlin_extension.block.dsl.LayoutBlockDsl

/**
 * Supports a LayoutBlockContainer that can have one to many layout block elements.
 */
class MultiLayoutBlockContainer : LayoutBlockDsl {
    val underlying = mutableListOf<LayoutBlock>()

    override fun section(blockId: String?, builder: SectionBlockBuilder.() -> Unit) {
        underlying += SectionBlockBuilder(blockId).apply(builder).build()
    }

    override fun divider(blockId: String?) {
        underlying += DividerBlock(blockId)
    }

    override fun actions(blockId: String?, builder: ActionsBlockBuilder.() -> Unit) {
        underlying += ActionsBlockBuilder(blockId).apply(builder).build()
    }

    override fun context(blockId: String?, builder: ContextBlockBuilder.() -> Unit) {
        underlying += ContextBlockBuilder(blockId).apply(builder).build()
    }

    override fun file(blockId: String?, externalId: String?, source: FileSource?) {
        underlying += FileBlock.builder()
                .blockId(blockId)
                .externalId(externalId)
                .source(source?.value)
                .build()
    }

    override fun image(fallback: String?, imageUrl: String?, imageWidth: Int?, imageHeight: Int?, imageBytes: Int?, altText: String?, blockId: String?, builder: ImageBlockBuilder.() -> Unit) {
        underlying += ImageBlockBuilder(
                fallback = fallback,
                imageUrl = imageUrl,
                imageWidth = imageWidth,
                imageHeight = imageHeight,
                imageBytes = imageBytes,
                altText = altText,
                blockId = blockId
        ).apply(builder).build()
    }
}