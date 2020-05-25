package com.slack.api.model.kotlin_extension.block.element.dsl

import com.slack.api.model.kotlin_extension.block.BlockLayoutBuilder
import com.slack.api.model.kotlin_extension.block.element.*

// same name with the object + "Dsl" suffix
@BlockLayoutBuilder
interface BlockElementDsl : BlockElementInputDsl, BlockElementRichTextDsl {
    fun button(builder: ButtonElementBuilder.() -> Unit)

    fun checkboxes(builder: CheckboxesElementBuilder.() -> Unit)

    fun image(imageUrl: String? = null, altText: String? = null, fallback: String? = null, imageWidth: Int? = null, imageHeight: Int? = null, imageBytes: Int? = null)

    fun overflowMenu(builder: OverflowMenuElementBuilder.() -> Unit)

    fun radioButtons(builder: RadioButtonsElementBuilder.() -> Unit)
}
