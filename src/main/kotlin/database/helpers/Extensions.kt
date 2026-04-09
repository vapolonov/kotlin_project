package database.helpers

import database.ProductEntity
import database.UserEntity
import database.models.ProductsEntity
import database.models.UsersEntity
import org.jetbrains.exposed.v1.core.ResultRow
import java.sql.ResultSet

class Extensions {
  companion object {
    fun ProductEntity.toModel(resultRow: ResultRow) = ProductsEntity(
      id = resultRow[id].value,
      name = resultRow[ProductEntity.Name],
      description = resultRow[ProductEntity.Description],
      price = resultRow[ProductEntity.Price],
    )

    fun ResultRow.toUser() = UsersEntity(
      id = this[UserEntity.id].value,
      username = this[UserEntity.Username],
      password = this[UserEntity.Password],
      email = this[UserEntity.Email],
    )

    fun ResultSet.toProduct() = ProductsEntity(
      id = getInt("id"),
      name = getString("name"),
      description = getString("description"),
      price = getDouble("price"),
    )

    fun ResultSet.toUser() = UsersEntity(
      id = getInt("id"),
      username = getString("username"),
      email = getString("email"),
      password = getString("password"),
    )
  }
}