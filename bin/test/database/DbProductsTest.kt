package database

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class DbProductsTest {

  @Test
  @DisplayName("Test fetching all products from the database -> basic JDBC")
  fun testGetAllProducts() {
    val jdbcClient = JDBCHelper()

    val products = jdbcClient.getProducts()
    assertEquals(3, products.size)
  }

  @Test
  @DisplayName("Test fetching all products from the database -> Kotlin JDBC")
  fun testGetAllProductsKotlin() {
    val jdbcClient = JDBCKotlinHelper()

    val products = jdbcClient.getProductsKotlin()
    assertEquals(3, products.size)
  }

  @Test
  @DisplayName("Test fetching all products from the database -> Exposed ORM")
  fun testGetAllProductsExposed() {
    val jdbcClient = ExposedHelper()

    val products = jdbcClient.getAllProductsExposed()
    assertEquals(3, products.size)
  }
}