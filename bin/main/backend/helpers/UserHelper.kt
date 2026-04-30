package backend.helpers

import backend.api.extensions.Extensions.Companion.getAsObject
import backend.api.models.users.CreateUserRequest
import backend.api.models.users.CreateUserResponse
import backend.controllers.Controllers

class UserHelper: Controllers() {

  fun createUser(email: String, password: String): CreateUserResponse {
    return users.createUser(body = CreateUserRequest(
      username = "random",
      email = email,
      password = password
    )).getAsObject()
  }
}