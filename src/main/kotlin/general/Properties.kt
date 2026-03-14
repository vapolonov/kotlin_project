package general

import java.util.Properties

@Suppress("JAVA_CLASS_ON_COMPANION")
class Properties {

  companion object {
    val properties : PropsModel by lazy {
      val stream = javaClass.getResourceAsStream(System.getProperty("env_config", "/tst.properties"))
        ?: throw IllegalStateException("Properties file not found")

      val props = Properties().apply { load(stream) }

      PropsModel(
        browserName = props.getProperty("browser.name", "chrome"),
        browserVersion = props.getProperty("browser.version", "latest"),
        frontendUrl = props.getProperty("frontend.url", "http://localhost:4000"),
        backendUrl = props.getProperty("backend.url", "http://localhost:1111/api/v1"),
        selenoidUrl = props.getProperty("selenoid.url", "https://selenoid.autotests.cloud"),
      )
    }
  }
}