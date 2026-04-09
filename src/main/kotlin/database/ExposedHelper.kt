package database

import database.helpers.Extensions.Companion.toModel
import database.helpers.Extensions.Companion.toUser
import database.models.ProductsEntity
import database.models.UsersEntity
import org.jetbrains.exposed.v1.core.dao.id.IntIdTable
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

class ExposedHelper {
  private val jdbcUrl = "jdbc:postgresql://localhost:5432/playground"
  private val username = "postgres"
  private val password = "postgres"

  private val database = Database.connect(
      url = jdbcUrl,
      driver = "org.postgresql.Driver",
      user = username,
      password = password
    )

  fun getAllProductsExposed() : List<ProductsEntity> {
    return transaction(database) {
      ProductEntity
        .selectAll()
        .map { ProductEntity.toModel(it) }
    }.also { database.connector().close() }
  }

  fun getUserByEmailExposed(email: String) : UsersEntity? {
    return transaction(database) {
      UserEntity
      .selectAll()
      .where { UserEntity.Email eq email }
      .map { it.toUser() }
      .singleOrNull()
    }.also { database.connector().close() }
  }
}

object ProductEntity : IntIdTable("table_products") {
  var Name = varchar("name", 100)
  var Description = varchar("description", 255)
  var Price = double("price")
}

object UserEntity : IntIdTable("table_users", "id") {
  var Username = varchar("username", 60)
  var Password = varchar("password", 100)
  var Email = varchar("email", 100)
}