package backend.controllers

import backend.api.endpoints.Endpoints
import backend.api.models.users.CreateUserRequest
import backend.api.models.users.CreateUserResponse
import backend.api.models.users.DeleteUserResponse
import io.qameta.allure.Step
import retrofit2.Response

class UsersController : Endpoints() {

  @Step("Create new user")
  fun createUser(username: String, email: String, password: String): Response<CreateUserResponse> {
    return usersApi.postUserCreate(body = CreateUserRequest(username = username, email = email, password = password)).execute()
  }

  @Step("Delete user by id")
  fun deleteUserById(id: Int): Response<DeleteUserResponse> {
    return usersApi.deleteUser(id).execute()
  }

}