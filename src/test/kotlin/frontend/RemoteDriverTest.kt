package frontend

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.open
import com.codeborne.selenide.Selenide.sleep
import frontend.helpers.BaseUiTest
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class RemoteDriverTest : BaseUiTest() {

  @Test
  @DisplayName("Проверка открытия google.com через Selenoid")
  fun remoteDriverTest() {
    val title = Selenide.title()
    sleep(10000)
    title shouldBe "Google"

  }
}