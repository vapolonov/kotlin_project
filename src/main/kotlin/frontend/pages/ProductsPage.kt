package frontend.pages

import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.Selenide.elements
import com.codeborne.selenide.SelenideElement
import frontend.components.list.ProductsItem
import frontend.components.list.ProductsItems
import frontend.helpers.Wrappers.Companion.byDataTestGroup
import frontend.helpers.Wrappers.Companion.byDataTestId
import io.qameta.allure.Step

class ProductsPage {

  private val txtTitle: SelenideElement get() = element(byDataTestId("products-title"))
  private val listItems: ElementsCollection get() = elements(byDataTestGroup("product-card"))

  @Step("Получить название страницы продуктов")
  fun getTitle(): String {
    return txtTitle.text
  }

  @Step("Получить список продуктов на станице")
  fun getProducts(): ElementsCollection {
    return listItems
  }

  @Step("Получить список продуктов на станице Products в виде объектов")
  fun getProductsItems(): List<ProductsItem> {
    return ProductsItems(listItems).getItems()
  }
}