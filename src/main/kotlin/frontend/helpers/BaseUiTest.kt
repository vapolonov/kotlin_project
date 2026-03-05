package frontend.helpers

import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.open
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

open class BaseUiTest {

  init {
    Configuration.baseUrl = "http://localhost:4000"
    Configuration.timeout = 15000
    Configuration.pageLoadStrategy = "normal"
    Configuration.reopenBrowserOnFail = true
  }

  @BeforeEach
  fun openBrowser() {
    open("/")
  }

  @AfterEach
  fun closeBrowser() {
    Selenide.clearBrowserCookies()
    Selenide.clearBrowserLocalStorage()
    Selenide.closeWebDriver()
  }
}