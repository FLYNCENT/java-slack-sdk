package com.slack.api.model.kotlin_extension.block.element.dsl

import com.slack.api.model.kotlin_extension.block.BlockLayoutBuilder
import com.slack.api.model.kotlin_extension.block.element.*

@BlockLayoutBuilder
interface RichTextElementDsl {
    fun text(builder: RichTextElementTextBuilder.() -> Unit)

    fun channel(builder: RichTextElementChannelBuilder.() -> Unit)

    fun user(builder: RichTextElementUserBuilder.() -> Unit)

    fun emoji(builder: RichTextElementEmojiBuilder.() -> Unit)

    fun link(builder: RichTextElementLinkBuilder.() -> Unit)

    fun team(builder: RichTextElementTeamBuilder.() -> Unit)

    fun usergroup(usergroupId: String)

    fun date(timestamp: String)

    fun broadcast(range: String)

    fun color(value: String)

    fun richTextList(builder: RichTextListElementBuilder.() -> Unit)

    fun richTextPreformatted(builder: RichTextPreformattedElementBuilder.() -> Unit)

    fun richTextQuote(builder: RichTextQuoteElementBuilder.() -> Unit)

    fun richTextSection(builder: RichTextSectionElementBuilder.() -> Unit)
}