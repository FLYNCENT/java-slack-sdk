package com.slack.api.model.kotlin_extension.block.element

import com.slack.api.model.block.element.ConversationsFilter
import com.slack.api.model.block.element.MultiConversationsSelectElement
import com.slack.api.model.kotlin_extension.block.BlockLayoutBuilder
import com.slack.api.model.kotlin_extension.block.Builder
import com.slack.api.model.kotlin_extension.block.composition.container.SingleConfirmationDialogContainer
import com.slack.api.model.kotlin_extension.block.composition.dsl.ConfirmationDialogDsl
import com.slack.api.model.kotlin_extension.block.element.container.SingleActionIdContainer
import com.slack.api.model.kotlin_extension.block.element.container.SinglePlaceholderContainer
import com.slack.api.model.kotlin_extension.block.element.dsl.ActionIdDsl
import com.slack.api.model.kotlin_extension.block.element.dsl.PlaceholderDsl

@BlockLayoutBuilder
class MultiConversationsSelectElementBuilder private constructor(
        private val actionIdContainer: SingleActionIdContainer,
        private val confirmationDialogContainer: SingleConfirmationDialogContainer,
        private val placeholderContainer: SinglePlaceholderContainer
) : Builder<MultiConversationsSelectElement>,
        ActionIdDsl by actionIdContainer,
        ConfirmationDialogDsl by confirmationDialogContainer,
        PlaceholderDsl by placeholderContainer {
    private var initialConversations: List<String>? = null
    private var maxSelectedItems: Int? = null
    private var filter: ConversationsFilter? = null

    constructor() : this(SingleActionIdContainer(), SingleConfirmationDialogContainer(), SinglePlaceholderContainer())

    fun initialConversations(vararg conversations: String) {
        initialConversations = conversations.toList()
    }

    fun maxSelectedItems(items: Int) {
        maxSelectedItems = items
    }

    fun filter(vararg include: ConversationType, excludeExternalSharedChannels: Boolean? = null, excludeBotUsers: Boolean? = null) {
        filter = ConversationsFilter(include.map { it.value }, excludeExternalSharedChannels, excludeBotUsers)
    }

    override fun build(): MultiConversationsSelectElement {
        return MultiConversationsSelectElement.builder()
                .placeholder(placeholderContainer.underlying)
                .actionId(actionIdContainer.underlying)
                .initialConversations(initialConversations)
                .confirm(confirmationDialogContainer.underlying)
                .maxSelectedItems(maxSelectedItems)
                .filter(filter)
                .build()
    }
}