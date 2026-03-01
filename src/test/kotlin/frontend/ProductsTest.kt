package frontend

import frontend.components.Header
import frontend.helpers.BaseUiTest
import frontend.pages.ProductsPage
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ProductsTest : BaseUiTest() {

  @Test
  @DisplayName("Проверка заголовка страницы Products")
  fun testProductsPageTitle() {
    Header().clickLink("Products")
    val title = ProductsPage()
      .getTitle()

    title shouldBe "All Products"
  }
}