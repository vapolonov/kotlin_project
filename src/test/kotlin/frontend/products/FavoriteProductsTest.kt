package frontend.products

import backend.api.extensions.Extensions.Companion.getAsObject
import backend.api.models.products.CreateProductRequest
import backend.controllers.Controllers
import frontend.helpers.BaseUiTest
import frontend.pages.MainPage
import io.kotest.matchers.shouldBe
import io.qameta.allure.Feature
import io.qameta.allure.Story
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@Feature("")
@Story("Products tests")
class FavoriteProductsTest : BaseUiTest() {
  private val controllers = Controllers()

  @Test
  @DisplayName("Check favorite products")
  fun checkFavoriteProductsExists() {
    val body = CreateProductRequest(
        name = "Coffee Black 2",
        description = "Coffee without milk",
        price = 2.51,
      )
    val product = controllers.products.createProduct(body = body).getAsObject()

    val favoritesList = MainPage()
      .openMainPage()
      .getPopularProducts()

    favoritesList.size shouldBe 1
    favoritesList.first().name shouldBe product.name
    favoritesList.first().price shouldBe product.price

  }
}