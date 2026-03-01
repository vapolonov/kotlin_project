package frontend.components

import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide.elements
import frontend.helpers.Wrappers.Companion.byDataTestGroup
import io.qameta.allure.Step

class Header {
  private val linksHeader: ElementsCollection get() = elements(byDataTestGroup("nav-link"))

  @Step("Получить список ссылок в шапке")
  fun clickLink(linkName: String): Header {
    linksHeader.first { it.text == linkName }.click()
    return this
  }
}