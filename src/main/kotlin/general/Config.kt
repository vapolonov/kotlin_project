package general

import java.util.Properties

object Config {

  val get: PropsModel by lazy {
    val env = System.getProperty("env", "tst")
    val fileName = "/$env.properties"
//    val props = Properties().apply { load(FileInputStream("src/main/resources/$fileName")) }
    val props = Properties().apply {
      val stream =
        Config::class.java.getResourceAsStream(fileName) ?: throw IllegalStateException("Properties file not found")
      stream.use { load(it) }
    }

    fun Properties.getRequiredProperty(key: String): String {
      return getProperty(key) ?: throw IllegalStateException("Properties file not found")
    }

    PropsModel(
        browserName = props.getProperty("browser.name", "chrome"),
        browserVersion = props.getProperty("browser.version", "latest"),
        frontendUrl = props.getProperty("frontend.url", "http://localhost:4000"),
        backendUrl = props.getProperty("backend.url", "http://localhost:1111/api/v1"),
        selenoidUrl = props.getProperty("selenoid.url", "https://selenoid.autotests.cloud"),
      )
  }
}