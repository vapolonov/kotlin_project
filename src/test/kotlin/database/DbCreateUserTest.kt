package database

import backend.helpers.GarbageCollector
import backend.helpers.Utils.Companion.randomEmailPrefix
import frontend.components.CreateUserPopup
import frontend.components.Header
import frontend.helpers.BaseUiTest
import frontend.pages.MainPage
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class DbCreateUserTest : BaseUiTest() {

  @Test
  @DisplayName("Создание пользователя через UI и проверка через БД")
  fun checkDBCreateUser() {
    val email = "${randomEmailPrefix()}@autotest.com"

    MainPage()
      .header()
      .clickLink("Join")

    CreateUserPopup()
      .fillCreateAccountForm(username = "random", email = email, pass = "random")
      .submitCreateUser()

    Header().checkAvatar()

    val user = JDBCHelper().getUserByEmail(email)
    user?.email shouldBe email
    user?.username shouldBe "random"
    GarbageCollector.users.add(user!!.id)
  }

  @Test
  @DisplayName("Создание пользователя через UI и проверка через БД")
  fun checkDBCreateUserKotlin() {
    val email = "${randomEmailPrefix()}@autotest.com"

    MainPage()
      .header()
      .clickLink("Join")

    CreateUserPopup()
      .fillCreateAccountForm(username = "random", email = email, pass = "random")
      .submitCreateUser()

    Header().checkAvatar()

    val user = JDBCKotlinHelper().getUserByEmailKotlin(email)
    user?.email shouldBe email
    user?.username shouldBe "random"
    GarbageCollector.users.add(user!!.id)
  }

  @Test
  @DisplayName("Создание пользователя через UI и проверка через БД")
  fun checkDBCreateUserExposed() {
    val email = "${randomEmailPrefix()}@autotest.com"

    MainPage()
      .header()
      .clickLink("Join")

    CreateUserPopup()
      .fillCreateAccountForm(username = "random", email = email, pass = "random")
      .submitCreateUser()

    Header().checkAvatar()

    val user = ExposedHelper().getUserByEmailExposed(email)
    user?.email shouldBe email
    user?.username shouldBe "random"
    GarbageCollector.users.add(user!!.id)
  }
}