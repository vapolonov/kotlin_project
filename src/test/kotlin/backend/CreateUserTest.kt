package backend

import backend.api.extensions.Extensions.Companion.getAsObject
import backend.api.extensions.Extensions.Companion.getErrorAsObject
import backend.api.models.ErrorResponse
import backend.api.models.users.CreateUserRequest
import backend.api.models.users.defaultUser
import backend.api.models.users.defaultUserWithPhone
import backend.controllers.Controllers
import backend.helpers.AuthorizationHelper
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.kotest.matchers.shouldBe
import net.datafaker.Faker
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CreateUserTest : Controllers() {
  private val testData = Faker()
  private val authHelper = AuthorizationHelper()

  @Test
  @DisplayName("Create and delete user")
  fun testCreateAndDeleteUser() {
    val username = testData.credentials().username()
    val email = testData.internet().emailAddress()
    val password = testData.credentials().password()

    val response = users.createUser(CreateUserRequest(username, email, password)).getAsObject()

    response.username shouldBe username
    response.email shouldBe email

    val delete = users.deleteUserById(authHelper.getAdminToken(), response.id)

    delete.code() shouldBe 200
  }

  @Test
  @DisplayName("Create new user with empty password")
  fun testCreateUserWithoutPassword() {
    val username = testData.credentials().username()
    val email = testData.internet().emailAddress()

    val response = users.createUser(CreateUserRequest(username, email, "")).getErrorAsObject<ErrorResponse>()

    response.code shouldBe 400
    response.reason shouldBe "User details cannot be null or blank"
  }

  @Test
  @DisplayName("Create user with valid data")
  fun testCreateUserWithValidData() {
    val user = users.createUser(defaultUser).getAsObject()
    val expectedUser = users.getUserById(token = authHelper.getAdminToken(), id = user.id)

    expectedUser shouldBeEqualToComparingFields user
  }

  @Test
  @DisplayName("Update user")
  fun testUpdateUser() {
    val user = users.createUser(defaultUser).getAsObject()
    val userWithPhone = users.updateUserById(
      token = authHelper.getAdminToken(), user.id, defaultUserWithPhone
    ).getAsObject()
    val expectedUser = users.getUserById(token = authHelper.getAdminToken(), id = userWithPhone.id)

    expectedUser shouldBeEqualToComparingFields userWithPhone
  }
}