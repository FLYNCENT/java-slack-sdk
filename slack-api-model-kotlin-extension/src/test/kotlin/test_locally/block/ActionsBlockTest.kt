package test_locally.block

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
                }
            }
        }
        val result = gson.toJson(blocks)
        val expected = """[{"type":"actions","elements":[{"type":"button","text":{"type":"plain_text","text":"button 1"},"action_id":"a-btn","value":"v1"},{"type":"conversations_select","action_id":"a-conv"}],"block_id":"b-id"}]"""
        assertEquals(expected, result)
    }

}
