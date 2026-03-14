package general

import com.codeborne.selenide.Screenshots
import com.codeborne.selenide.Selenide
import io.qameta.allure.Attachment
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.TestWatcher
import org.openqa.selenium.logging.LogType.BROWSER
import java.util.Optional

object AllureTestWatcher : TestWatcher {

  override fun testSuccessful(context: ExtensionContext) {
    println("Test successful: - ${context.displayName}")
  }

  override fun testFailed(context: ExtensionContext, cause: Throwable?) {
    println("Test filed: ${context.displayName}")
    attachScreenshot()
    browserConsoleLogs()
  }

  override fun testDisabled(context: ExtensionContext, reason: Optional<String>) {
    println("Test disabled: ${context.displayName}, reason: $reason")
  }

  @Attachment(value = "{name}", type = "image/png")
  fun attachScreenshot(name: String = "SCREENSHOT_ON_FAIL"): ByteArray? {
    return Screenshots.takeScreenShotAsFile()?.readBytes()
  }

  @Attachment(value = "{attachName}", type = "text/plain")
  fun attachAsText(attachName: String?, message: String?): String? {
    return message
  }

  fun browserConsoleLogs() {
    attachAsText(
      "Browser console logs",
      java.lang.String.join("\n", Selenide.getWebDriverLogs(BROWSER))
    )
  }
}