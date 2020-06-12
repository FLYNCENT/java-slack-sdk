package test_locally.block

import com.slack.api.model.kotlin_extension.block.element.ConversationType
import com.slack.api.model.kotlin_extension.block.withBlocks
import com.slack.api.util.json.GsonFactory
import org.junit.Test
import kotlin.test.assertEquals

class ActionsBlockTest {

    @Test
    fun buttonAndConv() {
        val gson = GsonFactory.createSnakeCase()
        val blocks = withBlocks {
            actions {
                blockId("b-id")
                button {
                    actionId("a-btn")
                    text("button 1")
                    value("v1")
                }
                conversationsSelect {
                    actionId("a-conv")
                    conversationsFilter(ConversationType.PUBLIC, ConversationType.IM, excludeExternalSharedChannels = true, excludeBotUsers = true)
                }
            }
        }
        val result = gson.toJson(blocks)
        val expected = """[{"type":"actions","elements":[{"type":"button","text":{"type":"plain_text","text":"button 1"},"action_id":"a-btn","value":"v1"},{"type":"conversations_select","action_id":"a-conv","filter":{"include":["public","im"],"exclude_external_shared_channels":true,"exclude_bot_users":true}}],"block_id":"b-id"}]"""
        assertEquals(expected, result)
    }

    @Test
    fun `multi-selects`() {
        val gson = GsonFactory.createSnakeCase()
        val blocks = withBlocks {
            actions {
                multiChannelsSelect {
                    actionId("a-id")
                    placeholder("Pick a channel...")
                    initialChannels("general", "random")
                    maxSelectedItems(5)
                }
                multiConversationsSelect {
                    actionId("b-id")
                    filter(ConversationType.PUBLIC, ConversationType.PRIVATE)
                    initialConversations("user1", "user2")
                    placeholder("Pick users to chat with...")
                    maxSelectedItems(3)
                }
                multiExternalSelect {
                    initialOptions {
                        option {
                            value("option1")
                            url("https://www.google.com/")
                            description("Choose Option 1")
                        }
                    }
                    placeholder("Pick an option...")
                    minQueryLength(5)
                    maxSelectedItems(2)
                }
                multiStaticSelect {
                    optionGroups {
                        optionGroup {
                            label("Group 1")
                            option {
                                value("option2")
                                url("https://www.supergoogle.com/")
                                description("Choose option 2")
                            }
                            option {
                                value("option3")
                                url("https://www.google.com/")
                                description("Choose Option 3")
                            }
                        }
                    }
                    initialOptions {
                        option {
                            value("option2")
                            url("https://www.supergoogle.com/")
                            description("Choose option 2")
                        }
                    }
                }
                multiUsersSelect {
                    placeholder("Pick a user...")
                    maxSelectedItems(2)
                    initialUsers("erittenhouse", "seratch")
                }
            }
        }

        val result = gson.toJson(blocks)
        val expected = """[{"type":"actions","elements":[{"type":"multi_channels_select","placeholder":{"type":"plain_text","text":"Pick a channel..."},"action_id":"a-id","initial_channels":["general","random"],"max_selected_items":5},{"type":"multi_conversations_select","placeholder":{"type":"plain_text","text":"Pick users to chat with..."},"action_id":"b-id","initial_conversations":["user1","user2"],"max_selected_items":3,"filter":{"include":["public","private"]}},{"type":"multi_external_select","placeholder":{"type":"plain_text","text":"Pick an option..."},"initial_options":[{"value":"option1","description":{"type":"plain_text","text":"Choose Option 1"},"url":"https://www.google.com/"}],"min_query_length":5,"max_selected_items":2},{"type":"multi_static_select","option_groups":[{"label":{"type":"plain_text","text":"Group 1"},"options":[{"value":"option2","description":{"type":"plain_text","text":"Choose option 2"},"url":"https://www.supergoogle.com/"},{"value":"option3","description":{"type":"plain_text","text":"Choose Option 3"},"url":"https://www.google.com/"}]}],"initial_options":[{"value":"option2","description":{"type":"plain_text","text":"Choose option 2"},"url":"https://www.supergoogle.com/"}]},{"type":"multi_users_select","placeholder":{"type":"plain_text","text":"Pick a user..."},"initial_users":["erittenhouse","seratch"],"max_selected_items":2}]}]"""
        assertEquals(expected, result)
    }
}
