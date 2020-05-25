package com.slack.api.model.kotlin_extension.block.element.dsl

import com.slack.api.model.kotlin_extension.block.BlockLayoutBuilder
import com.slack.api.model.kotlin_extension.block.element.*

// same name with the object + "Dsl" suffix
@BlockLayoutBuilder
interface BlockElementDsl {

    fun button(builder: ButtonElementBuilder.() -> Unit)

    fun checkboxes(builder: CheckboxesElementBuilder.() -> Unit)

    fun channelsSelect(builder: ChannelsSelectElementBuilder.() -> Unit)

    fun conversationsSelect(builder: ConversationsSelectElementBuilder.() -> Unit)

    fun datePicker(builder: DatePickerElementBuilder.() -> Unit)

    fun externalSelect(builder: ExternalSelectElementBuilder.() -> Unit)

    fun image(imageUrl: String? = null, altText: String? = null, fallback: String? = null, imageWidth: Int? = null, imageHeight: Int? = null, imageBytes: Int? = null)

    fun multiChannelsSelect(builder: MultiChannelsSelectElementBuilder.() -> Unit)

    fun multiConversationsSelect(builder: MultiConversationsSelectElementBuilder.() -> Unit)

    fun multiExternalSelect(builder: MultiExternalSelectElementBuilder.() -> Unit)

    fun multiStaticSelect(builder: MultiStaticSelectElementBuilder.() -> Unit)

    fun multiUsersSelect(builder: MultiUsersSelectElementBuilder.() -> Unit)

    fun overflowMenu(builder: OverflowMenuElementBuilder.() -> Unit)

    fun plainTextInput(builder: PlainTextInputElementBuilder.() -> Unit)

    fun radioButtons(builder: RadioButtonsElementBuilder.() -> Unit)

    fun richTextList(builder: RichTextListElementBuilder.() -> Unit)

    fun richTextPreformatted(builder: RichTextPreformattedElementBuilder.() -> Unit)

    fun richTextQuote(builder: RichTextQuoteElementBuilder.() -> Unit)

    fun richTextSection(builder: RichTextSectionElementBuilder.() -> Unit)

    fun staticSelect(builder: StaticSelectElementBuilder.() -> Unit)

    fun usersSelect(builder: UsersSelectElementBuilder.() -> Unit)
}
