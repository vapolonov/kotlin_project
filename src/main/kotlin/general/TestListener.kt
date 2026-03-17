package general

import com.codeborne.selenide.Screenshots
import com.codeborne.selenide.Selenide
import io.qameta.allure.Attachment
import org.junit.platform.engine.TestExecutionResult
import org.junit.platform.launcher.TestExecutionListener
import org.junit.platform.launcher.TestIdentifier
import org.junit.platform.launcher.TestPlan
import org.openqa.selenium.logging.LogType.BROWSER

class TestListener : TestExecutionListener {

  override fun testPlanExecutionStarted(testPlan: TestPlan) {
    println("<-----Starting Test Plan execution----->")
    println("Init Configurations").also { Config.get }
//    println("Init Selenide WebDriver").also { Configuration.browser = DriverProvider::class.java.name }
  }

  override fun executionSkipped(testIdentifier: TestIdentifier, reason: String) {
    if (testIdentifier.isTest) println("Skipping test: ${testIdentifier.displayName} - Reason: $reason")
  }

  override fun executionFinished(testIdentifier: TestIdentifier, testExecutionResult: TestExecutionResult) {
    if (testIdentifier.isTest) println("Finished test: ${testIdentifier.displayName} - Reason: ${testExecutionResult.status}")
    if (testExecutionResult.status == TestExecutionResult.Status.FAILED) {
      attachScreenshot()
//      browserConsoleLogs()
    }
  }

  override fun testPlanExecutionFinished(testPlan: TestPlan) {
    Selenide.closeWebDriver()
    println("<-----Finished Test Plan execution----->")
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