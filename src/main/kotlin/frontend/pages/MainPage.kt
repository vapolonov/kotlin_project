package frontend.pages

import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.Selenide.open
import com.codeborne.selenide.SelenideElement
import frontend.components.Header
import frontend.helpers.Wrappers.Companion.byDataTestId
import io.qameta.allure.Step

open class MainPage {

  private val txtTitle: SelenideElement get() = `$`(byDataTestId("main-image-text"))

  @Step("Открыть главную страницу")
  fun openMainPage() {
    open("/")
  }

  @Step("Получить название кофейни")
  fun getTitle(): String {
    return txtTitle.text
  }

  @Step("Перейти к компоненту Header")
  fun header(): Header {
    return Header()
  }

}