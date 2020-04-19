package com.slack.api.model.kotlin_extension.block

import com.slack.api.model.block.ImageBlock
import com.slack.api.model.block.composition.PlainTextObject

@BlockLayoutBuilder
class ImageBlockBuilder(
        private var fallback: String?,
        private var imageUrl: String?,
        private var imageWidth: Int?,
        private var imageHeight: Int?,
        private var imageBytes: Int?,
        private var altText: String?,
        private var blockId: String?
) : Builder<ImageBlock> {
    private var title: PlainTextObject? = null

    fun title(text: String, emoji: Boolean? = null) {
        title = PlainTextObject(text, emoji)
    }

    override fun build(): ImageBlock {
        return ImageBlock.builder()
                .fallback(fallback)
                .imageUrl(imageUrl)
                .imageWidth(imageWidth)
                .imageHeight(imageHeight)
                .imageBytes(imageBytes)
                .altText(altText)
                .blockId(blockId)
                .title(title)
                .build()
    }
}