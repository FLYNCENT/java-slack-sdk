package com.slack.api.model.kotlin_extension.block.element

import com.slack.api.model.block.element.ConversationsFilter
import com.slack.api.model.block.element.ConversationsSelectElement
import com.slack.api.model.kotlin_extension.block.BlockLayoutBuilder
import com.slack.api.model.kotlin_extension.block.Builder
import com.slack.api.model.kotlin_extension.block.composition.container.SingleConfirmationDialogContainer
import com.slack.api.model.kotlin_extension.block.composition.dsl.ConfirmationDialogDsl
import com.slack.api.model.kotlin_extension.block.element.container.SingleActionIdContainer
import com.slack.api.model.kotlin_extension.block.element.container.SinglePlaceholderContainer
import com.slack.api.model.kotlin_extension.block.element.dsl.ActionIdDsl
import com.slack.api.model.kotlin_extension.block.element.dsl.PlaceholderDsl

@BlockLayoutBuilder
class ConversationsSelectElementBuilder private constructor(
        private val actionIdContainer: SingleActionIdContainer,
        private val confirmationDialogContainer: SingleConfirmationDialogContainer,
        private val placeholderContainer: SinglePlaceholderContainer
) : Builder<ConversationsSelectElement>,
        ActionIdDsl by actionIdContainer,
        ConfirmationDialogDsl by confirmationDialogContainer,
        PlaceholderDsl by placeholderContainer {
    private var initialConversation: String? = null
    private var responseUrlEnabled: Boolean? = null
    private var conversationsFilter: ConversationsFilter? = null

    constructor() : this(SingleActionIdContainer(), SingleConfirmationDialogContainer(), SinglePlaceholderContainer())

    fun initialConversation(conversation: String) {
        initialConversation = conversation
    }

    fun responseUrlEnabled(enabled: Boolean) {
        responseUrlEnabled = enabled
    }

    fun conversationsFilter(vararg include: ConversationType, excludeExternalSharedChannels: Boolean = false, excludeBotUsers: Boolean = false) {
        conversationsFilter = ConversationsFilter.builder()
                .include(include.map { it.value })
                .excludeExternalSharedChannels(excludeExternalSharedChannels)
                .excludeBotUsers(excludeBotUsers)
                .build()
    }

    override fun build(): ConversationsSelectElement {
        return ConversationsSelectElement.builder()
                .placeholder(placeholderContainer.underlying)
                .actionId(actionIdContainer.underlying)
                .initialConversation(initialConversation)
                .confirm(confirmationDialogContainer.underlying)
                .responseUrlEnabled(responseUrlEnabled)
                .filter(conversationsFilter)
                .build()
    }
}