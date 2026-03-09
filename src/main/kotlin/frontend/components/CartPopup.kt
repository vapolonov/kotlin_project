package frontend.components

import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.Selenide.elements
import frontend.components.list.CartItem
import frontend.components.list.CartItems
import frontend.helpers.Wrappers.Companion.byDataTestGroup
import frontend.helpers.Wrappers.Companion.byDataTestId
import io.qameta.allure.Step

class CartPopup {
  private val txtTotalPrice get() = element(byDataTestId("cart-total-price"))
  private val btnCheckout get() = element(byDataTestId("cart-checkout"))
  private val newCartItems get() = elements(byDataTestGroup("cart-item"))

  @Step("Получить список товаров в корзине")
  fun getCartProducts(): List<CartItem> {
    return CartItems(newCartItems).getItems()
  }
}