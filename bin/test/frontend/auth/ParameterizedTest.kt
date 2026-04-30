package frontend.auth

import frontend.helpers.BaseUiTest
import frontend.pages.MainPage
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class ParameterizedTest : BaseUiTest() {

  @ParameterizedTest
  @ValueSource(strings = ["Products", "Orders", "Contact", "Cart"])
  fun testWithValueSource(links: String) {
    val listLinks = MainPage().header().getLinks()

    listLinks.shouldContainAll(links)
  }

  @ParameterizedTest
  @CsvSource(
    "admin, 123,Invalid email or password",
    "admin,'',Please enter email and password",
    "'', 123,Please enter email and password",
    "'','',Please enter email and password"
  )
  @DisplayName("Проверить авторизацию с НЕ корректными данными")
  fun testUnsuccessfulLogin(email: String, password: String, message: String) {
    val error = MainPage()
      .openLoginForm()
      .authPopup()
      .fillLoginForm(email, password)
      .submitLogin()
      .getErrorText()
    error shouldBe message
  }

  @Test
  @DisplayName("Проверить авторизацию с корректными данными")
  fun testLogin() {
    MainPage()
      .openLoginForm()
      .authPopup()
      .fillLoginForm("admin@test.com", "QWE123qwe")
      .submitLogin()
    val isVisible = MainPage().header().checkAvatar()
    isVisible shouldBe true
  }
}