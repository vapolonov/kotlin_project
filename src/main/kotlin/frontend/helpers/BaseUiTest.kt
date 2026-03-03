package frontend.helpers

import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.open
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

open class BaseUiTest {

  companion object {
    init {
      Configuration.browser = DriverProvider::class.java.name
      Configuration.baseUrl = "https://google.com"
      Configuration.timeout = 15_000
      Configuration.pageLoadStrategy = "normal"
      Configuration.reopenBrowserOnFail = true
    }
  }

  @BeforeEach
  fun openBrowser() {
    open("/")
  }

  @AfterEach
  fun closeBrowser() {
    Selenide.clearBrowserCookies()
    Selenide.clearBrowserLocalStorage()
  }
}