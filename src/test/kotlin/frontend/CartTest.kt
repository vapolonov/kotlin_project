package frontend

import frontend.components.CartPopup
import frontend.components.list.ProductItem
import frontend.helpers.BaseUiTest
import frontend.pages.MainPage
import io.kotest.matchers.equality.shouldBeEqualToDifferentTypeIgnoringFields
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CartTest : BaseUiTest() {

  @Test
  @DisplayName("Проверка продуктов в корзине")
  fun testProductsInCart() {
    val firstPopularProduct = MainPage()
      .getPopularProducts()
      .first()
    firstPopularProduct.btnIncrement.click()

    MainPage().header().clickLink("Cart")
    val firstCartProduct = CartPopup().getCartProducts().first()

    firstPopularProduct.apply { quantity = 1 }
      .shouldBeEqualToDifferentTypeIgnoringFields(
      firstCartProduct,
        ProductItem::description,
      ProductItem::btnIncrement,
      ProductItem::btnDecrement,
      ProductItem::image,
    )
  }
}
