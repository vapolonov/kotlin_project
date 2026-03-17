package backend

import backend.api.extensions.Extensions.Companion.getAsObject
import backend.api.extensions.Extensions.Companion.getErrorAsObject
import backend.api.models.ErrorResponse
import backend.controllers.Controllers
import io.kotest.matchers.shouldBe
import net.datafaker.Faker
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CreateUserTest : Controllers() {
  val testData = Faker()

  @Test
  @DisplayName("Create new user")
  fun testCreateNewUser() {
    val username = testData.credentials().username()
    val email = testData.internet().emailAddress()
    val password = testData.credentials().password()

    val response = users.createUser(username, email, password).getAsObject()

    response.username shouldBe username
    response.email shouldBe email

    val responseDelete = users.deleteUserById(response.id).getAsObject()
    responseDelete.code shouldBe 200
    responseDelete.message shouldBe "User with id '${response.id}' was successfully deleted"
//    Response body is null or cannot be cast to the specified type: body: null | errorBody: {
//    "code": 401,
//    "reason": "The token is invalid."
  }

  @Test
  @DisplayName("Create new user with empty password")
  fun testCreateUserWithoutPassword() {
    val username = testData.credentials().username()
    val email = testData.internet().emailAddress()

    val response = users.createUser(username, email, "").getErrorAsObject<ErrorResponse>()

    response.code shouldBe 400
    response.reason shouldBe "User details cannot be null or blank"
  }
}