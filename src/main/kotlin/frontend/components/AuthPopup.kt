package frontend.components

import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selectors.shadowCss
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.SelenideElement
import frontend.helpers.Wrappers.Companion.byDataTestId
import io.kotest.matchers.should
import io.qameta.allure.Step

class AuthPopup {

  // private val emailInput: SelenideElement get() = element(shadowCss("input", "[data-test-id='login-email']"))
  private val emailInput: SelenideElement get() = element(byDataTestId("login-email"))
    .find(shadowCss("input"))

  // private val passwordInput: SelenideElement get() = element(shadowCss("input", "[data-test-id='login-password']"))
  private val passwordInput: SelenideElement get() = element(byDataTestId("login-password"))
    .find(shadowCss("input"))
  private val loginBtn: SelenideElement get() = element(byDataTestId("login-submit"))
  private val txtError: SelenideElement get() = element(byDataTestId("login-error"))

  @Step("Заполнить форму Login")
  fun fillLoginForm(email: String, pass: String): AuthPopup {
    emailInput.value = email
    passwordInput.value = pass
    return this
  }

  @Step("Нажать на кнопку Login")
  fun submitLogin(): AuthPopup {
    loginBtn.click()
    return this
  }

  @Step("Получить текст ошибки")
  fun getErrorText(): String {
    txtError.shouldBe(visible)
    return txtError.text
  }
}