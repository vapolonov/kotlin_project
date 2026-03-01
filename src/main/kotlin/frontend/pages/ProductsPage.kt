package frontend.pages

import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.Selenide.`$$`
import com.codeborne.selenide.SelenideElement
import frontend.helpers.Wrappers.Companion.byDataTestGroup
import frontend.helpers.Wrappers.Companion.byDataTestId
import io.qameta.allure.Step

class ProductsPage {

  private val txtTitle: SelenideElement get() = `$`(byDataTestId("products-title"))
  private val listItems: ElementsCollection get() = `$$`(byDataTestGroup("product-card"))

  @Step("Получить название страницы продуктов")
  fun getTitle(): String {
    return txtTitle.text
  }

  @Step("Получить список продуктов на станице")
  fun getProducts(): ElementsCollection {
    return listItems
  }
}