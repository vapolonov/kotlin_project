package frontend.components

import com.codeborne.selenide.Selectors.shadowCss
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import frontend.helpers.Wrappers.Companion.byDataTestId
import io.qameta.allure.Step

class AuthPopup {

  private val emailInput: SelenideElement get() = `$`(shadowCss("input", "[data-test-id='login-email']"))
  private val passwordInput: SelenideElement get() = `$`(shadowCss("input", "[data-test-id='login-password']"))
  private val loginBtn: SelenideElement get() = `$`(byDataTestId("login-submit"))
  private val txtError: SelenideElement get() = `$`(byDataTestId("login-error"))

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
    return txtError.text
  }
}