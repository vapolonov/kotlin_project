package frontend.pages

import com.codeborne.selenide.CollectionCondition.sizeGreaterThan
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.Selenide.elements
import com.codeborne.selenide.Selenide.open
import com.codeborne.selenide.SelenideElement
import frontend.components.AuthPopup
import frontend.components.Header
import frontend.components.list.ProductItem
import frontend.components.list.ProductsItems
import frontend.helpers.Wrappers.Companion.byDataTestGroup
import frontend.helpers.Wrappers.Companion.byDataTestId
import io.qameta.allure.Step

open class MainPage {

  private val txtTitle: SelenideElement get() = element(byDataTestId("main-image-text"))
  private val joinBtn: SelenideElement get() = element(byDataTestId("nav-link-auth"))
  private val loginLink: SelenideElement get() = element(byDataTestId("create-login"))
  private val listPopularProducts: ElementsCollection get() = elements(byDataTestGroup("product-card"))

  @Step("Открыть главную страницу")
  fun openMainPage(): MainPage {
    open("/")
    return this
  }

  @Step("Получить название кофейни")
  fun getTitle(): String {
    return txtTitle.text
  }

  @Step("Поверить заголовок")
  fun verifyTitle(): MainPage {
    txtTitle.shouldBe(visible)
    return this
  }

  @Step("Перейти к компоненту Header")
  fun header(): Header {
    return Header()
  }

  @Step("Перейти к компоненту AuthPopup")
  fun authPopup(): AuthPopup {
    return AuthPopup()
  }

  @Step("Открыть форму Login")
  fun openLoginForm(): MainPage {
    joinBtn.click()
    loginLink.click()
    return this
  }

  @Step("Получить список популярных товаров")
  fun getPopularProducts(): List<ProductItem> {
    listPopularProducts.shouldHave(sizeGreaterThan(0))
    return ProductsItems().getItems()
  }
}