package frontend.products

import backend.helpers.AuthorizationHelper
import backend.helpers.ProductsHelper
import backend.helpers.UserHelper
import frontend.helpers.BaseUiTest
import frontend.pages.ProductsPage
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import kotlin.random.Random

class AllProductsTest: BaseUiTest() {
  val productsHelper = ProductsHelper()
  val authHelper = AuthorizationHelper()
  val userHelper = UserHelper()

  @Test
  fun testFiveProductsExist() {
    val listOfProducts = productsHelper.createProducts(5).sortedBy { it.name }

    val productsList = ProductsPage()
      .openPage()
      .getProductsAsObjects()
      .sortedBy { it.name }

    productsList.size shouldBe 5
    productsList.forEachIndexed { index, product ->
      product.name shouldBe listOfProducts[index].name
    }
  }

  @Test
  fun productsShouldHaveCoffeeInName() {
    val email = "auto-${Random.nextInt(10000)}@autotest.com"
    val password = "random"
    userHelper.createUser(email, password)
    val token = authHelper.getToken(email, password)

    val productName = "COFFEE"
    val listOfProducts = productsHelper.createProductsViaRepeat(token, 3, productName).sortedBy { it.name }

    val productsList = ProductsPage()
      .openPage()
      .getProductsAsObjects()
      .filter { it.name.contains(productName) }

    productsList.size shouldBe 3
    productsList.forEachIndexed { index, product ->
      product.name shouldBe listOfProducts[index].name
    }


  }
}