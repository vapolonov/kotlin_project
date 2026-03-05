package frontend

import frontend.components.Header
import frontend.components.list.PopularItem
import frontend.helpers.BaseUiTest
import frontend.pages.MainPage
import frontend.pages.ProductsPage
import io.kotest.matchers.equality.shouldBeEqualToDifferentTypeIgnoringFields
import io.kotest.matchers.equals.shouldBeEqual
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

  @Test
  @DisplayName("Проверка что популярные продукты, есть на странице Products")
  fun testPopularProducts() {
    val secondPopularProduct = MainPage()
      .getPopularProducts()[1]

    MainPage()
      .header()
      .clickLink("Products")

    val txtTitle= ProductsPage().getTitle()
    txtTitle shouldBe "All Products"

    val secondProductsItem = ProductsPage()
      .getProductsItems()[1]

    secondPopularProduct.shouldBeEqualToDifferentTypeIgnoringFields(
      secondProductsItem,
      PopularItem::image,
      PopularItem::btnIncrement,
      PopularItem::btnDecrement,
    )
  }

  @Test
  @DisplayName("Проверка, что при выборе товара на главной странице, количество выбранного товара меняется на странице Products")
  fun testCountProductAsTheSaneAsPopularProduct() {
    MainPage()
      .getPopularProducts()[3]
      .btnIncrement
      .click()

    MainPage()
      .header()
      .clickLink("Products")

    val txtTitle= ProductsPage().getTitle()
    txtTitle shouldBe "All Products"

    val fourthProductsItem = ProductsPage()
      .getProductsItems()[3]

    val fourthPopularProduct = MainPage().getPopularProducts()[3]

    fourthPopularProduct.quantity shouldBeEqual fourthProductsItem.quantity
  }

  @Test
  @DisplayName("Сравнение популярных товаров с товарами на странице Products")
  fun testAllProducts() {
    val popularProducts = MainPage()
      .getPopularProducts()

    MainPage()
      .header()
      .clickLink("Products")

    val allProductsItems = ProductsPage()
      .getProductsItems()

    popularProducts.zip(allProductsItems).forEach { (popular, product) ->
      popular.shouldBeEqualToDifferentTypeIgnoringFields(
        product,
        PopularItem::image,
        PopularItem::btnIncrement,
        PopularItem::btnDecrement,
      )
    }
  }
}