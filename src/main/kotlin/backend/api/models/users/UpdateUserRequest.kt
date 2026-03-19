package backend.api.models.users

import backend.helpers.Utils.Companion.randomEmailPrefix

data class UpdateUserRequest(
    val username: String,
    val password: String,
    val email: String,
    val phoneNumber: String
)

val defaultUserWithPhone = UpdateUserRequest(
    username = defaultUser.username,
    password = defaultUser.password,
    email = defaultUser.email,
    phoneNumber = "+7(910)123-45-67"
)