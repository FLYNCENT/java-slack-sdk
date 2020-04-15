package test_locally

import com.slack.api.model.kotlin_extension.block.element.ButtonElementBuilder
import org.junit.Test
import kotlin.test.assertFailsWith

class ButtonValidationsTest {

  @Test
  fun `Can enforce limits and restrictions`() {
    assertFailsWith<IllegalArgumentException> {
      ButtonElementBuilder(actionId = randomString(256), url = null, value = null)
    }

    assertFailsWith<IllegalArgumentException> {
      ButtonElementBuilder(url = randomString(3001), actionId = null, value = null)
    }

    assertFailsWith<java.lang.IllegalArgumentException> {
      ButtonElementBuilder(value = randomString(2001), url = null, actionId = null)
    }

    assertFailsWith<java.lang.IllegalArgumentException> {
      ButtonElementBuilder().apply {
        plainText(randomString(76))
      }
    }
  }

  private fun randomString(length: Int): String {
    return buildString {
      for (character in 1..length) {
        append("abcdefghijklmnopqrstuvwxyz".random())
      }
    }
  }
}