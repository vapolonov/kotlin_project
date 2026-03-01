package frontend.pages

import com.codeborne.selenide.Selectors.shadowCss
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.Selenide.open
import com.codeborne.selenide.SelenideElement
import frontend.components.Header
import frontend.helpers.Wrappers.Companion.byDataTestId
import io.qameta.allure.Step

open class MainPage {

  private val txtTitle: SelenideElement get() = `$`(byDataTestId("main-image-text"))
  private val joinBtn: SelenideElement get() = `$`(byDataTestId("nav-link-auth"))
  private val loginLink: SelenideElement get() = `$`(byDataTestId("create-login"))
  private val emailInput: SelenideElement get() = `$`(shadowCss("input", "[data-test-id='login-email']"))
  private val passwordInput: SelenideElement get() = `$`(shadowCss("input", "[data-test-id='login-password']"))
  private val loginBtn: SelenideElement get() = `$`(byDataTestId("login-submit"))
  private val txtError: SelenideElement get() = `$`(byDataTestId("login-error"))

  @Step("Открыть главную страницу")
  fun openMainPage() {
    open("/")
  }

  @Step("Получить название кофейни")
  fun getTitle(): String {
    return txtTitle.text
  }

  @Step("Перейти к компоненту Header")
  fun header(): Header {
    return Header()
  }

  @Step("Открыть форму Login")
  fun openLoginForm(): MainPage {
    joinBtn.click()
    loginLink.click()
    return this
  }

  @Step("Заполнить форму Login")
  fun fillLoginForm(email: String, pass: String): MainPage {
    emailInput.value = email
    passwordInput.value = pass
    return this
  }

  @Step("Нажать на кнопку Login")
  fun submitLogin(): MainPage {
    loginBtn.click()
    return this
  }

  @Step("Получить текст ошибки")
  fun getErrorText(): String {
    return txtError.text
  }

}