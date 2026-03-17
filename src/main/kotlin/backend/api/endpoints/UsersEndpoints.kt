package backend.api.endpoints

import backend.api.models.users.CreateUserRequest
import backend.api.models.users.CreateUserResponse
import backend.api.models.users.DeleteUserResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface UsersEndpoints {

  @POST("users/create")
  fun postUserCreate(@Body body: CreateUserRequest) : Call<CreateUserResponse>

  @DELETE("users/{id}")
  fun deleteUser(@Header("Authorization") token : String, @Path("id") id : Int) : Call<ResponseBody>
}