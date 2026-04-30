package backend.controllers

import backend.api.endpoints.Endpoints
import backend.api.extensions.Extensions.Companion.getAsObject
import backend.api.models.users.CreateUserRequest
import backend.api.models.users.CreateUserResponse
import backend.api.models.users.UpdateUserRequest
import backend.api.models.users.UpdateUserResponse
import backend.helpers.AuthorizationHelper
import backend.helpers.GarbageCollector
import backend.helpers.GarbageCollector.users
import io.qameta.allure.Step
import okhttp3.ResponseBody
import retrofit2.Response

class UsersController : Endpoints() {
  private val authHelper = AuthorizationHelper()

  @Step("Create new user")
  fun createUser(body: CreateUserRequest): Response<CreateUserResponse> {
  //  val user = usersApi.postUserCreate(body).execute()
  //  GarbageCollector.users.add(user.getAsObject().id)
  //  return user

    return usersApi.postUserCreate(body).execute()
//      .also { GarbageCollector.users.add(it.getAsObject().id) }  // replaced with all users deletion
  }

  @Step("Get user by id")
  fun getUserById(token: String = authHelper.getAdminToken(), id: Int): Response<CreateUserResponse> {
    return usersApi.getUserById(token, id).execute()
  }

  @Step("Delete user by id")
  fun deleteUserById(token: String = authHelper.getAdminToken(), id: Int): Response<ResponseBody> {
    return usersApi.deleteUser(token, id).execute()
  }

  @Step("Update user by id")
  fun updateUserById(token: String = authHelper.getAdminToken(), id: Int, body: UpdateUserRequest): Response<UpdateUserResponse> {
    return usersApi.updateUser(token, id, body).execute()
  }

  @Step("Get all users")
  fun getAllUsers(token: String = authHelper.getAdminToken(), offset: Int = 0, limit: Int = 50): Response<List<CreateUserResponse>> {
    return usersApi.getAllUsers(token, offset, limit).execute()
  }
}