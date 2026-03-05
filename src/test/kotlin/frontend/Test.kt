package frontend

import frontend.helpers.BaseUiTest
import frontend.pages.MainPage
import frontend.pages.ProductsPage
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


class Test : BaseUiTest() {

  @Test
  @DisplayName("Проверка названия кофейни на главной странице")
  fun testMainPageTitle() {
    val title = MainPage()
      .getTitle()

    title shouldBe "Welcome to Brew & Bean"
  }

  @Test
  @DisplayName("")
  fun productsCountTest() {
    MainPage()
      .header()
      .clickLink("Products")
    val products = ProductsPage()
      .getProducts()

    products.shouldHaveSize(7)
  }
}