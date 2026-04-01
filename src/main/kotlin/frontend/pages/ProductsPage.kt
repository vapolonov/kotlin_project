package frontend.pages

import com.codeborne.selenide.CollectionCondition.sizeGreaterThan
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.Selenide.elements
import com.codeborne.selenide.Selenide.open
import com.codeborne.selenide.SelenideElement
import frontend.components.list.ProductItem
import frontend.components.list.ProductsItems
import frontend.helpers.Wrappers.Companion.byDataTestGroup
import frontend.helpers.Wrappers.Companion.byDataTestId
import io.qameta.allure.Step

class ProductsPage {

  private val txtTitle: SelenideElement get() = element(byDataTestId("products-title"))
  private val listItems: ElementsCollection get() = elements(byDataTestGroup("product-card"))
  private val listProducts get() = ProductsItems().getItems()

  @Step("Open Products page")
  fun openPage(): ProductsPage {
    open("/products")
    return this
  }

  @Step("Получить название страницы продуктов")
  fun getTitle(): String {
    txtTitle.shouldBe(visible)
    return txtTitle.text
  }

  @Step("Получить список продуктов на странице")
  fun getProducts(): ElementsCollection {
    listItems.first().shouldBe(visible)
    return listItems
  }

  @Step("Получить список продуктов на станице Products в виде объектов")
  fun getProductsItems(): List<ProductItem> {
    listItems.shouldHave(sizeGreaterThan(0))
    return ProductsItems().getItems()
  }

  @Step("Получить список товаров")
  fun getProductsAsObjects(): List<ProductItem> {
    listItems.first().shouldBe(visible)
    return listProducts
  }
}