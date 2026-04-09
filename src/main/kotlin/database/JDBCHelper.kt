package database

import database.helpers.Extensions.Companion.toUser
import database.models.ProductsEntity
import database.models.UsersEntity
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

class JDBCHelper {
  private val jdbcUrl = "jdbc:postgresql://localhost:5432/playground"
  private val username = "postgres"
  private val password = "postgres"
  private val client = DriverManager.getConnection(jdbcUrl, username, password)

  fun getProducts(): List<ProductsEntity> {
    val products = mutableListOf<ProductsEntity>()

    try {
      val statement: Statement = client.createStatement()
      val resultSet: ResultSet = statement.executeQuery("SELECT * FROM table_products")
      while (resultSet.next()) {
        val product = ProductsEntity(
          id = resultSet.getInt("id"),
          name = resultSet.getString("name"),
          description = resultSet.getString("description"),
          price = resultSet.getDouble("price"),
        )
        products.add(product)
      }
      resultSet.close()
      statement.close()
    } catch (e: Exception) {
      println("Error fetching products: ${e.message}")
    }
    return products
  }

  fun getUserByEmail(email: String): UsersEntity? =
    runCatching {
      client.prepareStatement(
        "SELECT * FROM table_users WHERE email = ?"
      ).use { stmt ->
        stmt.setString(1, email)
        stmt.executeQuery().use { rs ->
          rs.takeIf { it.next() }?.toUser()
        }
      }
    }.onFailure {
      println("Error fetching user: ${it.message}")
    }.getOrNull()
}

