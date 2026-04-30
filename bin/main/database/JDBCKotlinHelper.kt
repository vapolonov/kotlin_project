package database

import database.helpers.Extensions.Companion.toProduct
import database.helpers.Extensions.Companion.toUser
import database.models.ProductsEntity
import database.models.UsersEntity
import java.sql.DriverManager

class JDBCKotlinHelper {
  private val jdbcUrl = "jdbc:postgresql://localhost:5432/playground"
  private val username = "postgres"
  private val password = "postgres"
  private val client = DriverManager.getConnection(jdbcUrl, username, password)

  fun getProductsKotlin(): List<ProductsEntity> =
    client.use { conn ->
      conn.createStatement().use { stmt ->
        stmt.executeQuery("SELECT * FROM table_products").use { rs ->
          generateSequence { rs.takeIf { it.next() }?.toProduct() }.toList()
        }
      }
    }

  fun getUserByEmailKotlin(email: String): UsersEntity? =
    client.use { conn ->
      conn.prepareStatement("SELECT * FROM table_users WHERE email = ?").use { stmt ->
        stmt.setString(1, email)
        stmt.executeQuery().use { rs ->
          rs.takeIf { it.next() }?.toUser()
        }
      }
    }
}
