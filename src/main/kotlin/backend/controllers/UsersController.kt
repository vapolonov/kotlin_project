package backend.controllers

import backend.api.endpoints.Endpoints
import backend.api.extensions.Extensions.Companion.getAsObject
import backend.api.models.users.CreateUserRequest
import backend.api.models.users.CreateUserResponse
import backend.api.models.users.UpdateUserRequest
import backend.api.models.users.UpdateUserResponse
import backend.helpers.GarbageCollector
import io.qameta.allure.Step
import okhttp3.ResponseBody
import retrofit2.Response

class UsersController : Endpoints() {

  @Step("Create new user")
  fun createUser(body: CreateUserRequest): Response<CreateUserResponse> {
  //  val user = usersApi.postUserCreate(body).execute()
  //  GarbageCollector.users.add(user.getAsObject().id)
  //  return user

    return usersApi.postUserCreate(body).execute().also { GarbageCollector.users.add(it.getAsObject().id) }
  }

  @Step("Get user by id")
  fun getUserById(token: String, id: Int): Response<CreateUserResponse> {
    return usersApi.getUserById(token, id).execute()
  }

  @Step("Delete user by id")
  fun deleteUserById(token: String, id: Int): Response<ResponseBody> {
    return usersApi.deleteUser(token, id).execute()
  }

  @Step("Update user by id")
  fun updateUserById(token: String, id: Int, body: UpdateUserRequest): Response<UpdateUserResponse> {
    return usersApi.updateUser(token, id, body).execute()
  }

}