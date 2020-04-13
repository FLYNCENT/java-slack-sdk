package com.slack.api.model.block.element

import com.slack.api.SlackAPIBuilder
import com.slack.api.model.block.composition.OptionObject

/**
 * Interface for adding one to many options to this container
 */
@SlackAPIBuilder
interface OptionContainer {
    fun option(value: String? = null, url: String? = null, buildOption: OptionBuilder.() -> Unit)
}

/**
 * Supports an OptionContainer that can have one to many options.
 */
class MultiOptionContainerImpl : OptionContainer {
    val options = mutableListOf<OptionObject>()

    override fun option(value: String?, url: String?, buildOption: OptionBuilder.() -> Unit) {
        options += OptionBuilder(value, url).apply(buildOption).build()
    }
}