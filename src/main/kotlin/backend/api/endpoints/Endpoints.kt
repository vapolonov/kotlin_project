package backend.api.endpoints

import backend.RetrofitClient

open class Endpoints {

    protected val authApi: AuthEndpoints by lazy { RetrofitClient.createService(AuthEndpoints::class.java) }
    protected val usersApi: UsersEndpoints by lazy { RetrofitClient.createService(UsersEndpoints::class.java) }
}