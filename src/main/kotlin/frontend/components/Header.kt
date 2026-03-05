package frontend.components

import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.Selenide.elements
import com.codeborne.selenide.SelenideElement
import frontend.helpers.Wrappers.Companion.byDataTestGroup
import frontend.helpers.Wrappers.Companion.shouldBeVisible
import io.qameta.allure.Step

class Header {
  private val linksHeader: ElementsCollection get() = elements(byDataTestGroup("nav-link"))
  private val avatar: SelenideElement get() = element(".avatar")

  @Step("Получить список ссылок в шапке")
  fun clickLink(linkName: String): Header {
    val link = linksHeader.find { it.text().contains(linkName) }
    link?.click()
    return this
  }

  @Step("Получить список ссылок в шапке")
  fun getLinks(): List<String> {
    return linksHeader.map { it.text }
  }

  @Step("Проверить видимость аватарки")
  fun checkAvatar(): Boolean {
    return avatar.shouldBeVisible()
  }
}